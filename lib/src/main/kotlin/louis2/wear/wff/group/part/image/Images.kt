package louis2.wear.wff.group.part.image

import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.SupportsImage
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag
import louis2.wear.wff.group.part.image.IMAGES.Change

/**
 * A container for a set of related image resources. Each time the defined trigger occurs, a new image appears.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/image/images)
 */
@WffTagMarker
inline fun PARTIMAGE.images(
        change: Change? = null,
        crossinline block: IMAGES.() -> Unit
): Unit = IMAGES(
        initialAttributes = attributesMapOf(
                "change", change?.xmlValue(Change.TAP),
        ),
        consumer = consumer
).visit(block)

class IMAGES(
        initialAttributes: Map<String, String>,
        consumer: TagConsumer<*>,
) : XMLTag(
        tagName = "Images",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
), Tag, SupportsImage {
    enum class Change {
        TAP, ON_VISIBLE, ON_NEXT_SECOND, ON_NEXT_MINUTE, ON_NEXT_HOUR;

        fun xmlValue(default: Change): String? = when (this) {
            default -> null
            else -> name
        }
    }
}