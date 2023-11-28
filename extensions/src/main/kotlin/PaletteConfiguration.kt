package splitties.wff.extensions

import splitties.wff.Color
import splitties.wff.CompareScope
import splitties.wff.SupportsConditions
import splitties.wff.common.*
import splitties.wff.common.attributes.ArithmeticExpression
import splitties.wff.userConfiguration.*

class PaletteConfiguration(
    private val id: String,
    private val palettes: List<Palette>,
    private val defaultValueIndex: Int = 0
) : AbstractConfiguration() {

    init {
        if (defaultValueIndex > palettes.lastIndex) {
            val max = palettes.lastIndex
            val given = defaultValueIndex
            throw IndexOutOfBoundsException("Max index is $max but $given was provided")
        }
    }

    context (SupportsConditions)
    fun forEach(block: CompareScope.(colors: List<Color.Static>) -> Unit) {
        condition {
            expressions {
                palettes.forEachIndexed { index, palette ->
                    expression("$index") {
                        ArithmeticExpression.Boolean {
                            palette.colors.mapIndexed { index, color ->
                                "[CONFIGURATION.colors.$index] == ${color.xmlValue()}"
                            }.joinToString(" && ")
                        }
                    }
                }
            }
            palettes.forEachIndexed { index, palette ->
                compare("$index") { block(palette.colors) }
            }
        }
    }

    context (USERCONFIGURATIONS)
    fun register(
        displayName: String,
        screenReaderText: String? = null,
        icon: String? = null
    ) = registration {
        colorConfiguration(
            id = id,
            displayName = displayName,
            screenReaderText = screenReaderText,
            icon = icon,
            defaultValue = palettes[defaultValueIndex].stableKey
        ) {
            palettes.forEach {
                colorOption(
                    id = it.stableKey,
                    displayName = it.displayName,
                    screenReaderText = it.screenReaderText,
                    icon = it.icon,
                    colors = it.colors
                )
            }
        }
    }
}
