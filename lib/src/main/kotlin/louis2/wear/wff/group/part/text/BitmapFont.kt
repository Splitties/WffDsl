package louis2.wear.wff.group.part.text

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.TextFormatterGroup
import louis2.wear.wff.TextScope
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag
import louis2.wear.wff.clock.TIMETEXT
import louis2.wear.wff.internal.asArgbColor
import louis2.wear.wff.BITMAPFONTS.BITMAPFONT as BitmapFont

/**
 * Provides rendering instructions for a specific instance of [BitmapFont] that appears in a text-based element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/bitmap-font)
 *
 * @param family A string representing the name of the bitmap character set.
 * This name should match the name given to a standalone [BitmapFont] element
 * defined elsewhere in the watch face file.
 */
@WffTagMarker
inline fun TextScope.bitmapFont(
    family: String,
    size: Float,
    color: UInt? = null,
    crossinline block: BITMAPFONT.() -> Unit
): Unit = BITMAPFONT(
    emptyTag = false,
    initialAttributes = attributesMapOf(
        "family", family,
        "size", size.toString(),
        "color", color?.asArgbColor(),
    ),
    consumer = consumer
).visit(block)

/**
 * Provides rendering instructions for a specific text element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/font)
 */
@WffTagMarker
fun TIMETEXT.bitmapFont(
    family: String,
    size: Float,
    color: UInt? = null,
): Unit = BITMAPFONT(
    emptyTag = true,
    initialAttributes = attributesMapOf(
        "family", family,
        "size", size.toString(),
        "color", color?.asArgbColor(),
    ),
    consumer = consumer
).visit {}

class BITMAPFONT(
    emptyTag: Boolean,
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "BitmapFont",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = emptyTag
), TextFormatterGroup
