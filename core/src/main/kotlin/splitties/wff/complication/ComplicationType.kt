package splitties.wff.complication

enum class ComplicationType {
    SHORT_TEXT,
    LONG_TEXT,
    MONOCHROMATIC_IMAGE,
    SMALL_IMAGE,
    PHOTO_IMAGE,
    RANGED_VALUE,
    EMPTY;

    fun xmlValue() = name
}
