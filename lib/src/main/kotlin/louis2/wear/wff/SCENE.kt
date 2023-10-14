package louis2.wear.wff

import kotlinx.html.*

/**
 * A scene is a container of visual tags. Each watch face must contain exactly one Scene element.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/scene)
 */
@HtmlTagMarker
inline fun WATCHFACE.scene(
    backgroundColor: UInt = 0xFF_000000u,
    crossinline block: SCENE.() -> Unit
) : Unit = SCENE(
    initialAttributes = attributesMapOf("backgroundColor", "#${backgroundColor.toString(radix = 16)}"),
    consumer = consumer
).visit(block)

class SCENE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Scene",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
)
