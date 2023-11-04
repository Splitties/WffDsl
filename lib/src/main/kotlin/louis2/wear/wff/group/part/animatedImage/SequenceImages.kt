package louis2.wear.wff.group.part.animatedImage

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.SupportsAnimatedImage
import louis2.wear.wff.SupportsImage
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag
import louis2.wear.wff.common.EventTrigger

/**
 * The `SequenceImages` tag creates an animation from a sequence of images.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/animated-image/sequence-image)
 */
@WffTagMarker
inline fun SupportsAnimatedImage.sequenceImages(
    loopCount: Int = 1,
    thumbnail: String? = null,
    crossinline block: SEQUENCEIMAGES.() -> Unit
): Unit = SEQUENCEIMAGES(
    initialAttributes = attributesMapOf(
        "loopCount", loopCount.takeUnless { it == 1 }?.toString(),
        "thumbnail", thumbnail
    ),
    consumer = consumer
).visit(block)

class SEQUENCEIMAGES(
    initialAttributes: Map<String, String>,
    consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "SequenceImages",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsImage
