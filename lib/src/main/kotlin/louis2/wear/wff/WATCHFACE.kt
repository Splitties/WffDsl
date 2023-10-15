package louis2.wear.wff

import kotlinx.html.*

/**
 * The root element of every watchface.xml file.
 * It contains information about the elements that should appear in
 * the watch face preview when users choose which watch face to use on their Wear OS devices.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/watch-face)
 */
@WffTagMarker
inline fun <T, C : TagConsumer<T>> C.watchFace(
    width: Int,
    height: Int,
    clipShape: WATCHFACE.ClipShape = WATCHFACE.ClipShape.CIRCLE,
    cornerRadiusX: Float? = null,
    cornerRadiusY: Float? = null,
    crossinline block: WATCHFACE.() -> Unit
) : T = WATCHFACE(
    initialAttributes = attributesMapOf(
        "width",
        width.toString(),
        "height",
        height.toString(),
        "clipShape",
        clipShape.name,
        "cornerRadiusX",
        cornerRadiusX?.toString(),
        "cornerRadiusY",
        cornerRadiusY?.toString()
    ),
    consumer = this,
).visitAndFinalize(this, block)

class WATCHFACE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "WatchFace",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
) {
    enum class ClipShape {
        NONE, CIRCLE, RECTANGLE
    }
}
