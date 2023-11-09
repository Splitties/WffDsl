package splitties.wff.group.part.text.formatter

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.SupportsTemplate
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag
import splitties.wff.common.attributes.ArithmeticExpression

/**
 * Allows watch face developers to specify a string format, such as %s %d. The string format is very similar to printf() in the C programming language or String.format() in the Java programming language.
 *
 * Additionally, Template allows the developer to use Android string resources, including the ones declared in res/values/strings.xml.
 *
 * Introduced in Wear OS 4.
 *
 * @param stringResourceName "`@string/`" will be prepended to the text.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/formatter/template)
 */
@Suppress("conflicting_overloads")
@WffTagMarker
@JvmName("templateWithStringResourceName")
inline fun SupportsTemplate.template(
    stringResourceName: String,
    crossinline block: TEMPLATE.() -> Unit = {}
): Unit = template {
    +"@string/$stringResourceName"
    block()
}

/**
 * Allows watch face developers to specify a string format, such as %s %d. The string format is very similar to printf() in the C programming language or String.format() in the Java programming language.
 *
 * Additionally, Template allows the developer to use Android string resources, including the ones declared in res/values/strings.xml.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/formatter/template)
 */
@Suppress("conflicting_overloads")
@WffTagMarker
inline fun SupportsTemplate.template(
    text: String,
    crossinline block: TEMPLATE.() -> Unit = {}
): Unit = template {
    +text
    block()
}

/**
 * Allows watch face developers to specify a string format, such as %s %d. The string format is very similar to printf() in the C programming language or String.format() in the Java programming language.
 *
 * Additionally, Template allows the developer to use Android string resources, including the ones declared in res/values/strings.xml.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/part/text/formatter/template)
 */
@WffTagMarker
inline fun SupportsTemplate.template(
    crossinline block: TEMPLATE.() -> Unit
): Unit = TEMPLATE(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

@WffTagMarker
fun TEMPLATE.parameter(
    expression: ArithmeticExpression.String
): Unit = TEMPLATE.PARAMETER(
    initialAttributes = attributesMapOf("expression", expression.toString()),
    consumer = consumer
).visit {}

class TEMPLATE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Template",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
) {
    class PARAMETER(
        initialAttributes: Map<String, String>,
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "Parameter",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = true
    )
}
