package louis2.wear.wff.group.part.image

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.SupportsImage
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * Contains a reference to an image resource, for displaying in a watch face.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/image/image)
 */
@WffTagMarker
fun SupportsImage.image(
    resource: String,
): Unit = IMAGE(
    initialAttributes = attributesMapOf(
        "resource", resource,
    ),
    consumer = consumer
).visit {}

class IMAGE(
    initialAttributes: Map<String, String>,
    consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "Image",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
)
