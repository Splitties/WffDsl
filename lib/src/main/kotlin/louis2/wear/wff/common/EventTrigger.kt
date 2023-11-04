package louis2.wear.wff.common

enum class EventTrigger {
    TAP, ON_VISIBLE, ON_NEXT_SECOND, ON_NEXT_MINUTE, ON_NEXT_HOUR;

    fun xmlValue() = name
}
