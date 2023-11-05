package louis2.wear.wff.group.part.animatedImage

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.SupportsAnimatedImage
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * Element for drawing animated images (like GIF).
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/animated-image/animated-image)
 *
 * @param thumbnail A resource ID referring to a representative thumbnail for the animation. This can be used to render in place of the `AnimatedImage` where animation is not supported (for example, in previews in the watch face carousel).
 */
@WffTagMarker
fun SupportsAnimatedImage.animatedImage(
    resource: String,
    format: ANIMATEDIMAGE.Format,
    thumbnail: String? = null
): Unit = ANIMATEDIMAGE(
    initialAttributes = attributesMapOf(
        "resource", resource,
        "format", format.xmlValue(),
        "thumbnail", thumbnail
    ),
    consumer = consumer
).visit {}

class ANIMATEDIMAGE(
    initialAttributes: Map<String, String>,
    consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "AnimatedImage",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
) {
    enum class Format {
        IMAGE,
        AGIF,
        WEBP;

        fun xmlValue() = name
    }
}
