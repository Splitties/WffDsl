package louis2.wear.wff

sealed class Color private constructor() {
    companion object {
        fun argb(bits: UInt): Color = Argb(bits)
        fun rgb(bits: Int): Color = Rgb(bits)
        fun configurable(id: String, index: Int? = null): Color = Configurable(id = id, index = index)

        val white = rgb(0xFF_FF_FF)
        val black = rgb(0x0)
    }

    abstract fun xmlValue(): String

    private class Argb(private val bits: UInt) : Color() {
        override fun xmlValue(): String = "#${bits.toString(radix = 16)}"
    }

    private class Rgb(private val bits: Int) : Color() {
        override fun xmlValue(): String {
            val r: String = bits.getByte(position = 2).toString(radix = 16).padStart(length = 2, padChar = '0')
            val g: String = bits.getByte(position = 1).toString(radix = 16).padStart(length = 2, padChar = '0')
            val b: String = bits.getByte(position = 0).toString(radix = 16).padStart(length = 2, padChar = '0')
            return "#$r$g$b"
        }

        fun Int.getByte(position: Int): UByte = (this shr position * 8).toUByte()
    }

    private class Configurable(
        private val id: String,
        private val index: Int?
    ) : Color() {
        override fun xmlValue() = when (index) {
            null -> "[CONFIGURATION.$id]"
            else -> "[CONFIGURATION.$id.$index]"
        }
    }
}
