package splitties.wff.group.part.animatedImage

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.SupportsAnimatedImage
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag
import splitties.wff.common.EventTrigger

/**
 * A container element containing many `AnimatedImage` elements.
 * When an action occurs, the system switches to the next inner element.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/animated-image/animated-images)
 */
@WffTagMarker
inline fun SupportsAnimatedImage.animatedImages(
    change: List<EventTrigger> = listOf(EventTrigger.TAP),
    crossinline block: ANIMATEDIMAGES.() -> Unit,
): Unit = ANIMATEDIMAGES(
    initialAttributes = attributesMapOf(
        "change",
        change.takeUnless {
            it == listOf(EventTrigger.TAP)
        }?.joinToString(separator = " ") { it.xmlValue() }
    ),
    consumer = consumer
).visit(block)

class ANIMATEDIMAGES(
    initialAttributes: Map<String, String>,
    consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "AnimatedImages",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsAnimatedImage
