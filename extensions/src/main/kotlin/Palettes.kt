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
