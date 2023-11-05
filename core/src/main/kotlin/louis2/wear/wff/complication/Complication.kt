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
), SupportsGroup, SupportsPart, SupportsConditions, Container by parentContainer {

    companion object {
        /** For use in Template's [parameter] */
        const val TEXT = "[COMPLICATION.TEXT]"
        /** For use in Template's [parameter] */
        const val TITLE = "[COMPLICATION.TITLE]"

        /** For use in Image's resource */
        const val MONOCHROMATIC_IMAGE = "[COMPLICATION.MONOCHROMATIC_IMAGE]"
        /** For use in Image's resource */
        const val ICON = "[COMPLICATION.ICON]"
        /** For use in Image's resource */
        const val SMALL_IMAGE = "[COMPLICATION.SMALL_IMAGE]"

        /** For use in arithmetic expressions */
        const val RANGED_VALUE_VALUE = "[COMPLICATION.RANGED_VALUE_VALUE]"
        /** For use in arithmetic expressions */
        const val RANGED_VALUE_MIN = "[COMPLICATION.RANGED_VALUE_MIN]"
        /** For use in arithmetic expressions */
        const val RANGED_VALUE_MAX = "[COMPLICATION.RANGED_VALUE_MAX]"
    }
}
