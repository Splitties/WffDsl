package splitties.wff.extensions

import splitties.wff.Color
import splitties.wff.SupportsConditions
import splitties.wff.common.CONDITION
import splitties.wff.common.attributes.ArithmeticExpression
import splitties.wff.common.compare
import splitties.wff.common.condition
import splitties.wff.common.expression
import splitties.wff.common.expressions

fun List<Color.Static>.loop(
    syntheticEvening: Boolean = true
): List<Color.Static> = if (syntheticEvening) buildList(capacity = size * 2) {
    val initial = this@loop
    add(initial.first())
    initial.subList(fromIndex = 1, toIndex = initial.size).forEach {
        add(it)
        add(it)
    }
    add(initial.first())
} else this + this.first()

fun Color.Companion.list(
    builder: ColorListBuilder.() -> Unit
): List<Color.Static> = ColorListBuilder().apply(builder).list

class ColorListBuilder internal constructor() {
    internal val list = mutableListOf<Color.Static>()
    fun rgb(bits: Int) {
        list += Color.rgb(bits)
    }

    fun argb(bits: UInt) {
        list += Color.argb(bits)
    }
}
