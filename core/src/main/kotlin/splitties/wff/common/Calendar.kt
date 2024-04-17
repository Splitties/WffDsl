package splitties.wff.common

enum class Calendar {
    BUDDHIST,
    CHINESE,
    COPTIC,
    DANGI,
    ETHIOPIC,
    ETHIOPIC_AMETE_ALEM,
    GREGORIAN,
    HEBREW,
    INDIAN,
    ISLAMIC,
    ISLAMIC_CIVIL,
    ISLAMIC_UMALQURA,
    JAPANESE,
    PERSIAN,
    ROC;
    fun xmlValue(): String = name
}
