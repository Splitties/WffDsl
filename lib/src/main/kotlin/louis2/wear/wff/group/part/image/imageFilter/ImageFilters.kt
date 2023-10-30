package louis2.wear.wff.group.part.image.imageFilter

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.SupportsImage
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag
import louis2.wear.wff.group.part.image.PARTIMAGE

/**
 * Contains the set of image filters to apply to a pixel or element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/image/image-filter/image-filters)
 */
@WffTagMarker
inline fun PARTIMAGE.imageFilters(
    crossinline block: IMAGEFILTERS.() -> Unit
): Unit = IMAGEFILTERS(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

class IMAGEFILTERS(
    initialAttributes: Map<String, String>,
    consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "ImageFilters",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
)
