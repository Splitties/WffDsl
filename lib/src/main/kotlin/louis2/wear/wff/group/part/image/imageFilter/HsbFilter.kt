package louis2.wear.wff.group.part.image.imageFilter

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * A filter for changing a pixel or element's hue, saturation, and brightness (HSB).
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/image/image-filter/hsb-filter)
 *
 * @param hueRotate Represents an angle, in degrees, along the color wheel. An angle of 0.0 degrees corresponds to red,
 * an angle of 120.0 degrees corresponds to green, and an angle of 240.0 degrees corresponds to blue.
 *
 * By default, this value is 0.0.
 *
 * @param saturate Represents the hue's saturation. This is a floating-point number that is in the range [0, 1].
 * A saturation of 0.0 represents a shade of gray; a saturation of 1.0 represents the hue's full color, with no gray mixing in.
 *
 * By default, this value is 1.0.
 *
 * @param brightness Represents the hue's brightness. This is a floating-point number that is in the range [0, 1].
 * A brightness of 0.0 represents black; a brightness of 1.0 represents the hue's full color, with no darkening.
 *
 * By default, this value is 1.0.
 */
@WffTagMarker
fun IMAGEFILTERS.hsbFilter(
    hueRotate: Float = 0f,
    saturate: Float = 1f,
    brightness: Float = 1f
): Unit = HSBFILTERS(
    initialAttributes = attributesMapOf(
        "hueRotate", hueRotate.takeUnless { it == 0f }?.toString(),
        "saturate", saturate.takeUnless { it == 1f }?.toString(),
        "brightness", brightness.takeUnless { it == 1f }?.toString(),
    ),
    consumer = consumer
).visit {}

class HSBFILTERS(
    initialAttributes: Map<String, String>,
    consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "HsbFilter",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
