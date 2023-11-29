package splitties.wff.extensions

import splitties.wff.SupportsBooleanConfiguration
import splitties.wff.common.attributes.ArithmeticExpression
import splitties.wff.group.configuration.BOOLEANCONFIGURATION
import splitties.wff.group.configuration.booleanConfiguration
import splitties.wff.group.configuration.booleanOption
import splitties.wff.userConfiguration.USERCONFIGURATIONS
import splitties.wff.userConfiguration.booleanConfiguration

class BoolConfiguration(
    private val id: String,
) : AbstractConfiguration() {

    context (SupportsBooleanConfiguration)
    fun ifTrue(block: BOOLEANCONFIGURATION.BOOLEANOPTION.() -> Unit) {
        booleanConfiguration(id) {
            booleanOption(true) {
                block()
            }
        }
    }

    context (SupportsBooleanConfiguration)
    fun ifFalse(block: BOOLEANCONFIGURATION.BOOLEANOPTION.() -> Unit) {
        booleanConfiguration(id) {
            booleanOption(false) {
                block()
            }
        }
    }

    val value: ArithmeticExpression.Boolean = ArithmeticExpression.Boolean {
        "[CONFIGURATION.$id]"
    }

    context (USERCONFIGURATIONS)
    fun register(
        displayName: String,
        screenReaderText: String? = null,
        icon: String? = null,
        defaultValue: Boolean
    ) = registration {
        booleanConfiguration(
            id = id,
            displayName = displayName,
            screenReaderText = screenReaderText,
            icon = icon,
            defaultValue = defaultValue
        )
    }
}
