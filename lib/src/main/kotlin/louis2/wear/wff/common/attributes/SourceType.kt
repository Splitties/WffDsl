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

        //TODO: Add remaining ones
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
