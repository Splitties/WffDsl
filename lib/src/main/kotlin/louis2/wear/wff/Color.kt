package louis2.wear.wff

sealed class Color private constructor() {
    companion object {
        fun argb(bits: UInt): Color = Argb(bits)
        fun rgb(bits: Int): Color = Rgb(bits)

        val white = rgb(0xFF_FF_FF)
        val black = rgb(0x0)
    }

    abstract fun xmlValue(): String

    private class Argb(private val bits: UInt) : Color() {
        override fun xmlValue(): String = "#${bits.toString(radix = 16)}"
    }

    private class Rgb(private val bits: Int) : Color() {
        override fun xmlValue(): String {
            val r: String = bits.getByte(position = 2).toString(radix = 16)
            val g: String = bits.getByte(position = 1).toString(radix = 16)
            val b: String = bits.getByte(position = 0).toString(radix = 16)
            return "#$r$g$b"
        }

        fun Int.getByte(position: Int): UByte = (this shr position * 8).toUByte()
    }

}
