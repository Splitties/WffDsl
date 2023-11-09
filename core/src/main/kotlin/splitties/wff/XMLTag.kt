package splitties.wff

import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.impl.DelegatingMap

@WffTagMarker
open class XMLTag(
    override val tagName: String,
    override val consumer: TagConsumer<*>,
    initialAttributes: Map<String, String>,
    override val namespace: String? = null,
    override val inlineTag: Boolean,
    override val emptyTag: Boolean
) : Tag {

    override val attributes: DelegatingMap = DelegatingMap(initialAttributes, this) { consumer }

    override val attributesEntries: Collection<Map.Entry<String, String>>
        get() = attributes.immutableEntries
}
