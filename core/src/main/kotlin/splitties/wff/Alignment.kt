package splitties.wff

enum class Alignment {
    START, CENTER, END;
    fun xmlValue(default: Alignment): String? = when (this) {
        default -> null
        else -> name
    }
}
