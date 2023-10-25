package louis2.wear.wff.clock

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.internal.asArgbColor
import louis2.wear.wff.internal.h
import louis2.wear.wff.internal.w

/**
 * An analog clock is a container for AnalogHands inner elements, which display a series of clock hands that rotate around a watch face.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/clock/analog-clock)
 */
@WffTagMarker
inline fun SupportsClock.analogClock(
    x: Int = 0,
    y: Int = 0,
    width: Int = this.width,
    height: Int = this.height,
    pivotX: Float = .5f,
    pivotY: Float = .5f,
    angle: Float = 0f,
    alpha: UByte = 0xFFu,
    scaleX: Float = 1f,
    scaleY: Float = 1f,
    renderMode: RenderMode = RenderMode.SOURCE,
    tintColor: UInt? = null,
    crossinline block: ANALOGCLOCK.() -> Unit
): Unit = ANALOGCLOCK(
    initialAttributes = attributesMapOf(
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "pivotX", pivotX.takeUnless { angle == 0f }?.toString(),
        "pivotY", pivotY.takeUnless { angle == 0f }?.toString(),
        "angle", angle.takeUnless { it == 0f }?.toString(),
        "alpha", alpha.takeUnless { it == 0xFFu.toUByte() }?.toString(),
        "scaleX", scaleX.takeUnless { it == 1f }?.toString(),
        "scaleY", scaleY.takeUnless { it == 1f }?.toString(),
        "renderMode", renderMode.xmlValue(),
        "tintColor", tintColor?.asArgbColor(),
    ),
    consumer = consumer
).visit(block)

class ANALOGCLOCK(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "AnalogClock",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsLocalization, SupportsVariants, Container {
    override val width: Int get() = w()
    override val height: Int get() = h()
}
