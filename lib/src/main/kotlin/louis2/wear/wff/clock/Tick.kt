package louis2.wear.wff.clock

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * Allows watch face creators to define the behavior of SecondHand elements, such that the seconds increase in discrete ticks.
 *
 * Introduced in Wear OS 4.
 *
 * @param duration The duration of the tick, for a given second, in the range `(0.0-1.0)`. For example, a value of `0.2`, would mean the movement would take place over the first 200ms, then no movement would occur in the remaining 800ms.
 * @param strength The strength of the tick movement, should be in the range `(0.0-1.0]`.
 */
@WffTagMarker
fun SECONDHAND.tick(
    duration: Float,
    strength: Float
): Unit = TICK(
    initialAttributes = attributesMapOf(
        "duration", duration.also { require(it > 0f && it < 1f) }.toString(),
        "strength", strength.also { require(it > 0f && it <= 1f) }.toString(),
    ),
    consumer = consumer
).visit {}

class TICK(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Tick",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
