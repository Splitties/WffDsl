package splitties.wff.group.part.text.formatter

import kotlinx.html.TagConsumer
import kotlinx.html.visit
import splitties.wff.TextFormatterGroup
import splitties.wff.SupportsTemplate
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag

/**
 * Renders text in all uppercase characters.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/formatter/upper)
 *
 * ## Inner elements
 *
 * The Upper element can contain any number of Template inner elements.
 */
@WffTagMarker
inline fun TextFormatterGroup.upper(
    crossinline block: UPPER.() -> Unit = {}
): Unit = UPPER(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

class UPPER(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Upper",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsTemplate
