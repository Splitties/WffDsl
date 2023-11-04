package louis2.wear.wff.group.part.animatedImage

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*
import louis2.wear.wff.attr.ContainerAttrs
import louis2.wear.wff.common.EventTrigger

/**
 * An animation controller controls the playback of animations on the watch face.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/animated-image/animation-controller)
 *
 * @param loopCount How many times to repeat an animation before it stops. If `repeat` is set to `TRUE`, it takes precedence and this value is ignored. Defaults to 1.
 * @param resumePlayback Animations pause when the watch face is not visible. If `resumePlayback` is `TRUE`, the animation continues from the same frame that was shown when the watch face previously became not visible. If `resumePlayback` is set to `FALSE`, the animation restarts from the beginning when the watch face becomes visible again. Defaults to `FALSE`.
 * @param beforePlaying Define the state of the animation element before it can play.
 * @param afterPlaying Define the state of the animation element after it has finished playing.
 */
@WffTagMarker
fun PARTANIMATEDIMAGE.animationController(
    play: List<EventTrigger>,
    delayPlay: Float = 0f,
    delayRepeat: Float = 0f,
    repeat: Boolean = false,
    loopCount: Int = 1,
    resumePlayback: Boolean = false,
    beforePlaying: ANIMATIONCONTROLLER.FrameOption = ANIMATIONCONTROLLER.FrameOption.DO_NOTHING,
    afterPlaying: ANIMATIONCONTROLLER.FrameOption = ANIMATIONCONTROLLER.FrameOption.DO_NOTHING
): Unit = ANIMATIONCONTROLLER(
    initialAttributes = attributesMapOf(
        "play", play.joinToString(separator = " ") { it.xmlValue() },
        "delayPlay", delayPlay.takeUnless { delayPlay == 0f }?.toString(),
        "delayRepeat", delayRepeat.takeUnless { delayPlay == 0f }?.toString(),
        "repeat", repeat.takeIf { it }?.toString()?.uppercase(),
        "loopCount", loopCount.takeUnless { repeat || it == 1 }?.toString(),
        "resumePlayback", resumePlayback.takeIf { it }?.toString()?.uppercase(),
        "beforePlaying", beforePlaying.xmlValue(),
        "afterPlaying", afterPlaying.xmlValue(),
    ),
    consumer = consumer
).visit {}

class ANIMATIONCONTROLLER(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "AnimationController",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
) {
    enum class FrameOption {
        /** Causes no change to the rendered state of the element. */
        DO_NOTHING,
        /** Fixes the rendering of the element to the first frame of the animation. */
        FIRST_FRAME,
        /** Fixes the rendering of the element to the thumbnail resource provided by the animated resource. */
        THUMBNAIL,
        /** Hides the animation */
        HIDE;

        fun xmlValue() = when (this) {
            DO_NOTHING -> null
            else -> name
        }
    }
}
