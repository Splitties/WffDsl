package louis2.wear.wff

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit

/**
 * A scene is a container of visual tags. Each watch face must contain exactly one Scene element.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/scene)
 */
@WffTagMarker
inline fun WATCHFACE.scene(
    backgroundColor: UInt = 0xFF_000000u,
    crossinline block: SCENE.() -> Unit
) : Unit = SCENE(
    initialAttributes = attributesMapOf("backgroundColor", "#${backgroundColor.toString(radix = 16)}"),
    watchface = this,
    consumer = consumer
).visit(block)

class SCENE(
    initialAttributes: Map<String, String>,
    private val watchface: WATCHFACE,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Scene",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsGroup, SupportsConditions, SupportsClock, SupportsBooleanConfiguration {
    override val width: Int get() = watchface.width
    override val height: Int get() = watchface.height
}
