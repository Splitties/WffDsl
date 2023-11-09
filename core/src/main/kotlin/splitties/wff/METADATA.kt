package splitties.wff

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit

/**
 * Represents a predefined or user-defined key-value pair.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/metadata)
 */
@WffTagMarker
fun WATCHFACE.metadata(
    key: String,
    value: String
) : Unit = METADATA(
    initialAttributes = attributesMapOf("key", key, "value", value),
    consumer = consumer
).visit {}

/**
 * Sets the time that appears in the watch face preview. The value must be in HH:MM:SS format; for example, 22:10:00 represents 10:10 PM.
 *
 * If this value is invalid or unspecified, the watch face uses the system's default time.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/metadata)
 */
fun WATCHFACE.previewTime(format: String) = metadata(
    key = "PREVIEW_TIME",
    value = format.also {
        require(format.length == 8)
        format.forEachIndexed { index, c ->
            when (index) {
                2, 5 -> require(c == ':')
                else -> require(c.isDigit())
            }
        }
    }
)

/**
 * Specifies the primary watch face type, either DIGITAL or ANALOG. Even if your watch face supports both types, you must specify a primary type to appear in the watch face preview.
 *
 * If this value is invalid or unspecified, the system uses a default value of ANALOG.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/metadata)
 */
fun WATCHFACE.clockType(type: ClockType = ClockType.ANALOG) = metadata(
    key = "CLOCK_TYPE",
    value = type.name
)

/**
 * Sets the daily "number of steps" goal that appears in the watch face preview. This value must be a positive integer.
 *
 * If this value is invalid or unspecified, the watch face uses the system's default daily step goal.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/metadata)
 */
fun WATCHFACE.stepGoal(goal: Int) = metadata(
    key = "STEP_GOAL",
    value = goal.toString()
)

enum class ClockType {
    DIGITAL, ANALOG
}

class METADATA(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Metadata",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
