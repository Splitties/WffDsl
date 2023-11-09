package splitties.wff.complication

enum class SystemProvider {
    APP_SHORTCUT,
    DATE,
    DAY_OF_WEEK,
    FAVORITE_CONTACT,
    NEXT_EVENT,
    STEP_COUNT,
    SUNRISE_SUNSET,
    TIME_AND_DATE,
    UNREAD_NOTIFICATION_COUNT,
    WATCH_BATTERY,
    WORLD_CLOCK,
    DAY_AND_DATE,
    EMPTY;

    fun xmlValue() = name
}
