package splitties.wff.userConfiguration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag

/**
 * A List Configuration allows the user to select one item from a list when customizing the watch face in the watch face editor.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/user-configuration/list-configuration)
 */
@WffTagMarker
inline fun USERCONFIGURATIONS.listConfiguration(
    id: String,
    displayName: String,
    screenReaderText: String? = null,
    icon: String? = null,
    defaultValue: Int,
    crossinline block: LISTCONFIGURATION.() -> Unit
): Unit = LISTCONFIGURATION(
    initialAttributes = attributesMapOf(
        "id", id,
        "displayName", displayName,
        "screenReaderText", screenReaderText,
        "icon", icon,
        "defaultValue", defaultValue.toString(),
    ),
    consumer = consumer
).visit(block)

@WffTagMarker
fun LISTCONFIGURATION.listOption(
    id: Int,
    displayName: String,
    screenReaderText: String? = null,
    icon: String? = null,
): Unit = LISTCONFIGURATION.LISTOPTION(
    initialAttributes = attributesMapOf(
        "id", id.toString(),
        "displayName", displayName,
        "screenReaderText", screenReaderText,
        "icon", icon
    ),
    consumer = consumer
).visit {}

class LISTCONFIGURATION(
    initialAttributes: Map<String, String> = emptyMap(),
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "ListConfiguration",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
) {
    class LISTOPTION(
        initialAttributes: Map<String, String> = emptyMap(),
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "ListOption",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = true
    )
}
