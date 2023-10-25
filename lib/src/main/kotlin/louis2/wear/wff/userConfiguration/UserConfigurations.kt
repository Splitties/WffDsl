package louis2.wear.wff.userConfiguration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*

/**
 * A container for settings that a user can modify to personalize the watch face. The elements contained within UserConfigurations are used to configure the watch face editor.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/user-configuration/user-configurations)
 */
@WffTagMarker
inline fun WATCHFACE.userConfigurations(
    crossinline block: USERCONFIGURATIONS.() -> Unit
): Unit = USERCONFIGURATIONS(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

@WffTagMarker
fun USERCONFIGURATIONS.booleanConfiguration(
    id: String,
    displayName: String,
    icon: String? = null,
    screenReaderText: String? = null,
    defaultValue: Boolean,
): Unit = USERCONFIGURATIONS.BOOLEANCONFIGURATION(
    initialAttributes = attributesMapOf(
        "id", id,
        "displayName", displayName,
        "icon", icon,
        "screenReaderText", screenReaderText,
        "defaultValue", defaultValue.toString().uppercase(),
    ),
    consumer = consumer
).visit {}

class USERCONFIGURATIONS(
    initialAttributes: Map<String, String> = emptyMap(),
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "UserConfigurations",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
) {
    class BOOLEANCONFIGURATION(
        initialAttributes: Map<String, String> = emptyMap(),
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "BooleanOption",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = true
    )
}
