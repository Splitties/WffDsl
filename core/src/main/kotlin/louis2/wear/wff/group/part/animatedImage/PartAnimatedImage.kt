package louis2.wear.wff.group.part.animatedImage

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.attr.ContainerAttrs

/**
 * A `PartAnimatedImage` contains an animated element that appears on the watch face. The `PartAnimatedImage` determines where on the watch face the animated element appears, and the [inner elements](https://developer.android.com/training/wearables/wff/group/part/animated-image/part-animated-image#inner-elements) determine the specific animated resource which plays.
 *
 * **Caution:** If you don't specify a `Thumbnail` in this `PartAnimatedImage` element,
 * the watch face instead loads the first frame of a sibling `AnimatedImage` element,
 * represented by that element's `thumbnail` attribute. This can be very slow.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/animated-image/part-animated-image)
 */
@WffTagMarker
inline fun SupportsPart.partAnimatedImage(
    x: Int = 0,
    y: Int = 0,
    width: Int = this.width,
    height: Int = this.height,
    pivotX: Float = .5f,
    pivotY: Float = .5f,
    angle: Float = 0f,
    alpha: UByte = 0xFFu,
    name: String? = null,
    scaleX: Float = 1f,
    scaleY: Float = 1f,
    renderMode: RenderMode = RenderMode.SOURCE,
    tintColor: Color? = null,
    crossinline block: PARTANIMATEDIMAGE.() -> Unit = {}
): Unit = PARTANIMATEDIMAGE(
    initialAttributes = attributesMapOf(
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "pivotX", pivotX.takeUnless { angle == 0f }?.toString(),
        "pivotY", pivotY.takeUnless { angle == 0f }?.toString(),
        "angle", angle.takeUnless { it == 0f }?.toString(),
        "alpha", alpha.takeUnless { it == 0xFFu.toUByte() }?.toString(),
        "name", name,
        "scaleX", scaleX.takeUnless { it == 1f }?.toString(),
        "scaleY", scaleY.takeUnless { it == 1f }?.toString(),
        "renderMode", renderMode.xmlValue(),
        "tintColor", tintColor?.xmlValue(),
    ),
    consumer = consumer
).visit(block)

class PARTANIMATEDIMAGE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "PartAnimatedImage",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), Part, SupportsAnimatedImage {
    override val attrs = ContainerAttrs()
}
