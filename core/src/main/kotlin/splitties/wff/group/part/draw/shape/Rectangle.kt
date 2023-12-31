package splitties.wff.group.part.draw.shape

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.*
import splitties.wff.attr.AttrsHost
import splitties.wff.group.part.draw.PARTDRAW

/**
 * Draws a Rectangle shape.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/shape/rectangle)
 */
@WffTagMarker
inline fun PARTDRAW.rectangle(
    x: Float = 0f,
    y: Float = 0f,
    width: Float = this.width.toFloat(),
    height: Float = this.height.toFloat(),
    crossinline block: RECTANGLE.() -> Unit
): Unit = RECTANGLE(
    initialAttributes = attributesMapOf(
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
    ),
    consumer = consumer
).visit(block)

class RECTANGLE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Rectangle",
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
    }
}
