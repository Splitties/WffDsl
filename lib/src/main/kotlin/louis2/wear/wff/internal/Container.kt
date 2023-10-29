package louis2.wear.wff.internal

import louis2.wear.wff.Container
import louis2.wear.wff.XMLTag

context (Container, XMLTag)
internal fun w(): Int = attributes.getValue("width").toInt()

context (Container, XMLTag)
internal fun h(): Int = attributes.getValue("height").toInt()
