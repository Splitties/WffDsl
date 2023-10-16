package louis2.wear.wff

import kotlinx.html.*
import louis2.wear.wff.internal.h
import louis2.wear.wff.internal.w

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
inline fun <T> WatchFaceDsl<T>.watchFace(
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
        clipShape.xmlValue(),
        "cornerRadiusX",
        cornerRadiusX?.toString(),
        "cornerRadiusY",
        cornerRadiusY?.toString()
    ),
    consumer = consumer,
).visitAndFinalize(consumer, block)

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
), Container {
    enum class ClipShape {
        NONE, CIRCLE, RECTANGLE;
        fun xmlValue(): String? = when (this) {
            CIRCLE -> null
            else -> name
        }
    }

    override val width: Int get() = w()
    override val height: Int get() = h()
}
