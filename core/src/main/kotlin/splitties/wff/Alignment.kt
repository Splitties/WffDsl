package splitties.wff

enum class Alignment {
    START, CENTER, END;
    fun xmlValue(default: splitties.wff.Alignment): String? = when (this) {
        default -> null
        else -> name
    }
}
