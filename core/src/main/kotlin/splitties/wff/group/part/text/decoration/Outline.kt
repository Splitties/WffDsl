package splitties.wff.group.part.text.decoration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.*

/**
 * Draws an outline around a text element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/decoration/outline)
 *
 * @param width Optional. This floating-point attribute specifies the distance that the outline appears outside the edge of the text element.
 *
 * By default, this attribute's value is 2.0.
 */
@WffTagMarker
inline fun TextDecorationGroup.outline(
    color: Color,
    width: Float = 2f,
    crossinline block: OUTLINE.() -> Unit = {}
): Unit = OUTLINE(
    initialAttributes = attributesMapOf(
        "color", color.xmlValue(),
        "width", width.takeUnless { it == 2f }?.toString(),
    ),
    consumer = consumer
).visit(block)

class OUTLINE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Outline",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), TextDecoration
