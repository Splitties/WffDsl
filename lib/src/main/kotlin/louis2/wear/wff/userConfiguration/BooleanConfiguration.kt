package louis2.wear.wff.userConfiguration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*

/**
 * A Boolean Configuration gives the user an option that can be turned on or off when customizing the watch face in the watch face editor.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/user-configuration/boolean-configuration)
 */
@WffTagMarker
inline fun SupportsBooleanConfiguration.booleanConfiguration(
    id: String,
    crossinline block: BOOLEANCONFIGURATION.() -> Unit
): Unit = BOOLEANCONFIGURATION(
    initialAttributes = attributesMapOf("id", id),
    parentContainer = this,
    consumer = consumer
).visit(block)

@WffTagMarker
inline fun BOOLEANCONFIGURATION.booleanOption(
    value: Boolean,
    crossinline block: BOOLEANCONFIGURATION.BOOLEANOPTION.() -> Unit
): Unit = BOOLEANCONFIGURATION.BOOLEANOPTION(
    initialAttributes = attributesMapOf("id", value.toString().uppercase()),
    parentContainer = this,
    consumer = consumer
).visit(block)

class BOOLEANCONFIGURATION(
    initialAttributes: Map<String, String> = emptyMap(),
    private val parentContainer: Container,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "BooleanConfiguration",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), Container {
    override val width: Int get() = parentContainer.width
    override val height: Int get() = parentContainer.height

    class BOOLEANOPTION(
        initialAttributes: Map<String, String> = emptyMap(),
        private val parentContainer: Container,
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "BooleanOption",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
    ), SupportsPart, SupportsGroup, SupportsConditions, SupportsClock {
        override val width: Int get() = parentContainer.width
        override val height: Int get() = parentContainer.height
    }
}
