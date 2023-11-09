package splitties.wff.complication

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag

/**
 * Complication bounding areas are used to define the rendering area for a Complication. An outline of the bounding area is shown in the watch face editor. Any content outside of the bounding area is cropped.
 *
 * The bounding area also determines the region where the user can select the complication from the watch face.
 *
 * Introduced in Wear OS 4.
 *
 * A `BoundingBox` element defines a rectangular bounding area for the complication.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/complication/bounding#bounding-box)
 *
 * @param outlinePadding A float that specifies padding to apply to the outside of the bounding area. This is used to create a larger outline in the watch face editor. Defaults to 0.
 */
@WffTagMarker
fun COMPLICATIONSLOT.boundingBox(
    x: Int = 0,
    y: Int = 0,
    width: Int = this.width,
    height: Int = this.height,
    outlinePadding: Float = 0f
): Unit = BOUNDINGBOX(
    initialAttributes = attributesMapOf(
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "outlinePadding", outlinePadding.takeUnless { it == 0f }?.toString()
    ),
    consumer = consumer,
).visit {}

class BOUNDINGBOX(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "BoundingBox",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
