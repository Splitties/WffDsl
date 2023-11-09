package splitties.wff.complication

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.Direction
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag

/**
 * Complication bounding areas are used to define the rendering area for a Complication. An outline of the bounding area is shown in the watch face editor. Any content outside of the bounding area is cropped.
 *
 * The bounding area also determines the region where the user can select the complication from the watch face.
 *
 * Introduced in Wear OS 4.
 *
 * A `BoundingArc` element defines a arc shaped bounding area for the complication.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/complication/bounding#bounding-arc)
 *
 * @param isRoundEdge Determines whether the cap at the end of the arc is flat or rounded. Defaults to FALSE.
 * @param outlinePadding A float that specifies padding to apply to the outside of the bounding area. This is used to create a larger outline in the watch face editor. Defaults to 0.
 */
@WffTagMarker
fun COMPLICATIONSLOT.boundingArc(
    centerX: Float,
    centerY: Float,
    width: Float,
    height: Float,
    thickness: Float,
    startAngle: Float,
    endAngle: Float,
    isRoundEdge: Boolean = false,
    direction: Direction = Direction.CLOCKWISE,
    outlinePadding: Float = 0f
): Unit = BOUNDINGARC(
    initialAttributes = attributesMapOf(
        "centerX", centerX.toString(),
        "centerY", centerY.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "thickness", thickness.toString(),
        "startAngle", startAngle.toString(),
        "endAngle", endAngle.toString(),
        "isRoundEdge", isRoundEdge.takeIf { it }.toString().uppercase(),
        "direction", direction.xmlValue(),
        "outlinePadding", outlinePadding.takeUnless { it == 0f }?.toString()
    ),
    consumer = consumer,
).visit {}

class BOUNDINGARC(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "BoundingArc",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
