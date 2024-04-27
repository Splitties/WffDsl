package splitties.wff.previews

import kotlinx.html.Entities
import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.Unsafe
import kotlinx.html.org.w3c.dom.events.Event

class TagTreeConsumer: TagConsumer<TagTree> {
    val tree = TagTree()

    override fun finalize(): TagTree {
        return tree.also {
            println(it)
        }
    }

    override fun onTagAttributeChange(tag: Tag, attribute: String, value: String?) {
        println("onTagAttributeChange: $tag $attribute=$value")
    }

    override fun onTagComment(content: CharSequence) {
        println("onTagComment: $content")
    }

    override fun onTagContent(content: CharSequence) {
        println("onTagContent: $content")

        tree.content(content)
    }

    override fun onTagContentEntity(entity: Entities) {
        println("onTagContentEntity: $entity")
    }

    override fun onTagContentUnsafe(block: Unsafe.() -> Unit) {
        println("onTagContentUnsafe")
    }

    override fun onTagStart(tag: Tag) {
        println("onTagStart: $tag ${tag.attributes.toMap()}")

        tree.push(tag)
    }

    override fun onTagEvent(tag: Tag, event: String, value: (Event) -> Unit) {
        println("onTagEvent: $tag")
    }

    override fun onTagEnd(tag: Tag) {
        println("onTagEnd: $tag")

        tree.pop(tag)
    }
}

abstract class WffNode() {
    abstract val name: String

}

class Unknown(override val name: String): WffNode() {

}
