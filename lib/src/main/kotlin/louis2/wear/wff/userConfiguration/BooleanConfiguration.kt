package louis2.wear.wff.userConfiguration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

@WffTagMarker
fun USERCONFIGURATIONS.booleanConfiguration(
    id: String,
    displayName: String,
    icon: String? = null,
    screenReaderText: String? = null,
    defaultValue: Boolean,
): Unit = BOOLEANCONFIGURATION(
    initialAttributes = attributesMapOf(
        "id", id,
        "displayName", displayName,
        "icon", icon,
        "screenReaderText", screenReaderText,
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
