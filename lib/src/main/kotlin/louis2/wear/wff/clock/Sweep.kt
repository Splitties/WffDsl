package louis2.wear.wff.clock

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * Allows watch face creators to define the behavior of SecondHand elements, such that the seconds increase in a smooth continuous motion.
 *
 * Introduced in Wear OS 4.
 *
 * @param frequency The frequency, in Hz, at which the hand is redrawn. Must be one of: 2, 5, 10, 15
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/clock/sweep)
 */
@WffTagMarker
fun SECONDHAND.sweep(frequency: Int): Unit = SWEEP(
    initialAttributes = attributesMapOf(
        key = "frequency",
        value = frequency.also {
            when (it) {
                2, 5, 10, 15 -> Unit
                else -> throw IllegalArgumentException("Needs to be 2, 5, 10, or 15 Hz")
            }
        }.toString()
    ),
    consumer = consumer
).visit {}

class SWEEP(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Sweep",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
