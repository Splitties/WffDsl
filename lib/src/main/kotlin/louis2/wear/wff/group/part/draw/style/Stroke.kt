package louis2.wear.wff.group.part.draw.style

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.internal.asArgbColor

/**
 * A stroke sets the visual style of a line or border.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/style/stroke)
 */
@WffTagMarker
inline fun StrokeAble.stroke(
    thickness: Float,
    dashIntervals: String,
    dashPhase: String,
    cap: STROKE.Cap,
    crossinline block: STROKE.() -> Unit
): Unit = STROKE(
    initialAttributes = attributesMapOf(
        "thickness", thickness.toString(),
        "dashIntervals", dashIntervals,
        "dashPhase", dashPhase,
        "cap", cap.xmlValue()
    ),
    consumer = consumer
).visit(block)

/**
 * A stroke sets the visual style of a line or border.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/style/stroke)
 */
@WffTagMarker
fun StrokeAble.stroke(
    color: UInt,
    thickness: Float,
    dashIntervals: String,
    dashPhase: String,
    cap: STROKE.Cap
): Unit = STROKE(
    initialAttributes = attributesMapOf(
        "color", color.asArgbColor(),
        "thickness", thickness.toString(),
        "dashIntervals", dashIntervals,
        "dashPhase", dashPhase,
        "cap", cap.xmlValue()
    ),
    consumer = consumer
).visit {}

class STROKE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Stroke",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SupportsGradients {
    enum class Cap {
        BUTT, ROUND, SQUARE;

        fun xmlValue(): String? = when (this) {
            BUTT -> null
            else -> name
        }
    }
}
