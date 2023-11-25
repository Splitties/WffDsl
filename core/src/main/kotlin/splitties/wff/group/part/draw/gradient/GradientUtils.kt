package splitties.wff.group.part.draw.gradient

import splitties.wff.Color

@PublishedApi
internal fun FloatArray?.asGradientPositions(colors: List<Color>): String {
    if (this == null) return List(colors.size) {
        it / colors.lastIndex.toFloat()
    }.joinToString(separator = " ")
    onEachIndexed { index, it ->
        require(it in 0f..1f) {
            "position at index $index is not within [0..1] ($it)"
        }
    }
    require(size == colors.size) { "colors and positions must have matching sizes" }
    return joinToString(separator = " ")
}
