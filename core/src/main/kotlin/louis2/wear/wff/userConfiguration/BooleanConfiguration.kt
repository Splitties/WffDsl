package louis2.wear.wff.userConfiguration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * A Boolean Configuration gives the user an option that can be turned on or off when customizing the watch face in the watch face editor.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/user-configuration/boolean-configuration)
 */
@WffTagMarker
fun USERCONFIGURATIONS.booleanConfiguration(
    id: String,
    displayName: String,
    screenReaderText: String? = null,
    icon: String? = null,
    defaultValue: Boolean,
): Unit = BOOLEANCONFIGURATION(
    initialAttributes = attributesMapOf(
        "id", id,
        "displayName", displayName,
        "screenReaderText", screenReaderText,
        "icon", icon,
        "defaultValue", defaultValue.toString().uppercase(),
    ),
    consumer = consumer
).visit {}

class BOOLEANCONFIGURATION(
    initialAttributes: Map<String, String> = emptyMap(),
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "BooleanConfiguration",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
