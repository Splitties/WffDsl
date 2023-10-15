package louis2.wear.wff.clock

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*

/**
 * An hour hand is a clock hand that rotates around a watch face once every 12 hours.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/clock/hour-hand)
 */
@WffTagMarker
inline fun ANALOGCLOCK.hourHand(
    resource: String,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    pivotX: Float = .5f,
    pivotY: Float = .5f,
    alpha: UByte = 0xFFu,
    tintColor: UInt? = null,
    crossinline block: HOURHAND.() -> Unit = {}
): Unit = HOURHAND(
    initialAttributes = attributesMapOf(
        "resource", resource,
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "pivotX", pivotX.toString(),
        "pivotY", pivotY.toString(),
        "alpha", alpha.takeUnless { it == 0xFFu.toUByte() }?.toString(),
        "tintColor", tintColor?.let { "#${it.toString(radix = 16)}" },
    ),
    consumer = consumer
).visit(block)

class HOURHAND(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "HourHand",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), AnalogHand
