package louis2.wear.wff.group.part.text.decoration

import kotlinx.html.TagConsumer
import kotlinx.html.visit
import louis2.wear.wff.TextDecoration
import louis2.wear.wff.TextDecorationGroup
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * Draws a horizontal line underneath a text element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/decoration/underline)
 */
@WffTagMarker
inline fun TextDecorationGroup.underline(
    crossinline block: UNDERLINE.() -> Unit = {}
): Unit = UNDERLINE(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

/**
 * Draws a horizontal line underneath a text element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/decoration/underline)
 */
@WffTagMarker
inline fun TextDecoration.underline(
    crossinline block: UNDERLINE.() -> Unit = {}
): Unit = UNDERLINE(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

class UNDERLINE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Underline",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), TextDecoration
