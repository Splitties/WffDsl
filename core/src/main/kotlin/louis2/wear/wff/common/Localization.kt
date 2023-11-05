package louis2.wear.wff.common

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.SupportsLocalization
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * Specifies the time zone and calendar format for a parent element whose value depends on the current date and time.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/localization)
 *
 * [IANA time zone identifier](https://en.wikipedia.org/wiki/List_of_tz_database_time_zones)
 *
 * [ICU-supported world calendar](https://developer.android.com/reference/android/icu/util/Calendar)
 *
 * @param timeZone An IANA time zone identifier, such as Asia/Seoul, Europe/London, or America/Los_Angeles.
 *
 * A value of SYNC_TO_DEVICE indicates that the expression should use the Wear OS device's current time zone.
 */
@WffTagMarker
fun SupportsLocalization.localization(
    timeZone: String,
    calendar: String = "GREGORIAN"
): Unit = LOCALIZATION(
    initialAttributes = attributesMapOf(
        "timeZone", timeZone,
        "calendar", calendar
    ),
    consumer = consumer
).visit {}

class LOCALIZATION(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "Localization",
    consumer = consumer,
    initialAttributes = initialAttributes,
    inlineTag = false,
    emptyTag = true
)
