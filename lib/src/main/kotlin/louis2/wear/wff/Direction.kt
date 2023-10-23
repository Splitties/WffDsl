package louis2.wear.wff

enum class Direction {
    CLOCKWISE, COUNTER_CLOCKWISE;

    fun xmlValue(): String? = when (this) {
        CLOCKWISE -> null
        COUNTER_CLOCKWISE -> name
    }
}
