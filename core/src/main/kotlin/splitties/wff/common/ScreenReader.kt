package splitties.wff.common

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.SupportsScreenReader
import splitties.wff.WffTagMarker
import splitties.wff.XMLTag
import splitties.wff.common.attributes.ArithmeticExpression
import splitties.wff.internal.visit

/**
 * Provides support for the TalkBack screen reader, which users can activate to navigate a watch face using touch and voice input.
 *
 * Every group- or part-based element supports the `ScreenReader` element.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/common/screen-reader)
 */
@WffTagMarker
fun SupportsScreenReader.screenReader(
    stringId: String,
    block: (SCREENREADER.() -> Unit)? = null
): Unit = SCREENREADER(
    initialAttributes = attributesMapOf("stringId", stringId),
    emptyTag = block == null,
    consumer = consumer
).visit(block)

@WffTagMarker
fun SCREENREADER.parameter(
    expression: ArithmeticExpression.String
): Unit = SCREENREADER.PARAMETER(
    initialAttributes = attributesMapOf("expression", expression.toString()),
    consumer = consumer
).visit {}

class SCREENREADER(
    initialAttributes: Map<String, String>,
    emptyTag: Boolean,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "ScreenReader",
    consumer = consumer,
    initialAttributes = initialAttributes,
    inlineTag = false,
    emptyTag = emptyTag
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
