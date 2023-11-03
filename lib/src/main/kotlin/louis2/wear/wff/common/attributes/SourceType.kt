package louis2.wear.wff.common.attributes

import louis2.wear.wff.common.attributes.ArithmeticExpression as Exp

/**
 * The value of a data source, which updates a real-time value that appears on the watch face.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/attributes/source-type)
 */
object SourceType {

    /**
     * ## Time and day
     *
     * The Watch Face Format supports data sources related to the time and day.
     */
    object Time {

        /**
         * The number of milliseconds that have elapsed since midnight UTC on January 1, 1970.
         *
         * _UTC timestamp with milliseconds wouldn't fit into an int,
         * but this is not evaluated in Kotlin, and types are erased
         * before writing in the XML anyway._
         */
        val UTC_TIMESTAMP = Exp.Int { "[UTC_TIMESTAMP]" }

        /** An integer value that represents the millisecond field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current time. This value is always between `0` and `999`, inclusive. */
        val MILLISECOND = Exp.Int { "[MILLISECOND]" }

        /** An integer value that represents the second field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current time. This value is always between `0` and `59`, inclusive. */
        val SECOND = Exp.Int { "[SECOND]" }

        /** A string that represents the second field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current time, with leading zeros to make the value 2 characters long. This value is always between `00` and `59`, inclusive. */
        val SECOND_Z = Exp.String { "[SECOND_Z]" }

        /** A floating-point value that combines the second and millisecond fields of a [](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html)[`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current time. This value is always between `0.0` and `59.999`, inclusive. */
        val SECOND_MILLISECOND = Exp.Float { "[SECOND_MILLISECOND]" }

        /** The number of seconds that have elapsed during the current day, based on the values for `HOUR_0_23`, `MINUTE`, and `SECOND`. This value is always between `0` and `86399` (`24*60*60−1`), inclusive. */
        val SECONDS_IN_DAY = Exp.Int { "[SECONDS_IN_DAY]" }

        /** An integer value that represents the minute field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current time. This value is always between `0` and `59`, inclusive. */
        val MINUTE = Exp.Int { "[MINUTE]" }

        /** A string value that represents the minute field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current time, with leading zeros to make the value 2 characters long. This value is always between `00` and `59`, inclusive. */
        val MINUTE_Z = Exp.String { "[MINUTE_Z]" }

        /** A floating-point value that combines the minute and second fields of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current time. This value is always between `0.0` and `59+59/60`, inclusive. */
        val MINUTE_SECOND = Exp.Float { "[MINUTE_SECOND]" }

        /** The 12-hour component of the current time, represented as a value between `0` and `11` inclusive. If the current [`CLOCK_HOUR_OF_AMPM`](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/ChronoField.html#CLOCK_HOUR_OF_AMPM) is `12`, such as in `12:34 PM`, this value is converted to `0`. */
        val HOUR_0_11 = Exp.Int { "[HOUR_0_11]" }

        /** A string that represents the 12-hour component of the current time -- from `00` to `11` inclusive -- using the Wear OS device's current time zone. If this value is less than `10`, it includes a leading zero. */
        val HOUR_0_11_Z = Exp.String { "[HOUR_0_11_Z]" }

        /** A floating-point value the combines a modified `CLOCK_HOUR_OF_AMPM` field and minute field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current time. This value is always between `0.0` and `11+59/60`, inclusive. */
        val HOUR_0_11_MINUTE = Exp.Float { "[HOUR_0_11_MINUTE]" }

        /** The 12-hour component of the current time, represented as a value between `1` and `12` inclusive. If the current [`HOUR_OF_DAY`](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/ChronoField.html#CLOCK_HOUR_OF_AMPM) is `0`, such as in `0:12` (12:12 AM), this value is converted to `12`. */
        val HOUR_1_12 = Exp.Int { "[HOUR_1_12]" }

        /** A string that represents the 12-hour component of the current time -- from `01` to `12` inclusive -- using the Wear OS device's current time zone. If this value is less than `10`, it includes a leading zero. */
        val HOUR_1_12_Z = Exp.String { "[HOUR_1_12_Z]" }

        /** A floating-point value the combines the `CLOCK_HOUR_OF_AMPM` and minute fields of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current time. This value is always between `1.0` and `12+59/60`, inclusive. */
        val HOUR_1_12_MINUTE = Exp.Float { "[HOUR_1_12_MINUTE]" }

        /** The 24-hour component of the current time, represented as a value between `0` and `23` inclusive. If the value is `24`, such as in `24:13` (12:13 AM the next day), this value is converted to `0`. */
        val HOUR_0_23 = Exp.Int { "[HOUR_0_23]" }

        /** A string that represents the 24-hour component of the current time -- from `00` to `23` inclusive -- using the Wear OS device's current time zone. If this value is less than `10`, it includes a leading zero. */
        val HOUR_0_23_Z = Exp.String { "[HOUR_0_23_Z]" }

        /** A floating-point value the combines the hour and minute fields of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current time. This value is always between `0.0` and `23+59/60`, inclusive. */
        val HOUR_0_23_MINUTE = Exp.Float { "[HOUR_0_23_MINUTE]" }

        /** The 24-hour component of the current time, represented as a value between `1` and `24` inclusive. If the value is `0`, such as in `0:12` (12:12 AM), this value is converted to `24`. */
        val HOUR_1_24 = Exp.Int { "[HOUR_1_24]" }

        /** A string that represents the 12-hour component of the current time -- from `01` to `24` inclusive -- using the Wear OS device's current time zone. If this value is less than `10`, it includes a leading zero. */
        val HOUR_1_24_Z = Exp.String { "[HOUR_1_24_Z]" }

        /** A floating-point value the combines a modified hour field and minute field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current time. This value is always between `1.0` and `24+59/60`, inclusive. */
        val HOUR_1_24_MINUTE = Exp.Float { "[HOUR_1_24_MINUTE]" }

        /** An integer value that represents the day field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current date. This value is always between `1` and `31`, inclusive. */
        val DAY = Exp.Int { "[DAY]" }

        /** A string value that represents the day field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current date, with leading zeros to make the value 2 characters long. This value is always between `01` and `31`, inclusive. */
        val DAY_Z = Exp.String { "[DAY_Z]" }

        /** A floating-point value that combines the day-of-month and hour fields of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current date and time. This value is always between `1.0` and `31+23/24`, inclusive. */
        val DAY_HOUR = Exp.Float { "[DAY_HOUR]" }

        /** A modified version of the [`getDayOfMonth()`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html#getDayOfMonth--) value of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current date, subtracted by 1. This value is always between `0` and `30`, inclusive. */
        val DAY_0_30 = Exp.Int { "[DAY_0_30]" }

        /** A floating-point value that combines a modified day-of-month field and hour field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current date and time. This value is always between `0.0` and `30+23/24`, inclusive. */
        val DAY_0_30_HOUR = Exp.Float { "[DAY_0_30_HOUR]" }

        /** The number of days that have started since the calendar year changed. This value is always between `1` and `366` inclusive, and has a maximum value of `365` during non-leap years. */
        val DAY_OF_YEAR = Exp.Int { "[DAY_OF_YEAR]" }

        /** A modified version of the `getDayOfWeek()` value from a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current date. The Watch Face Format assigns a value of `1` to represent Sunday and a value of `7` to represent Saturday. */
        val DAY_OF_WEEK = Exp.Int { "[DAY_OF_WEEK]" }

        /** The current day of the week, represented as a full-length string. Examples include `Sunday` and `Monday`. */
        val DAY_OF_WEEK_F = Exp.String { "[DAY_OF_WEEK_F]" }

        /** The current day of the week, represented as a shortened string. Examples include `Sun` and `Mon`. */
        val DAY_OF_WEEK_S = Exp.String { "[DAY_OF_WEEK_S]" }

        /** An integer value that represents the month field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current date. This value is always between `1` and `12`, inclusive. */
        val MONTH = Exp.Int { "[MONTH]" }

        /** A string value that represents the month field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current date, with leading zeros to make the value 2 characters long. This value is always between `01` and `12`, inclusive. */
        val MONTH_Z = Exp.String { "[MONTH_Z]" }

        /** The current month of the year, represented as a full-length string. Examples include `January` and `December`. */
        val MONTH_F = Exp.String { "[MONTH_F]" }

        /** The current month of the year, represented as a shortened string. Examples include `Jan` and `Dec`. */
        val MONTH_S = Exp.String { "[MONTH_S]" }

        /** The number of days in the current month. This value is either `28` or `29` during February, `30` during April, June, September, and November, and `31` during all other months of the Gregorian calendar. */
        val DAYS_IN_MONTH = Exp.Int { "[DAYS_IN_MONTH]" }

        /** A floating-point value that combines the month-of-year field and modified day-of-month field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current date. This value is always between `1.0` and `12+29/30`, inclusive. */
        val MONTH_DAY = Exp.Float { "[MONTH_DAY]" }

        /** A modified version of the `getMonthValue()` value from a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current date. This value assigns a value of `0` to January and a value of `11` to December. */
        val MONTH_0_11 = Exp.Int { "[MONTH_0_11]" }

        /** A floating-point value that combines a modified month-of-year field and modified day-of-month field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current date. This value is always between `0.0` and `11+29/30`, inclusive. */
        val MONTH_0_11_DAY = Exp.Float { "[MONTH_0_11_DAY]" }

        /** An integer value that represents the year field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object for the current date. This value is clamped to always be between `1902` and `2100`, inclusive. */
        val YEAR = Exp.Int { "[YEAR]" }

        /** A 2-digit integer that represents the last 2 digits of the current year. For example, the value for 2023 is `23`. */
        val YEAR_S = Exp.String { "[YEAR_S]" }

        /** A floating-point value that combines the year field and modified day-of-month field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current date. January 2023 is represented as `2023.0`, and December 2023 is represented as `2023+11/12`. */
        val YEAR_MONTH = Exp.Float { "[YEAR_MONTH]" }

        /** A floating-point value that combines the year field, a modified month-of-year field, and a modified day-of-month field of a [`ZonedDateTime`](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) object that represents the current date. January 1, 2023 is represented as `2023.0`, and December 31, 2023 is represented as `2023+(11+29/30)/12≈2023.9972`. */
        val YEAR_MONTH_DAY = Exp.Float { "[YEAR_MONTH_DAY]" }

        /** An integer that represents the value of the [`ALIGNED_WEEK_OF_MONTH`](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/ChronoField.html#ALIGNED_WEEK_OF_MONTH) of a `ChronoField` object that represents the current date. This value is always between `0` and `5`, inclusive. */
        val WEEK_IN_MONTH = Exp.Int { "[WEEK_IN_MONTH]" }

        /** An integer that represents the value of the [`ALIGNED_WEEK_OF_YEAR`](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/ChronoField.html#ALIGNED_WEEK_OF_YEAR) of a `ChronoField` object that represents the current date. This value is always between `1` and `53`, inclusive. */
        val WEEK_IN_YEAR = Exp.Int { "[WEEK_IN_YEAR]" }

        /** A boolean value that is equivalent to the current return value of [`is24HourFormat()`](https://developer.android.com/reference/android/text/format/DateFormat#is24HourFormat(android.content.Context)), based on the user's chosen locale and preferences. */
        val IS_24_HOUR_MODE = Exp.Boolean { "[IS_24_HOUR_MODE]" }

        /** A boolean value that is equivalent to calling [`useDaylightTime()`](https://developer.android.com/reference/android/icu/util/TimeZone#useDaylightTime()) on the `TimeZone` object for the current locale. Indicates whether the currently-set time zone observes daylight saving time. */
        val IS_DAYLIGHT_SAVING_TIME = Exp.Boolean { "[IS_DAYLIGHT_SAVING_TIME]" }

        /** A string that is equivalent to calling [`getDisplayName()`](https://developer.android.com/reference/android/icu/util/TimeZone#getDisplayName()) on the `TimeZone` object for the current locale. Returns the full time zone name, such as "Eastern Standard Time" for New York City's time zone during the winter. */
        val TIMEZONE = Exp.String { "[TIMEZONE]" }

        /** A string that is equivalent to calling an overloaded version of [`getDisplayName()`](https://developer.android.com/reference/android/icu/util/TimeZone#getDisplayName(boolean,%20int)) on the `TimeZone` object for the current locale, where the given style is `SHORT`. Returns an abbreviated time zone name, such as "EST" for New York City's time zone during the winter. */
        val TIMEZONE_ABB = Exp.String { "[TIMEZONE_ABB]" }

        /** A string that is equivalent to calling [`getId()`](https://developer.android.com/reference/android/icu/util/TimeZone#getID()) on the `TimeZone` object for the current locale. Returns an [IANA time zone ID value](https://en.wikipedia.org/wiki/List_of_tz_database_time_zones), such as `America/New_York` for New York City's time zone. */
        val TIMEZONE_ID = Exp.String { "[TIMEZONE_ID]" }

        /** A string that is equivalent to calling [`getRawOffset()`](https://developer.android.com/reference/android/icu/util/TimeZone#getRawOffset()) on the `TimeZone` object for the current locale. This value is always between `-12` and `+14`, inclusive. Returns the value that should be added to UTC to get the standard time for the current time zone (not corrected for daylight saving time). */
        val TIMEZONE_OFFSET = Exp.String { "[TIMEZONE_OFFSET]" }

        /** A string that is equivalent to passing the current date into [`getOffset()`](https://developer.android.com/reference/android/icu/util/TimeZone#getOffset(long)) on the `TimeZone` object for the current locale. This value is always between `-12` and `+14`, inclusive. Returns the value that should be added to UTC to get the current local time, with daylight saving time taken into account if necessary. */
        val TIMEZONE_OFFSET_DST = Exp.String { "[TIMEZONE_OFFSET_DST]" }

        /** An integer that indicates whether the current time is before noon, or is noon or later. Possible values include `0` for AM and `1` for PM. */
        val AMPM_STATE = Exp.Int { "[AMPM_STATE]" }

        /**
         * An integer that indicates where the AM or PM indicator should appear within a time string. Contains one of the following values:
         *
         * *   `0` means that AM or PM should appear at the beginning of the string, such as `AM 12:03`.
         * *   `1` means that AM or PM should appear at the end of the string, such as `12:03 AM`.
         * *   `-1` means that it's unknown where AM or PM should appear in the string.
         */
        val AMPM_POSITION = Exp.Int { "[AMPM_POSITION]" }

        /** A 2-character string that indicates the AM or PM status of the current time. The value is either "AM" or "PM". */
        val AMPM_STRING = Exp.String { "[AMPM_STRING]" }
    }

    /**
     * ## Moon phase
     *
     * The Watch Face Format supports data sources related to the moon phase.
     */
    object MoonPhase {
        /** An integer value indicating the number of days that have started since the most recent new moon. This value is always between `0` and `28`, inclusive. */
        val MOON_PHASE_POSITION = Exp.Int { "[MOON_PHASE_POSITION]" }

        /**
         * An integer that encodes the current moon phase. Possible values include the following:
         *
         * *   `0` for the most recent new moon
         * *   `1` for an evening crescent moon
         * *   `2` for a first-quarter moon
         * *   `3` for a waxing gibbous moon
         * *   `4` for a full moon
         * *   `5` for a waning gibbous moon
         * *   `6` for a last-quarter moon
         * *   `7` for a morning crescent moon
         */
        val MOON_PHASE_TYPE = Exp.Int { "[MOON_PHASE_TYPE]" }

        /**
         * A string that represents the current moon phase. Possible values include the following:
         *
         * *   **New Moon**, when less than 0.5 day has elapsed, or more than 27.5 days have elapsed, since the most recent new moon.
         * *   **Evening Crescent**, when at least 0.5 day but less than 6.5 days have elapsed since the most recent new moon.
         * *   **First Quarter**, when at least 6.5 days but less than 7.5 days have elapsed since the most recent new moon.
         * *   **Waxing Gibbous**, when at least 7.5 days but less than 13.5 days have elapsed since the most recent new moon.
         * *   **Full Moon**, when at least 13.5 days but less than 14.5 days have elapsed since the most recent new moon.
         * *   **Waning Gibbous**, when at least 14.5 days but less than 20.5 days have elapsed since the most recent new moon.
         * *   **Last Quarter**, when at least 20.5 days but less than 21.5 days have elapsed since the most recent new moon.
         * *   **Morning Crescent**, when at least 21.5 days but less than 27.5 days have elapsed since the most recent new moon.
         */
        val MOON_PHASE_TYPE_STRING = Exp.String { "[MOON_PHASE_TYPE_STRING]" }
    }

    /**
     * ## Language
     *
     * The Watch Face Format supports data sources related to the user's language.
     */
    object Language {
        /** A string that represents the Wear OS device's current locale, expressed as a 2-character [ISO 639-1 language code](https://en.wikipedia.org/wiki/ISO_639-1), an underscore, and a 2-character [ISO 3166-1 region code](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2). The United States variant of English is represented as `en_US`. */
        val LANGUAGE_LOCALE_NAME = Exp.String { "[LANGUAGE_LOCALE_NAME]" }
    }

    /**
     * ## Health data
     *
     * The Watch Face Format supports data sources related to the user's health data.
     */
    object HealthData {
        /** The number of steps that the user has taken so far today, according to the Wear OS device sensors. */
        val STEP_COUNT = Exp.Int { "[STEP_COUNT]" }

        /** The number of steps that the user has selected as their daily step goal. This value is never less than `1000`. */
        val STEP_GOAL = Exp.Int { "[STEP_GOAL]" }

        /** An integer indicating the progress that the user has made toward their step goal today, expressed as a rounded percentage. This value is always between `0` and `100`, inclusive. */
        val STEP_PERCENT = Exp.Int { "[STEP_PERCENT]" }

        /** The user's current heart rate, according to the Wear OS device sensors. This value is always between `0` and `240`, inclusive. */
        val HEART_RATE = Exp.Int { "[HEART_RATE]" }

        /** A string that represents the user's current heart rate, according to the Wear OS device sensors. This value is padded with zeros on the left as needed to make the value at least 2 characters long. So if the user's heart rate is measured as 65, this value is `65`. If the user's heart rate is measured as 0, this value is `00`. */
        val HEART_RATE_Z = Exp.String { "[HEART_RATE_Z]" }
    }

    /**
     * ## Device sensors
     *
     * The Watch Face Format supports data sources related to the device's sensors.
     */
    object Sensors {
        /** Boolean value indicating whether the watch face can obtain accelerometer data from the current Wear OS device's sensors. */
        val ACCELEROMETER_IS_SUPPORTED = Exp.Boolean { "[ACCELEROMETER_IS_SUPPORTED]" }

        /** A floating-point value that indicates the current linear acceleration along the x-axis, according to the Wear OS device's accelerometer. Positive values indicate that, when the watch face is pointing at the sky or ceiling, the Wear OS device is accelerating to the right. */
        val ACCELEROMETER_X = Exp.Float { "[ACCELEROMETER_X]" }

        /** A floating-point value that indicates the current linear acceleration along the y-axis, according to the Wear OS device's accelerometer. Positive values indicate that, when the watch face is pointing at the sky or ceiling, the Wear OS device is accelerating in the 12 o'clock (top) direction along the watch face. */
        val ACCELEROMETER_Y = Exp.Float { "[ACCELEROMETER_Y]" }

        /**
         * A floating-point value that indicates the current linear acceleration along the z-axis, according to the Wear OS device's accelerometer. Positive values indicate that, when the watch face is pointing at the sky or ceiling, the Wear OS device is accelerating toward the sky or ceiling.
         *
         * **NOTE:** When the device is at rest, this value is approximately `-9.8` to take gravity into account.
         */
        val ACCELEROMETER_Z = Exp.Float { "[ACCELEROMETER_Z]" }

        /** A floating-point value that indicates the current angular acceleration, in degrees, relative to the x-axis. This value is always in the range [[−90.0, 90.0]]. */
        val ACCELEROMETER_ANGLE_X = Exp.Float { "[ACCELEROMETER_ANGLE_X]" }

        /** A floating-point value that indicates the current angular acceleration, in degrees, relative to the y-axis. This value is always in the range [[−90.0,90.0]]. */
        val ACCELEROMETER_ANGLE_Y = Exp.Float { "[ACCELEROMETER_ANGLE_Y]" }

        /** A floating-point value that indicates the current angular acceleration, in degrees, relative to the z-axis. This value is always in the range [[−90.0,90.0]]. */
        val ACCELEROMETER_ANGLE_Z = Exp.Float { "[ACCELEROMETER_ANGLE_Z]" }

        /** A floating-point value that is the sum of `ACCELEROMETER_ANGLE_X` and `ACCELEROMETER_ANGLE_Y`. */
        val ACCELEROMETER_ANGLE_XY = Exp.Float { "[ACCELEROMETER_ANGLE_XY]" }
    }

    //TODO: Add Battery
    //TODO: Add Notifications
}
