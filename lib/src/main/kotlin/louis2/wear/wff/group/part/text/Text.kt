package louis2.wear.wff.group.part.text

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.internal.asArgbColor

/**
 * Specifies a plain-text configuration.
 *
 * Introduced in Wear OS 4.
 *
 * @param The default value is CENTER.
 * @param ellipsis A boolean value that indicates whether to show an ellipsis if the text is too long to fit in the parent element. The default is FALSE.
 * @param maxLines A non-zero integer that indicates the maximum number of lines that the parent element can use to display the text. If this value is less than 0, the watch face arranges the text in the optimal number of lines for taking up the parent element's height.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/text)
 */
@WffTagMarker
inline fun PARTTEXT.text(
    align: Alignment = Alignment.CENTER,
    ellipsis: Boolean = false,
    maxLines: Int? = null,
    crossinline block: TEXT.() -> Unit = {}
): Unit = TEXT(
    initialAttributes = attributesMapOf(
        "align", align.xmlValue(Alignment.CENTER),
        "ellipsis", ellipsis.takeIf { it }?.toString()?.uppercase(),
        "maxLines", maxLines?.toString()
    ),
    consumer = consumer
).visit(block)

class TEXT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Text",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), FontScope, BitmapFontScope
