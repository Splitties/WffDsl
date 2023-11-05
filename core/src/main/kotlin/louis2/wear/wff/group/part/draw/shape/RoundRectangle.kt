package louis2.wear.wff.group.part.draw.shape

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.attr.AttrsHost
import louis2.wear.wff.group.part.draw.PARTDRAW

/**
 * Draws a Rectangle with rounded corners.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/shape/round-rectangle)
 */
@WffTagMarker
inline fun PARTDRAW.roundRectangle(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    cornerRadiusX: Float,
    cornerRadiusY: Float,
    crossinline block: ROUNDRECTANGLE.() -> Unit
): Unit = ROUNDRECTANGLE(
    initialAttributes = attributesMapOf(
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "cornerRadiusX", cornerRadiusX.toString(),
        "cornerRadiusY", cornerRadiusY.toString(),
    ),
    consumer = consumer
).visit(block)

class ROUNDRECTANGLE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "RoundRectangle",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), StrokeAble, FillAble, Transformable {
    override val attrs = Attrs()

    class Attrs internal constructor(): AttrsHost() {
        val x by float()
        val y by float()
        val width by float()
        val height by float()
        val cornerRadiusX by float()
        val cornerRadiusY by float()
    }
}
