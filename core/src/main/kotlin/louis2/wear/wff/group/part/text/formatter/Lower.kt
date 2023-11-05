package louis2.wear.wff.group.part.text.formatter

import kotlinx.html.TagConsumer
import kotlinx.html.visit
import louis2.wear.wff.TextFormatterGroup
import louis2.wear.wff.SupportsTemplate
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * Renders text in all lowercase characters.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/formatter/lower)
 *
 * ## Inner elements
 *
 * The Lower element can contain any number of Template inner elements.
 */
@WffTagMarker
inline fun TextFormatterGroup.lower(
    crossinline block: LOWER.() -> Unit = {}
): Unit = LOWER(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

class LOWER(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Lower",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsTemplate
