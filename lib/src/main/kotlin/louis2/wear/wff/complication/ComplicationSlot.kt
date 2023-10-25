package louis2.wear.wff.complication

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.Container
import louis2.wear.wff.SupportsComplicationSlot
import louis2.wear.wff.SupportsScreenReader
import louis2.wear.wff.SupportsVariants
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * A Complication Slot is an area of the watch face that a Complication can be added by the user.
 * Complication Slots contain Complication elements, which define how the Complication is rendered for different
 * Complication Types and in different watch face modes.
 *
 * Note: A watch face can have at most eight instances of ComplicationSlot, total.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/complication/complication-slot)
 */
@WffTagMarker
inline fun SupportsComplicationSlot.complicationSlot(
        x: Int = 0,
        y: Int = 0,
        width: Int = this.width,
        height: Int = this.height,
        slotId: Int,
        supportedTypes: List<COMPLICATION.ComplicationType>,
        pivotX: Float = .5f,
        pivotY: Float = .5f,
        angle: Float = 0f,
        alpha: UByte = 0xFFu,
        scaleX: Float = 1f,
        scaleY: Float = 1f,
        tintColor: UInt? = null,
        displayName: String,
        isCustomizable: Boolean = true,
        crossinline block: COMPLICATIONSLOT.() -> Unit = {}
): Unit = COMPLICATIONSLOT(
        initialAttributes = attributesMapOf(
                "x", x.toString(),
                "y", y.toString(),
                "width", width.toString(),
                "height", height.toString(),
                "slotId", slotId.toString(),
                "supportedTypes", supportedTypes.joinToString(" ") { it.xmlValue() },
                "pivotX", pivotX.takeUnless { angle == 0f }?.toString(),
                "pivotY", pivotY.takeUnless { angle == 0f }?.toString(),
                "angle", angle.takeUnless { it == 0f }?.toString(),
                "alpha", alpha.takeUnless { it == 0xFFu.toUByte() }?.toString(),
                "scaleX", scaleX.takeUnless { it == 1f }?.toString(),
                "scaleY", scaleY.takeUnless { it == 1f }?.toString(),
                "tintColor", tintColor?.toString(),
                "displayName", displayName,
                "isCustomizable", isCustomizable.takeUnless { it == true }?.toString(),
        ),
        consumer = consumer,
        width = width,
        height = height,
).visit(block)

class COMPLICATIONSLOT(
        initialAttributes: Map<String, String>,
        override val consumer: TagConsumer<*>,
        override val width: Int,
        override val height: Int
) : XMLTag(
        tagName = "ComplicationSlot",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
), Container, SupportsScreenReader, SupportsVariants
