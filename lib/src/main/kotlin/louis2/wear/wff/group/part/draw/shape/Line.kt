package louis2.wear.wff.group.part.draw.shape

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.group.part.draw.PARTDRAW

/**
 * Draws a Line shape.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/shape/line)
 */
@WffTagMarker
inline fun PARTDRAW.arc(
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
), StrokeAble, Transformable