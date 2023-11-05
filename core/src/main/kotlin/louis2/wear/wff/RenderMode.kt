package louis2.wear.wff

enum class RenderMode {
    SOURCE, MASK, ALL;

    fun xmlValue(): String? = when (this) {
        SOURCE -> null
        MASK -> name
        ALL -> name
    }
}
