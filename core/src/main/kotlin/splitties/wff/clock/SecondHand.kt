package splitties.wff.clock

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.AnalogHand
import splitties.wff.Color
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag
import splitties.wff.attr.AnalogHandAttrs

/**
 * A second hand is a clock hand that rotates around a watch face once every minute.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/clock/second-hand)
 */
@WffTagMarker
inline fun ANALOGCLOCK.secondHand(
    resource: String,
    x: Int = 0,
    y: Int = 0,
    width: Int = this.width,
    height: Int = this.height,
    pivotX: Float = .5f,
    pivotY: Float = .5f,
    alpha: UByte = 0xFFu,
    tintColor: Color? = null,
    crossinline block: SECONDHAND.() -> Unit = {}
): Unit = SECONDHAND(
    initialAttributes = attributesMapOf(
        "resource", resource,
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "pivotX", pivotX.toString(),
        "pivotY", pivotY.toString(),
        "alpha", alpha.takeUnless { it == 0xFFu.toUByte() }?.toString(),
        "tintColor", tintColor?.xmlValue(),
    ),
    consumer = consumer
).visit(block)

class SECONDHAND(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "SecondHand",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), AnalogHand {
    override val attrs = AnalogHandAttrs()
}
