package louis2.wear.wff.group.part.draw.gradient

import louis2.wear.wff.internal.asArgbColor

@ExperimentalUnsignedTypes
@PublishedApi
internal fun FloatArray?.asGradientPositions(colors: UIntArray): String {
    if (this == null) return ""
    onEachIndexed { index, it ->
        require(it in 0f..1f) {
            "position at index $index is not within [0..1] ($it)"
        }
    }
    require(size == colors.size) { "colors and positions must have matching sizes" }
    return joinToString(separator = " ")
}

@ExperimentalUnsignedTypes
@PublishedApi
internal fun UIntArray.asGradientColors(): String {
    return joinToString(separator = " ") { it.asArgbColor() }
}
