package splitties.wff

sealed class Color private constructor() {

    companion object {
        fun argb(bits: UInt): Argb = Argb(bits)
        fun rgb(bits: Int): Rgb = Rgb(bits)
        fun configurable(id: String, index: Int? = null): Configurable = Configurable(id = id, index = index)

        val white = rgb(0xFF_FF_FF)
        val black = rgb(0x0)
    }

    abstract fun xmlValue(): String

    sealed class Static : Color() {
        protected abstract fun uintBits(): UInt
        fun withAlpha(alpha: UByte): Argb = Argb(alpha.toUInt() shl 24 or (uintBits() and 0xFFFFFFu))
    }

    class Argb internal constructor(private val bits: UInt) : Static() {
        override fun uintBits() = bits
        override fun xmlValue(): String = "#${bits.toString(radix = 16)}"
    }

    class Rgb internal constructor(private val bits: Int) : Static() {
        override fun uintBits() = bits.toUInt()

        override fun xmlValue(): String {
            val r: String = bits.getByte(position = 2).toString(radix = 16).padStart(length = 2, padChar = '0')
            val g: String = bits.getByte(position = 1).toString(radix = 16).padStart(length = 2, padChar = '0')
            val b: String = bits.getByte(position = 0).toString(radix = 16).padStart(length = 2, padChar = '0')
            return "#$r$g$b"
        }

        private fun Int.getByte(position: Int): UByte = (this shr position * 8).toUByte()
    }

    class Configurable internal constructor(
        private val id: String,
        private val index: Int?
    ) : Color() {
        override fun xmlValue() = when (index) {
            null -> "[CONFIGURATION.$id]"
            else -> "[CONFIGURATION.$id.$index]"
        }
    }
}
