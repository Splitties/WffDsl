package louis2.wear.wff.complication

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.group.part.text.formatter.parameter

/**
 * A Complication element defines how a particular Complication Type is displayed on the watch face.
 *
 * An inner Parameter element can contain an expression that's based on the data from a parent ComplicationSlot element.
 * Valid expressions include [ComplicationType].
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/complication/complication)
 */
@WffTagMarker
inline fun COMPLICATIONSLOT.complication(
    type: ComplicationType,
    crossinline block: COMPLICATION.() -> Unit = {}
): Unit = COMPLICATION(
    initialAttributes = attributesMapOf(
        "type", type.xmlValue(),
    ),
    parentContainer = this,
    consumer = consumer,
).visit(block)

class COMPLICATION(
    initialAttributes: Map<String, String>,
    private val parentContainer: Container,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "Complication",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsGroup, SupportsPart, SupportsConditions {
    override val width: Int get() = parentContainer.width
    override val height: Int get() = parentContainer.height

    companion object {
        /** For use in Template's [parameter] */
        const val TEXT = "[COMPLICATION.TEXT]"
        /** For use in Template's [parameter] */
        const val TITLE = "[COMPLICATION.TITLE]"
        /** For use in Template's [parameter] */
        const val ICON = "[COMPLICATION.ICON]"
    }
}
