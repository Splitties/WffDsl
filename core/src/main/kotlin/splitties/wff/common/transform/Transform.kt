package splitties.wff.common.transform

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.Transformable
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag
import splitties.wff.attr.AttrRef
import splitties.wff.common.attributes.ArithmeticExpression
import splitties.wff.common.attributes.ArithmeticExpressionScope

/**
 * Contains information about a geometric transformation.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/transform/transform)
 */
@WffTagMarker
inline fun <T : ArithmeticExpression> Transformable.transform(
    target: AttrRef<T>,
    expression: ArithmeticExpressionScope.() -> T,
    mode: TRANSFORM.Mode = TRANSFORM.Mode.TO,
    crossinline block: TRANSFORM.() -> Unit = {}
): Unit = TRANSFORM(
    initialAttributes = attributesMapOf(
        "target", target.name,
        "value", ArithmeticExpression(expression).toString(),
        "mode", mode.xmlValue()
    ),
    consumer = consumer
).visit(block)

// Can't use generics below because it would clash with signature from above at use site (Kotlin limitation?),
// unless named parameters are used.

/**
 * Contains information about a geometric transformation.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/transform/transform)
 */
@WffTagMarker
inline fun Transformable.transform(
    target: AttrRef<ArithmeticExpression.Int>,
    value: ArithmeticExpression.Int,
    mode: TRANSFORM.Mode = TRANSFORM.Mode.TO,
    crossinline block: TRANSFORM.() -> Unit = {}
): Unit = TRANSFORM(
    initialAttributes = attributesMapOf(
        "target", target.name,
        "value", value.toString(),
        "mode", mode.xmlValue()
    ),
    consumer = consumer
).visit(block)

/**
 * Contains information about a geometric transformation.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/transform/transform)
 */
@WffTagMarker
inline fun Transformable.transform(
    target: AttrRef<ArithmeticExpression.Float>,
    value: ArithmeticExpression.Float,
    mode: TRANSFORM.Mode = TRANSFORM.Mode.TO,
    crossinline block: TRANSFORM.() -> Unit = {}
): Unit = TRANSFORM(
    initialAttributes = attributesMapOf(
        "target", target.name,
        "value", value.toString(),
        "mode", mode.xmlValue()
    ),
    consumer = consumer
).visit(block)

class TRANSFORM(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "Transform",
    consumer = consumer,
    initialAttributes = initialAttributes,
    inlineTag = false,
    emptyTag = false
) {
    enum class Mode {
        TO, BY;

        fun xmlValue(): String? = if (this == TO) null else name
    }
}
