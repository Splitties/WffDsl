package splitties.wff.group.part.draw.shape

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.*
import splitties.wff.attr.AttrsHost
import splitties.wff.group.part.draw.PARTDRAW

/**
 * Draws an Arc shape.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/shape/arc)
 *
 * @param startAngle The angle (in degrees) where the arc begins. An angle of 0 degrees corresponds to the 12 o'clock position on the watch.
 * @param endAngle The angle (in degrees) where the arc ends. An angle of 0 degrees corresponds to the 12 o'clock position on the watch.
 */
@WffTagMarker
inline fun PARTDRAW.arc(
    centerX: Float = this.centerX,
    centerY: Float = this.centerY,
    width: Float,
    height: Float,
    startAngle: Float,
    endAngle: Float,
    direction: Direction = Direction.CLOCKWISE,
    crossinline block: ARC.() -> Unit
): Unit = ARC(
    initialAttributes = attributesMapOf(
        "centerX", centerX.toString(),
        "centerY", centerY.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "startAngle", startAngle.toString(),
        "endAngle", endAngle.toString(),
        "direction", direction.xmlValue(),
    ),
    consumer = consumer
).visit(block)

class ARC(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Arc",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), StrokeAble, Transformable {
    val width: Float get() = attributes.getValue("width").toFloat()
    val height: Float get() = attributes.getValue("height").toFloat()

    override val attrs = Attrs()

    class Attrs internal constructor(): AttrsHost() {
        val centerX by float()
        val centerY by float()
        val width by float()
        val height by float()
        val startAngle by float()
        val endAngle by float()
    }
}
