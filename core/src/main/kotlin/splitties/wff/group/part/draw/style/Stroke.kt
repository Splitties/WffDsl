package splitties.wff.group.part.draw.style

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.*
import splitties.wff.attr.AttrsHost

/**
 * A stroke sets the visual style of a line or border.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/style/stroke)
 */
@WffTagMarker
inline fun StrokeAble.stroke(
    thickness: Float,
    dashIntervals: FloatArray? = null,
    dashPhase: Float = 0f,
    cap: STROKE.Cap = STROKE.Cap.BUTT,
    crossinline block: STROKE.() -> Unit
): Unit = STROKE(
    initialAttributes = attributesMapOf(
        "thickness", thickness.toString(),
        "dashIntervals", dashIntervals?.asDashIntervalsString(),
        "dashPhase", dashPhase.takeUnless { it == 0f }?.toString(),
        "cap", cap.xmlValue()
    ),
    consumer = consumer,
    emptyTag = false,
).visit(block)

/**
 * A stroke sets the visual style of a line or border.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/draw/style/stroke)
 */
@WffTagMarker
fun StrokeAble.stroke(
    color: Color = Color.white,
    thickness: Float,
    dashIntervals: FloatArray? = null,
    dashPhase: Float = 0f,
    cap: STROKE.Cap = STROKE.Cap.BUTT
): Unit = STROKE(
    initialAttributes = attributesMapOf(
        "color", color.xmlValue(),
        "thickness", thickness.toString(),
        "dashIntervals", dashIntervals?.asDashIntervalsString(),
        "dashPhase", dashPhase.takeUnless { it == 0f }?.toString(),
        "cap", cap.xmlValue()
    ),
    emptyTag = true,
    consumer = consumer
).visit {}

class STROKE(
    initialAttributes: Map<String, String>,
    emptyTag: Boolean,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Stroke",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = emptyTag
), SupportsGradients, Transformable {
    enum class Cap {
        BUTT, ROUND, SQUARE;

        fun xmlValue(): String? = when (this) {
            BUTT -> null
            else -> name
        }
    }
    override val attrs = Attrs()

    class Attrs internal constructor(): AttrsHost() {
        val thickness by float()
        val dashPhase by float()
    }
}

@PublishedApi
internal fun FloatArray.asDashIntervalsString(): String {
    require(size % 2 == 0)
    require(size >= 2)
    return joinToString(separator = " ")
}
