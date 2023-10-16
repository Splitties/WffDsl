package louis2.wear.wff.group.part.text

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.FontScope
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag
import louis2.wear.wff.internal.asArgbColor

/**
 * Provides rendering instructions for a specific text element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/font)
 */
@WffTagMarker
inline fun FontScope.font(
    family: String,
    size: Float,
    color: UInt? = null,
    slant: FONT.Slant = FONT.Slant.NORMAL,
    width: FONT.Width = FONT.Width.NORMAL,
    weight: FONT.Weight = FONT.Weight.NORMAL,
    crossinline block: FONT.() -> Unit = {}
): Unit = FONT(
    initialAttributes = attributesMapOf(
        "family", family,
        "size", size.toString(),
        "slant", slant.xmlValue(),
        "width", width.xmlValue(),
        "weight", weight.xmlValue(),
        "color", color?.asArgbColor(),
    ),
    consumer = consumer
).visit(block)

class FONT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Font",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
) {
    enum class Slant {
        NORMAL, ITALIC;

        fun xmlValue(): String? = when (this) {
            NORMAL -> null
            else -> name
        }
    }
    enum class Width {
        ULTRA_CONDENSED, EXTRA_CONDENSED, CONDENSED, SEMI_CONDENSED,
        NORMAL,
        SEMI_EXPANDED, EXPANDED, EXTRA_EXPANDED, ULTRA_EXPANDED;

        fun xmlValue(): String? = when (this) {
            NORMAL -> null
            else -> name
        }
    }

    enum class Weight {
        THIN, ULTRA_LIGHT, EXTRA_LIGHT, LIGHT,
        NORMAL,
        MEDIUM, SEMI_BOLD, BOLD, ULTRA_BOLD, EXTRA_BOLD,
        BLACK, EXTRA_BLACK;

        companion object {
            operator fun invoke(value: Int): Weight = when (value) {
                100 -> THIN
                150 -> ULTRA_LIGHT
                200 -> EXTRA_LIGHT
                300 -> LIGHT
                500 -> MEDIUM
                600 -> SEMI_BOLD
                700 -> BOLD
                750 -> ULTRA_BOLD
                800 -> EXTRA_BOLD
                900 -> BLACK
                1000 -> EXTRA_BLACK
                else -> throw IllegalArgumentException("Unsupported value: $value")
            }
        }

        fun xmlValue(): String? = when (this) {
            NORMAL -> null
            else -> name
        }
    }
}
