package splitties.wff.group.part.draw.gradient

import kotlinx.html.*
import splitties.wff.*
import splitties.wff.attr.AttrsHost
import splitties.wff.internal.visit
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
@WffTagMarker
fun SupportsGradients.sweepGradient(
    centerX: Float,
    centerY: Float,
    startAngle: Float,
    endAngle: Float,
    colors: List<Color.Static>,
    positions: FloatArray? = null,
    direction: Direction = Direction.CLOCKWISE,
    block: (SWEEPGRADIENT.() -> Unit)? = null
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
    emptyTag = block == null,
    consumer = consumer
).visit(block)

class SWEEPGRADIENT(
    initialAttributes: Map<String, String>,
    emptyTag: Boolean,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "SweepGradient",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = emptyTag
), Transformable {
    override val attrs = Attrs()

    class Attrs internal constructor(): AttrsHost() {
        val centerX by float()
        val centerY by float()
        val startAngle by float()
        val endAngle by float()
    }
}
