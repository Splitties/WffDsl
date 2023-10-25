package louis2.wear.wff.common

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*

/**
 * Provides comparison logic for conditionally enabling the appearance, animation, and event handling of child elements.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/condition)
 */
@WffTagMarker
inline fun SupportsConditions.condition(
    crossinline block: CONDITION.() -> Unit
): Unit = CONDITION(parentContainer = this, consumer = consumer).visit(block)

@WffTagMarker
inline fun CONDITION.expressions(
    crossinline block: CONDITION.EXPRESSIONS.() -> Unit
): Unit = CONDITION.EXPRESSIONS(consumer = consumer).visit(block)

@WffTagMarker
fun CONDITION.EXPRESSIONS.expression(
    name: String,
    arithmeticExpression: String
): Unit = CONDITION.EXPRESSION(
    initialAttributes = attributesMapOf("name", name),
    consumer = consumer
).visit { +arithmeticExpression }


@WffTagMarker
inline fun CONDITION.compare(
    expressionName: String,
    crossinline block: CONDITION.COMPARE.() -> Unit
): Unit = CONDITION.COMPARE(
    initialAttributes = attributesMapOf("expression", expressionName),
    parentContainer = this,
    consumer = consumer
).visit(block)

@WffTagMarker
inline fun CONDITION.default(
    crossinline block: CONDITION.DEFAULT.() -> Unit
): Unit = CONDITION.DEFAULT(
    parentContainer = this,
    consumer = consumer
).visit(block)

class CONDITION(
    initialAttributes: Map<String, String> = emptyMap(),
    private val parentContainer: Container,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Condition",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), Container {
    override val width: Int get() = parentContainer.width
    override val height: Int get() = parentContainer.height

    class EXPRESSIONS(
        initialAttributes: Map<String, String> = emptyMap(),
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "Expressions",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
    )

    class EXPRESSION(
        initialAttributes: Map<String, String>,
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "Expression",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
    )

    class COMPARE(
        initialAttributes: Map<String, String>,
        private val parentContainer: Container,
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "Compare",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
    ), CompareScope {
        override val width: Int get() = parentContainer.width
        override val height: Int get() = parentContainer.height
    }

    class DEFAULT(
        initialAttributes: Map<String, String> = emptyMap(),
        private val parentContainer: Container,
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "Default",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
    ), CompareScope {
        override val width: Int get() = parentContainer.width
        override val height: Int get() = parentContainer.height
    }
}
