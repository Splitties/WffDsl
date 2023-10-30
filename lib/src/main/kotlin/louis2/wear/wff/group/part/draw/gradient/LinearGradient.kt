package louis2.wear.wff.group.part.draw.gradient

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.internal.xmlValue

/**
 * A gradient transitioning between two colors along a line.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/gradient/linear-gradient)
 *
 * ## Inner elements
 *
 * The LinearGradient element can contain between 0 and 4 `Transform` elements.
 */
@ExperimentalUnsignedTypes
@WffTagMarker
inline fun SupportsGradients.linearGradient(
    startX: Float,
    startY: Float,
    endX: Float,
    endY: Float,
    colors: List<Color>,
    positions: FloatArray? = null,
    crossinline block: LINEARGRADIENT.() -> Unit = {}
): Unit = LINEARGRADIENT(
    initialAttributes = attributesMapOf(
        "startX", startX.toString(),
        "startY", startY.toString(),
        "endX", endX.toString(),
        "endY", endY.toString(),
        "colors", colors.xmlValue(),
        "positions", positions.asGradientPositions(colors)
    ),
    consumer = consumer
).visit(block)

class LINEARGRADIENT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "LinearGradient",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), Transformable
