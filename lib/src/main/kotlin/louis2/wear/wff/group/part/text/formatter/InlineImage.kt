package louis2.wear.wff.group.part.text.formatter

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.common.attributes.ArithmeticExpression

/**
 * Represents an image that appears within a run of text. The image is treated as text, and not as an Image element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/formatter/inline-image)
 *
 * @param resource A string that represents the relative file path to the location where the image is stored.
 * @param source An arithmetic expression that evaluates to a string value.
 * @param overlapLeft A floating-point value that indicates the amount of horizontal space where the inline image should overlap the adjacent text on the left-hand side.
 * @param overlapRight A floating-point value that indicates the amount of horizontal space where the inline image should overlap the adjacent text on the right-hand side.
 */
@WffTagMarker
fun TextFormatterGroup.inlineImage(
    resource: String,
    width: Int,
    height: Int,
    source: ArithmeticExpression.String? = null,
    color: Color = Color.white,
    overlapLeft: Float = 0f,
    overlapRight: Float = 0f,
): Unit = INLINEIMAGE(
    initialAttributes = attributesMapOf(
        "resource", resource,
        "width", width.toString(),
        "height", height.toString(),
        "source", source?.toString(),
        "color", color.takeUnless { it == Color.white }?.xmlValue(),
        "overlapLeft", overlapLeft.takeUnless { it == 0f }?.toString(),
        "overlapRight", overlapRight.takeUnless { it == 0f }?.toString(),
    ),
    consumer = consumer
).visit {}

class INLINEIMAGE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "InlineImage",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsTemplate
