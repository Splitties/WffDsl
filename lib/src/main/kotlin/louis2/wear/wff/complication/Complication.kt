package louis2.wear.wff.complication

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*

/**
 * A Complication element defines how a particular Complication Type is displayed on the watch face.
 *
 * An inner Parameter element can contain an expression that's based on the data from a parent ComplicationSlot element.
 * Valid expressions include [COMPLICATION.ComplicationType].
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/complication/complication)
 */
@WffTagMarker
inline fun COMPLICATIONSLOT.complication(
        type: COMPLICATION.ComplicationType,
        crossinline block: COMPLICATION.() -> Unit = {}
): Unit = COMPLICATION(
        initialAttributes = attributesMapOf(
                "type", type.xmlValue(),
        ),
        consumer = consumer,
        width = width,
        height = height,
).visit(block)

class COMPLICATION(
        initialAttributes: Map<String, String>,
        override val consumer: TagConsumer<*>,
        override val width: Int,
        override val height: Int
) : XMLTag(
        tagName = "Complication",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
), SupportsGroup, SupportsConditions, SupportsPart {
        enum class ComplicationType {
                NO_DATA,
                NO_PERMISSION,
                SHORT_TEXT,
                LONG_TEXT,
                RANGED_VALUE,
                MONOCHROMATIC_IMAGE,
                SMALL_IMAGE,
                PHOTO_IMAGE,
                GOAL_PROGRESS;

                fun xmlValue() = name
        }
}
