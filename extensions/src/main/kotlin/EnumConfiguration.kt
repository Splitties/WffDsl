package splitties.wff.extensions

import splitties.wff.CompareScope
import splitties.wff.SupportsConditions
import splitties.wff.SupportsListConfiguration
import splitties.wff.common.*
import splitties.wff.common.attributes.ArithmeticExpression
import splitties.wff.common.attributes.ArithmeticExpressionScope
import splitties.wff.group.configuration.LISTCONFIGURATION
import splitties.wff.group.configuration.listConfiguration
import splitties.wff.group.configuration.listOption
import splitties.wff.userConfiguration.USERCONFIGURATIONS
import splitties.wff.userConfiguration.listConfiguration
import splitties.wff.userConfiguration.listOption
import kotlin.enums.EnumEntries
import kotlin.enums.enumEntries
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
class EnumConfiguration<T> @PublishedApi internal constructor(
    private val id: String,
    private val entries: EnumEntries<T>
) : AbstractConfiguration() where T : Enum<T>, T : EnumConfiguration.Entry {

    init {
        val checkedEntries = ArrayList<String>(entries.size)
        entries.forEach {
            if (checkedEntries.add(it.stableKey).not()) {
                throw IllegalArgumentException(
                    "entry ${it.name} is using the stableKey of another entry."
                )
            }
        }
    }

    interface Entry {
        val stableKey: String
        val displayName: String? get() = null
        val screenReaderText: String? get() = null
        val icon: String? get() = null
    }

    companion object {

        @OptIn(ExperimentalStdlibApi::class)
        inline operator fun <reified T> invoke(
            id: String
        ) where T : Enum<T>, T : Entry = EnumConfiguration(
            id = id,
            entries = enumEntries<T>()
        )
    }

    context (SupportsConditions)
    fun withValue(block: CompareScope.(T) -> Unit) {
        checkRegistered()
        condition {
            expressions {
                entries.forEach { entry ->
                    expression(entry.stableKey, ArithmeticExpression.Boolean {
                        "[CONFIGURATION.$id] == \"${entry.stableKey}\""
                    })
                }
            }
            entries.forEach { entry ->
                compare(entry.stableKey) {
                    block(entry)
                }
            }
        }
    }

    context (SupportsListConfiguration)
    fun withValueDoesNotWork(block: LISTCONFIGURATION.LISTOPTION.(T) -> Unit) {
        checkRegistered()
        listConfiguration(id) {
            entries.forEach { entry ->
                listOption(id = entry.stableKey) {
                    block(entry)
                }
            }
        }
    }

    context (SupportsConditions)
    fun ifSetTo(value: T, block: CompareScope.() -> Unit) {
        checkRegistered()
        condition {
            expressions { expression("0", isSetTo(value)) }
            compare("0") { block() }
        }
    }

    fun isSetTo(value: T): ArithmeticExpression.Boolean {
        checkRegistered()
        return ArithmeticExpression.Boolean {
            "[CONFIGURATION.$id] == \"${value.stableKey}\""
        }
    }

    @OverloadResolutionByLambdaReturnType
    fun map(
        block: ArithmeticExpressionScope.(T) -> ArithmeticExpression.Float
    ): ArithmeticExpression.Float = map(ArithmeticExpression.float, block)

    @OverloadResolutionByLambdaReturnType
    fun map(
        block: ArithmeticExpressionScope.(T) -> ArithmeticExpression.Int
    ): ArithmeticExpression.Int = map(ArithmeticExpression.int, block)

    @OverloadResolutionByLambdaReturnType
    fun map(
        block: ArithmeticExpressionScope.(T) -> ArithmeticExpression.Boolean
    ): ArithmeticExpression.Boolean = map(ArithmeticExpression.boolean, block)

    @OverloadResolutionByLambdaReturnType
    fun map(
        block: ArithmeticExpressionScope.(T) -> ArithmeticExpression.String
    ): ArithmeticExpression.String = map(ArithmeticExpression.string, block)

    private fun <R : ArithmeticExpression> map(
        create: (String) -> R,
        block: ArithmeticExpressionScope.(T) -> R
    ): R {
        checkRegistered()
        val rawExp = buildString {
            entries.forEachIndexed { index, entry ->
                val exp = ArithmeticExpressionScope().block(entry)
                if (index == entries.lastIndex) {
                    append(exp)
                } else {
                    append("[CONFIGURATION.$id] == \"${entry.stableKey}\"")
                    append(" ? $exp : ")
                }
            }
        }
        return create(rawExp)
    }

    context (USERCONFIGURATIONS)
    fun register(
        displayName: String,
        screenReaderText: String? = null,
        icon: String? = null,
        defaultValue: T = entries.first()
    ) = registration {
        listConfiguration(
            id = id,
            displayName = displayName,
            screenReaderText = screenReaderText,
            icon = icon,
            defaultValue = defaultValue.stableKey
        ) {
            entries.forEach {
                listOption(
                    id = it.stableKey,
                    displayName = it.displayName ?: it.name.lowercase(),
                    screenReaderText = it.screenReaderText,
                    icon = it.icon
                )
            }
        }
    }
}
