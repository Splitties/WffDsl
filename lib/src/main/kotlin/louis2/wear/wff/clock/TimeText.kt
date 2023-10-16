package louis2.wear.wff.clock

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.internal.asArgbColor

/**
 * A time text is a formatted string representing the current time.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/clock/time-text)
 */
@WffTagMarker
inline fun DIGITALCLOCK.timeText(
    format: String,
    hourFormat: TIMETEXT.HourFormat = TIMETEXT.HourFormat.SYNC_TO_DEVICE,
    align: Alignment = Alignment.START,
    x: Int = 0,
    y: Int = 0,
    width: Int = this.width,
    height: Int = this.height,
    pivotX: Float = .5f,
    pivotY: Float = .5f,
    alpha: UByte = 0xFFu,
    tintColor: UInt? = null,
    crossinline block: TIMETEXT.() -> Unit = {}
): Unit = TIMETEXT(
    initialAttributes = attributesMapOf(
        "format", format,
        "hourFormat", hourFormat.xmlValue(),
        "align", align.xmlValue(Alignment.START),
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "pivotX", pivotX.toString(),
        "pivotY", pivotY.toString(),
        "alpha", alpha.takeUnless { it == 0xFFu.toUByte() }?.toString(),
        "tintColor", tintColor?.asArgbColor(),
    ),
    consumer = consumer
).visit(block)

class TIMETEXT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "TimeText",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), BitmapFontScope, FontScope, VariantScope {
    enum class HourFormat {
        SYNC_TO_DEVICE, _12, _24;
        fun xmlValue(): String? = when (this) {
            SYNC_TO_DEVICE -> null
            _12 -> "12"
            _24 -> "24"
        }
    }
}
