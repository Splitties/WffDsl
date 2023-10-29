package louis2.wear.wff

context (Container, XMLTag)
internal fun w(): Int = attributes.getValue("width").toInt()

context (Container, XMLTag)
internal fun h(): Int = attributes.getValue("height").toInt()
