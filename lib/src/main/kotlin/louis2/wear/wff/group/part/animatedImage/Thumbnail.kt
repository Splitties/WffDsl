package louis2.wear.wff.group.part.animatedImage

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.attr.ContainerAttrs
import louis2.wear.wff.common.EventTrigger

/**
 * Represents a static image that appears on a watch face. This image represents the first frame of the animation for a parent `PartAnimatedImage` element.
 *
 * **Caution:** If you don't specify this `Thumbnail` element for a parent `PartAnimatedImage` element,
 * the watch face instead loads the first frame of a sibling `AnimatedImage` element,
 * represented by that element's `thumbnail` attribute. This can be very slow.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/animated-image/thumbnail)
 */
@WffTagMarker
fun PARTANIMATEDIMAGE.thumbnail(
    resource: String
): Unit = THUMBNAIL(
    initialAttributes = attributesMapOf("resource", resource),
    consumer = consumer
).visit {}

class THUMBNAIL(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Thumbnail",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
