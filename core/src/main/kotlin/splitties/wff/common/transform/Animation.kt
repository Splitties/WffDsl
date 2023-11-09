package splitties.wff.common.transform

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.Direction
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag

/**
 * Applies an animation to the parent Transform element within the watch face XML file.
 * In cases where a Transform element changes the value of a specific attribute,
 * a child Animation element updates the changed value gradually on the watch face.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/transform/animation)
 *
 * @param controls A 4-component vector that represents the position of the control points for the animation. The default value is <0.5, 0.5, 0.5, 0.5>.
 *
 * This attribute takes effect only when interpolation is set to CUBIC_BEZIER.
 *
 * @param angleDirection The direction to use when applying angular rotation to this animation.
 *
 * @param repeat Indicates the number of times that the animation should repeat.
 * A value of 0 (the default value) causes the animation to never repeat.
 * For any other valid value the repeat mode is taken into account.
 *
 * This value must be -1 or greater.
 *
 * @param fps The animation rate, in frames per second. The default value is `15`.
 * @param duration The duration of the animation, in seconds. This attribute is required.
 */
@WffTagMarker
fun TRANSFORM.animation(
    interpolation: ANIMATION.Interpolation = ANIMATION.Interpolation.LINEAR,
    controls: Pair<Pair<Float, Float>, Pair<Float, Float>>? = null,
    angleDirection: Direction? = null,
    repeat: Int = 0,
    fps: Int = 15,
    duration: Float,
): Unit = ANIMATION(
    initialAttributes = attributesMapOf(
        "interpolation", interpolation.xmlValue(),
        "controls", controls?.xmlValue(),
        "angleDirection", angleDirection?.name,
        "repeat", repeat.takeUnless { it == 0 }?.toString(),
        "fps", fps.takeUnless { it == 15 }?.toString(),
        "duration", duration.toString(),
    ),
    consumer = consumer
).visit {}

private fun Pair<Pair<Float, Float>, Pair<Float, Float>>.xmlValue(): String {
    return "${first.first}, ${first.second}, ${second.second}, ${first.second}"
}

class ANIMATION(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "Animation",
    consumer = consumer,
    initialAttributes = initialAttributes,
    inlineTag = false,
    emptyTag = true
) {
    enum class Interpolation {
        LINEAR,
        EASE_IN,
        EASE_OUT,
        EASE_IN_OUT,
        OVERSHOOT,
        CUBIC_BEZIER;

        fun xmlValue(): String? = when (this) {
            LINEAR -> null
            else -> name
        }
    }
}
