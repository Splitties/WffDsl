package splitties.wff.group.part.draw.shape

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.*
import splitties.wff.attr.AttrsHost
import splitties.wff.group.part.draw.PARTDRAW

/**
 * Draws a Line shape.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/shape/line)
 */
@WffTagMarker
inline fun PARTDRAW.line(
    startX: Float,
    startY: Float,
    endX: Float,
    endY: Float,
    crossinline block: LINE.() -> Unit
): Unit = LINE(
    initialAttributes = attributesMapOf(
        "startX", startX.toString(),
        "startY", startY.toString(),
        "endX", endX.toString(),
        "endY", endY.toString(),
    ),
    consumer = consumer
).visit(block)

class LINE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Line",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), StrokeAble, Transformable {
    override val attrs = Attrs()

    class Attrs internal constructor(): AttrsHost() {
        val startX by float()
        val startY by float()
        val endX by float()
        val endY by float()
    }
}
