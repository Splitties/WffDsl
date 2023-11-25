package splitties.wff.group.part.text

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.*
import splitties.wff.attr.AttrsHost

/**
 * Specifies a circular or curved text configuration.
 *
 * Introduced in Wear OS 4.
 *
 * @param startAngle An angle of 0 degrees represents the 12 o'clock position on the watch face.
 * @param endAngle An angle of 0 degrees represents the 12 o'clock position on the watch face.
 * @param align The default value is CENTER.
 * @param ellipsis A boolean value that indicates whether to show an ellipsis if the text is too long to fit in the parent element. The default is FALSE.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/text-circular)
 */
@WffTagMarker
inline fun PARTTEXT.textCircular(
    centerX: Float,
    centerY: Float,
    width: Float,
    height: Float,
    startAngle: Float,
    endAngle: Float,
    direction: Direction = Direction.CLOCKWISE,
    align: Alignment = Alignment.CENTER,
    ellipsis: Boolean = false,
    crossinline block: TEXTCIRCULAR.() -> Unit = {}
): Unit = TEXTCIRCULAR(
    initialAttributes = attributesMapOf(
        "centerX", centerX.toString(),
        "centerY", centerY.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "startAngle", startAngle.toString(),
        "endAngle", endAngle.toString(),
        "direction", direction.xmlValue(),
        "align", align.xmlValue(Alignment.CENTER),
        "ellipsis", ellipsis.takeIf { it }?.toString()?.uppercase(),
    ),
    consumer = consumer
).visit(block)

class TEXTCIRCULAR(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "TextCircular",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), TextScope, Transformable {
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
