package louis2.wear.wff.common.variant

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.VariantScope
import louis2.wear.wff.WffTagMarker
import louis2.wear.wff.XMLTag

/**
 * @param target The attribute whose value should change when the specified Wear OS device mode takes effect.
 * @param value The value that the attribute should have when the specified Wear OS device mode takes effect.
 */
@WffTagMarker
fun VariantScope.ambientVariant(
    target: String,
    value: String
): Unit = variant(
    mode = "AMBIENT",
    target = target,
    value = value
)

/**
 * @param target The attribute whose value should change when the specified Wear OS device mode takes effect.
 * @param value The value that the attribute should have when the specified Wear OS device mode takes effect.
 */
@WffTagMarker
fun VariantScope.ambientVariant(
    target: String,
    value: Int
): Unit = variant(
    mode = "AMBIENT",
    target = target,
    value = value.toString()
)

/**
 * @param target The attribute whose value should change when the specified Wear OS device mode takes effect.
 * @param value The value that the attribute should have when the specified Wear OS device mode takes effect.
 */
@WffTagMarker
fun VariantScope.ambientVariant(
    target: String,
    value: UByte
): Unit = variant(
    mode = "AMBIENT",
    target = target,
    value = value.toString()
)

/**
 * Allows watch face creators to define modified appearances, by changing the values of specific color and geometric properties, when the Wear OS device is in different display modes. As of Wear OS 4, the only supported mode is ambient mode.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/variant/variant)
 */
@WffTagMarker
fun VariantScope.variant(
    mode: String,
    target: String,
    value: String
): Unit = VARIANT(
    initialAttributes = attributesMapOf(
        "mode", mode,
        "target", target,
        "value", value,
    ),
    consumer = consumer
).visit {}

class VARIANT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Variant",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
