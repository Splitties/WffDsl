package louis2.wear.wff.group.part.text.decoration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.internal.asArgbColor

/**
 * Draws a shadow near a text element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/decoration/shadow)
 *
 * @param offsetX A floating-point number that specifies the distance that the shadow is shifted to the right of the text's center. Negative values indicate a shift to the left.
 *
 * By default, this attribute's value is `2.0`.
 *
 * @param offsetY A floating-point number that specifies the distance that the shadow is shifted down from the text's center. Negative values indicate a shift upward.
 *
 * By default, this attribute's value is `2.0`.
 *
 * @param radius A floating-point number that specifies the distance that the shadow extends beyond the edge of the text element.
 *
 * By default, this attribute's value is `2.0`.
 */
@WffTagMarker
inline fun TextDecorationGroup.shadow(
    color: UInt,
    offsetX: Float = 2f,
    offsetY: Float = 2f,
    radius: Float = 2f,
    crossinline block: SHADOW.() -> Unit = {}
): Unit = SHADOW(
    initialAttributes = attributesMapOf(
        "color", color.asArgbColor(),
        "offsetX", offsetX.takeUnless { it == 2f }?.toString(),
        "offsetY", offsetY.takeUnless { it == 2f }?.toString(),
        "radius", radius.takeUnless { it == 2f }?.toString(),
    ),
    consumer = consumer
).visit(block)

class SHADOW(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Shadow",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), TextDecoration
