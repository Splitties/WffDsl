package louis2.wear.wff.group.part.text.decoration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*

/**
 * Applies an outward glow to a text element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/decoration/out-glow)
 *
 * @param radius Optional. This floating-point attribute specifies the distance that the glow extends beyond the edge of the text element.
 *
 * By default, this attribute's value is 8.0.
 */
@WffTagMarker
inline fun TextDecorationGroup.outGlow(
    color: Color,
    radius: Float = 8f,
    crossinline block: OUTGLOW.() -> Unit = {}
): Unit = OUTGLOW(
    initialAttributes = attributesMapOf(
        "color", color.xmlValue(),
        "radius", radius.takeUnless { it == 8f }?.toString(),
    ),
    consumer = consumer
).visit(block)

class OUTGLOW(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "OutGlow",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), TextDecoration
