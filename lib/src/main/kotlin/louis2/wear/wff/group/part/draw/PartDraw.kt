package louis2.wear.wff.group.part.draw

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.h
import louis2.wear.wff.w

/**
 * A PartDraw contains a vector drawing primitives that appears on the watch face.
 * The PartDraw determines the area of the watch face the vectors are drawn,
 * and the inner elements determine the specifics of the vector drawing.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/part-draw)
 */
@WffTagMarker
inline fun SupportsPart.partDraw(
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
    crossinline block: PARTDRAW.() -> Unit = {}
): Unit = PARTDRAW(
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

class PARTDRAW(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "PartText",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), Part, Container {
    override val width: Int get() = w()
    override val height: Int get() = h()
}
