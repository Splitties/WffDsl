package louis2.wear.wff.group.part.text.decoration

import kotlinx.html.TagConsumer
import kotlinx.html.visit
import louis2.wear.wff.TextDecoration
import louis2.wear.wff.TextDecorationGroup
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * Draws a horizontal line through a text element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/decoration/strike-through)
 */
@WffTagMarker
inline fun TextDecorationGroup.strikeThrough(
    crossinline block: STRIKETHROUGH.() -> Unit = {}
): Unit = STRIKETHROUGH(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

/**
 * Draws a horizontal line through a text element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/decoration/strike-through)
 */
@WffTagMarker
inline fun TextDecoration.strikeThrough(
    crossinline block: STRIKETHROUGH.() -> Unit = {}
): Unit = STRIKETHROUGH(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

class STRIKETHROUGH(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "StrikeThrough",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), TextDecoration
