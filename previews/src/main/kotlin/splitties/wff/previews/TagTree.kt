package splitties.wff.previews

import kotlinx.html.Tag
import splitties.wff.SCENE
import splitties.wff.WATCHFACE
import splitties.wff.XMLTag
import splitties.wff.attr.AttrRef
import splitties.wff.clock.TIMETEXT
import splitties.wff.common.attributes.ArithmeticExpression

class TagTree {
    lateinit var watchface: WATCHFACE

    val childMap = mutableMapOf<Tag, MutableList<Tag>>()
    val tagContent = mutableMapOf<Tag, String>()
    val stack = mutableListOf<Tag>()

    fun push(tag: Tag) {
        if (tag is WATCHFACE) {
            watchface = tag
        } else {
            childMap.getOrPut(stack.last()) { mutableListOf() }.add(tag)
        }

        stack.add(tag)
    }

    fun pop(tag: Tag) {
        val removed = stack.removeLast()
        check(removed == tag)
    }

    fun content(content: CharSequence) {
        tagContent[stack.last()] = content.toString()
    }

    val WATCHFACE.scene: SCENE
        get() = childMap.getValue(this).find { it is SCENE } as SCENE

    val Tag.children: List<Tag>
        get() = childMap[this].orEmpty()

    val Tag.content: String?
        get() = tagContent[this]
}

val TIMETEXT.format: String
    get() = this.attributes.getValue("format")

val TIMETEXT.align: String
    get() = this.attributes.getValue("format")

context(XMLTag)
val AttrRef<ArithmeticExpression.Int>.value: Int
    get() {
        val value = attributes.getValue(this.name).toString().toIntOrNull()

        if (value == null) {
            println("Int value: $this ${this.name}")
        }

        return value ?: 0
    }