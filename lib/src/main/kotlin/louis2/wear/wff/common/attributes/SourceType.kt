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
    object Time {
        /**
         * UTC timestamp with milliseconds wouldn't fit into an int,
         * but this is not evaluated in Kotlin, and types are erased
         * before writing in the XML anyway.
         */
        val UTC_TIMESTAMP = Exp.Int { "[UTC_TIMESTAMP]" }

        /**
         * An integer value that represents the millisecond field of a ZonedDateTime object for the current time.
         * This value is always between 0 and 999, inclusive.
         */
        val MILLISECOND = Exp.Int { "[MILLISECOND]" }

        /**
         * An integer value that represents the second field of a ZonedDateTime object for the current time.
         * This value is always between 0 and 59, inclusive.
         */
        val SECOND = Exp.Int { "[SECOND]" }

        /**
         * A string that represents the second field of a ZonedDateTime object for the current time,
         * with leading zeros to make the value 2 characters long. This value is always between 00 and 59, inclusive.
         */
        val SECOND_Z = Exp.String { "[SECOND_Z]" }

        /**
         * A floating-point value that combines the second and millisecond fields of a ZonedDateTime object that
         * represents the current time. This value is always between 0.0 and 59.999, inclusive.
         */
        val SECOND_MILLISECOND = Exp.Float { "[SECOND_MILLISECOND]" }

        /**
         * The number of seconds that have elapsed during the current day, based on the values for HOUR_0_23,
         * MINUTE, and SECOND. This value is always between 0 and 86399 (24 * 60 * 60 - 1), inclusive.
         */
        val SECONDS_IN_DAY = Exp.Int { "[SECONDS_IN_DAY]" }

        /**
         * An integer value that represents the minute field of a ZonedDateTime object for the current time.
         * This value is always between 0 and 59, inclusive.
         */
        val MINUTE = Exp.Int { "[MINUTE]" }

        /**
         * A string value that represents the minute field of a ZonedDateTime object for the current time,
         * with leading zeros to make the value 2 characters long. This value is always between 00 and 59, inclusive.
         */
        val MINUTE_Z = Exp.String { "[MINUTE_Z]" }

        /**
         * A floating-point value that combines the minute and second fields of a ZonedDateTime object that
         * represents the current time. This value is always between 0.0 and 59+59/60, inclusive.
         */
        val MINUTE_SECOND = Exp.Float { "[MINUTE_SECOND]" }

        /**
         * The 12-hour component of the current time, represented as a value between 0 and 11 inclusive.
         * If the current CLOCK_HOUR_OF_AMPM is 12, such as in 12:34 PM, this value is converted to 0.
         */
        val HOUR_0_11 = Exp.Int { "[HOUR_0_11]" }

        /**
         * A string that represents the 12-hour component of the current time -- from 00 to 11 inclusive --
         * using the Wear OS device's current time zone. If this value is less than 10, it includes a leading zero.
         */
        val HOUR_0_11_Z = Exp.String { "[HOUR_0_11_Z]" }

        /**
         * A floating-point value the combines a modified CLOCK_HOUR_OF_AMPM field and minute field of a
         * ZonedDateTime object that represents the current time. This value is always between 0.0 and
         * 11+59/60, inclusive.
         */
        val HOUR_0_11_MINUTE = Exp.Float { "[HOUR_0_11_MINUTE]" }

        /**
         * The 12-hour component of the current time, represented as a value between 1 and 12 inclusive.
         * If the current HOUR_OF_DAY is 0, such as in 0:12 (12:12 AM), this value is converted to 12.
         */
        val HOUR_0_12 = Exp.Int { "[HOUR_0_12]" }

        /**
         * A string that represents the 12-hour component of the current time -- from 01 to 12 inclusive --
         * using the Wear OS device's current time zone. If this value is less than 10, it includes a leading zero.
         */
        val HOUR_0_12_Z = Exp.String { "[HOUR_0_12_Z]" }

        /**
         * A floating-point value the combines the CLOCK_HOUR_OF_AMPM and minute fields of a ZonedDateTime object that
         * represents the current time. This value is always between 1.0 and 12+59/60, inclusive.
         */
        val HOUR_0_12_MINUTE = Exp.Float { "[HOUR_0_12_MINUTE]" }

        /**
         * The 24-hour component of the current time, represented as a value between 0 and 23 inclusive.
         * If the value is 24, such as in 24:13 (12:13 AM the next day), this value is converted to 0.
         */
        val HOUR_0_23 = Exp.Int { "[HOUR_0_23]" }

        /**
         * A string that represents the 24-hour component of the current time -- from 00 to 23 inclusive --
         * using the Wear OS device's current time zone. If this value is less than 10, it includes a leading zero.
         */
        val HOUR_0_23_Z = Exp.String { "[HOUR_0_23_Z]" }

        /**
         * A floating-point value the combines the hour and minute fields of a ZonedDateTime object that
         * represents the current time. This value is always between 0.0 and 23+59/60, inclusive.
         */
        val HOUR_0_23_MINUTE = Exp.Float { "[HOUR_0_23_MINUTE]" }

        /**
         * The 24-hour component of the current time, represented as a value between 1 and 24 inclusive.
         * If the value is 0, such as in 0:12 (12:12 AM), this value is converted to 24.
         */
        val HOUR_1_24 = Exp.Int { "[HOUR_1_24]" }

        /**
         * A string that represents the 12-hour component of the current time -- from 01 to 24 inclusive --
         * using the Wear OS device's current time zone. If this value is less than 10, it includes a leading zero.
         */
        val HOUR_1_24_Z = Exp.String { "[HOUR_1_24_Z]" }

        /**
         * A floating-point value the combines a modified hour field and minute field of a ZonedDateTime object that
         * represents the current time. This value is always between 1.0 and 24+59/60, inclusive.
         */
        val HOUR_1_24_MINUTE = Exp.Float { "[HOUR_1_24_MINUTE]" }

        val DAY = Exp.Int { "[DAY]" }
        val DAY_Z = Exp.String { "[DAY_Z]" }
        val DAY_HOUR = Exp.Float { "[DAY_HOUR]" }
        val DAY_0_30 = Exp.Int { "[DAY_0_30]" }
        val DAY_0_30_HOUR = Exp.Float { "[DAY_0_30_HOUR]" }
        val DAY_OF_YEAR = Exp.Int { "[DAY_OF_YEAR]" }
        val DAY_OF_WEEK = Exp.Int { "[DAY_OF_WEEK]" }
        val DAY_OF_WEEK_F = Exp.String { "[DAY_OF_WEEK_F]" }
        val DAY_OF_WEEK_S = Exp.String { "[DAY_OF_WEEK_S]" }
        val MONTH = Exp.Int { "[MONTH]" }
        val MONTH_Z = Exp.String { "[MONTH_Z]" }
        val MONTH_F = Exp.String { "[MONTH_F]" }
        val MONTH_S = Exp.String { "[MONTH_S]" }
        val DAYS_IN_MONTH = Exp.Int { "[DAYS_IN_MONTH]" }
        val MONTH_DAY = Exp.Float { "[MONTH_DAY]" }
        val MONTH_0_11 = Exp.Int { "[MONTH_0_11]" }
        val MONTH_0_11_DAY = Exp.Float { "[MONTH_0_11_DAY]" }
        val YEAR = Exp.Int { "[YEAR]" }
        val YEAR_S = Exp.String { "[YEAR_S]" }
        val YEAR_MONTH = Exp.Float { "[YEAR_MONTH]" }
        val YEAR_MONTH_DAY = Exp.Float { "[YEAR_MONTH_DAY]" }
        val WEEK_IN_MONTH = Exp.Int { "[WEEK_IN_MONTH]" }
        val WEEK_IN_YEAR = Exp.Int { "[WEEK_IN_YEAR]" }
        val IS_24_HOUR_MODE = Exp.Boolean { "[IS_24_HOUR_MODE]" }
        val IS_DAYLIGHT_SAVING_TIME = Exp.Boolean { "[IS_DAYLIGHT_SAVING_TIME]" }
        val TIMEZONE = Exp.String { "[TIMEZONE]" }
        val TIMEZONE_ABB = Exp.String { "[TIMEZONE_ABB]" }
        val TIMEZONE_ID = Exp.String { "[TIMEZONE_ID]" }
        val TIMEZONE_OFFSET = Exp.String { "[TIMEZONE_OFFSET]" }
        val TIMEZONE_OFFSET_DST = Exp.String { "[TIMEZONE_OFFSET_DST]" }
        val AMPM_STATE = Exp.Int { "[AMPM_STATE]" }
        val AMPM_POSITION = Exp.Int { "[AMPM_POSITION]" }
        val AMPM_STRING = Exp.String { "[AMPM_STRING]" }
    }

    //TODO: Add MoonPhase
    //TODO: Add Language
    //TODO: Add HealthData

    object Sensors {
        val ACCELEROMETER_IS_SUPPORTED = Exp.Boolean { "[ACCELEROMETER_IS_SUPPORTED]" }

        //TODO: Add remaining ones
    }

    //TODO: Add Battery
    //TODO: Add Notifications
}
