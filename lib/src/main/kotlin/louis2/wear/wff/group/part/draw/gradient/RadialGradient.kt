package louis2.wear.wff.group.part.draw.gradient

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.internal.xmlValue

/**
 * A gradient transitioning between two colors radiating from a central origin point.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/gradient/radial-gradient)
 *
 * ## Inner elements
 *
 * The RadialGradient element can contain between 0 and 4 `Transform` elements.
 */
@ExperimentalUnsignedTypes
@WffTagMarker
inline fun SupportsGradients.radialGradient(
    centerX: Float,
    centerY: Float,
    radius: Float,
    colors: List<Color>,
    positions: FloatArray? = null,
    crossinline block: RADIALGRADIENT.() -> Unit = {}
): Unit = RADIALGRADIENT(
    initialAttributes = attributesMapOf(
        "centerX", centerX.toString(),
        "centerY", centerY.toString(),
        "radius", radius.toString(),
        "colors", colors.xmlValue(),
        "positions", positions.asGradientPositions(colors),
    ),
    consumer = consumer
).visit(block)

class RADIALGRADIENT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "RadialGradient",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), Transformable
