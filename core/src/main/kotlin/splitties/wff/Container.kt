package splitties.wff

context (Container, XMLTag)
internal fun w(): Int = attributes.getValue("width").toInt()

context (Container, XMLTag)
internal fun h(): Int = attributes.getValue("height").toInt()

val Container.centerX: Float get() = width / 2f
val Container.centerY: Float get() = height / 2f
