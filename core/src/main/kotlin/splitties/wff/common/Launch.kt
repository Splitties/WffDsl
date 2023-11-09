package splitties.wff.common

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.SupportsLaunch
import splitties.wff.SupportsLocalization
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag

/**
 * When a parent part-based element such as `PartText` is tapped,
 * this element triggers the launch of an app or app component that's installed on the Wear OS device.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/launch)
 */
@WffTagMarker
fun SupportsLaunch.launch(
    target: String,
): Unit = LAUNCH(
    initialAttributes = attributesMapOf("target", target),
    consumer = consumer
).visit {}

class LAUNCH(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "Launch",
    consumer = consumer,
    initialAttributes = initialAttributes,
    inlineTag = false,
    emptyTag = true
) {
    companion object {
        const val ALARM = "ALARM"
        const val BATTERY_STATUS = "BATTERY_STATUS"
        const val CALENDAR = "CALENDAR"
        const val MESSAGE = "MESSAGE"
        const val MUSIC_PLAYER = "MUSIC_PLAYER"
        const val PHONE = "PHONE"
        const val SETTINGS = "SETTINGS"
    }
}
