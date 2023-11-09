package splitties.wff.common.transform

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.SupportsGyro
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag
import splitties.wff.common.attributes.ArithmeticExpression

/**
 * Adjusts some attributes of the parent group- or part-based element according to arithmetic expressions that includes gyroscopic sensor data sources, such as `ACCELEROMETER_ANGLE_X`.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/transform/gyro)
 */
@WffTagMarker
fun SupportsGyro.gyro(
    x: ArithmeticExpression.Float? = null,
    y: ArithmeticExpression.Float? = null,
    scaleX: ArithmeticExpression.Float? = null,
    scaleY: ArithmeticExpression.Float? = null,
    angle: ArithmeticExpression.Float? = null,
    alpha: ArithmeticExpression.Int? = null
): Unit = GYRO(
    initialAttributes = attributesMapOf(
        "x", x?.toString(),
        "y", y?.toString(),
        "scaleX", scaleX?.toString(),
        "scaleY", scaleY?.toString(),
        "angle", angle?.toString(),
        "alpha", alpha?.toString(),
    ),
    consumer = consumer
).visit {}

class GYRO(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "Gyro",
    consumer = consumer,
    initialAttributes = initialAttributes,
    inlineTag = false,
    emptyTag = true
)
