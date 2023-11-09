package splitties.wff.group.part.draw.gradient

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.*
import splitties.wff.attr.AttrsHost
import splitties.wff.internal.xmlValue

/**
 * A gradient transitioning between two colors between a start and end angle.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/gradient/sweep-gradient)
 *
 * ## Inner elements
 *
 * The SweepGradient element can contain between 0 and 4 `Transform` elements.
 *
 * @param startAngle The angle (in degrees) to start the gradient.
 * @param endAngle The angle (in degrees) to end the gradient.
 */
@ExperimentalUnsignedTypes
@WffTagMarker
inline fun SupportsGradients.sweepGradient(
    centerX: Float,
    centerY: Float,
    startAngle: Float,
    endAngle: Float,
    colors: List<Color>,
    positions: FloatArray? = null,
    direction: Direction = Direction.CLOCKWISE,
    crossinline block: SWEEPGRADIENT.() -> Unit = {}
): Unit = SWEEPGRADIENT(
    initialAttributes = attributesMapOf(
        "centerX", centerX.toString(),
        "centerY", centerY.toString(),
        "startAngle", startAngle.toString(),
        "endAngle", endAngle.toString(),
        "colors", colors.xmlValue(),
        "positions", positions.asGradientPositions(colors),
        "direction", direction.xmlValue()
    ),
    consumer = consumer
).visit(block)

class SWEEPGRADIENT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "SweepGradient",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), Transformable {
    override val attrs = Attrs()

    class Attrs internal constructor(): AttrsHost() {
        val centerX by float()
        val centerY by float()
        val startAngle by float()
        val endAngle by float()
    }
}
