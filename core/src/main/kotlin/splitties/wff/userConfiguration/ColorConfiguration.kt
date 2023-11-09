package splitties.wff.userConfiguration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.Color
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag
import splitties.wff.internal.xmlValue

/**
 * A `ColorConfiguration` gives the user an option to change the color of elements of the watch face through the watch face editor.
 *
 * When a `ColorConfiguration` is defined, any color-based attribute of another element, such as `tintColor`, can refer to a color from the configuration using an expression that uses the ID values of the `ColorConfiguration` and a `ColorOption`.
 * For a complete demonstration, see the example.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/user-configuration/color-configuration)
 */
@WffTagMarker
inline fun USERCONFIGURATIONS.colorConfiguration(
    id: String,
    displayName: String,
    screenReaderText: String? = null,
    icon: String? = null,
    defaultValue: String,
    crossinline block: COLORCONFIGURATION.() -> Unit
): Unit = COLORCONFIGURATION(
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
fun COLORCONFIGURATION.colorOption(
    id: String,
    displayName: String? = null,
    screenReaderText: String? = null,
    icon: String? = null,
    colors: List<Color>
): Unit = COLORCONFIGURATION.COLOROPTION(
    initialAttributes = attributesMapOf(
        "id", id,
        "displayName", displayName,
        "screenReaderText", screenReaderText,
        "icon", icon,
        "colors", colors.xmlValue()
    ),
    consumer = consumer
).visit {}

@WffTagMarker
fun COLORCONFIGURATION.colorOption(
    id: String,
    displayName: String? = null,
    screenReaderText: String? = null,
    icon: String? = null,
    color: Color
): Unit = COLORCONFIGURATION.COLOROPTION(
    initialAttributes = attributesMapOf(
        "id", id,
        "displayName", displayName,
        "screenReaderText", screenReaderText,
        "icon", icon,
        "colors", listOf(color).xmlValue()
    ),
    consumer = consumer
).visit {}

class COLORCONFIGURATION(
    initialAttributes: Map<String, String> = emptyMap(),
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "ColorConfiguration",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
) {
    class COLOROPTION(
        initialAttributes: Map<String, String> = emptyMap(),
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "ColorOption",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = true
    )
}
