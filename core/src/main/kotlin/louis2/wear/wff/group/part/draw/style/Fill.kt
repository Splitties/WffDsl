package louis2.wear.wff.group.part.draw.style

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*

/**
 * A fill sets the visual style of the inside of a shape.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/style/fill)
 */
@WffTagMarker
inline fun FillAble.fill(
    crossinline block: FILL.() -> Unit
): Unit = FILL(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

/**
 * A fill sets the visual style of the inside of a shape.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/style/fill)
 */
@WffTagMarker
fun FillAble.fill(
    color: Color,
): Unit = FILL(
    initialAttributes = attributesMapOf(
        "color", color.xmlValue()
    ),
    consumer = consumer
).visit {}

class FILL(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Fill",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsGradients
