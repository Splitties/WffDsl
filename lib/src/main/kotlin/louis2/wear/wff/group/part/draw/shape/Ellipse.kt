package louis2.wear.wff.group.part.draw.shape

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.group.part.draw.PARTDRAW

/**
 * Draws an Ellipse shape.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/shape/ellipse)
 */
@WffTagMarker
inline fun PARTDRAW.ellipse(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    crossinline block: ELLIPSE.() -> Unit = {}
): Unit = ELLIPSE(
    initialAttributes = attributesMapOf(
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
    ),
    consumer = consumer
).visit(block)

class ELLIPSE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Ellipse",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), StrokeAble, FillAble, Transformable
