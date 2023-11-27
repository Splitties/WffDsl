package splitties.wff.extensions

import splitties.wff.Color
import splitties.wff.SupportsConditions
import splitties.wff.common.CONDITION
import splitties.wff.common.attributes.ArithmeticExpression
import splitties.wff.common.compare
import splitties.wff.common.condition
import splitties.wff.common.expression
import splitties.wff.common.expressions

fun List<Color.Static>.loop(): List<Color.Static> = this + this.first()

fun SupportsConditions.forEachPalette(
    vararg palettes: List<Color.Static>,
    block: CONDITION.COMPARE.(colors: List<Color.Static>) -> Unit
) = forEachPalette(palettes.asList(), block)

fun SupportsConditions.forEachPalette(
    palettes: List<List<Color.Static>>,
    block: CONDITION.COMPARE.(colors: List<Color.Static>) -> Unit
) {
    condition {
        expressions {
            palettes.forEachIndexed { index, colors ->
                expression("$index") {
                    ArithmeticExpression.Boolean {
                        colors.mapIndexed { index, color ->
                            "[CONFIGURATION.colors.$index] == ${color.xmlValue()}"
                        }.joinToString(" && ")
                    }
                }
            }
        }
        palettes.forEachIndexed { index, colors ->
            compare("$index") { block(colors) }
        }
    }
}
