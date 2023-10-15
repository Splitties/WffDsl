package louis2.wear.wff.internal

import louis2.wear.wff.Container
import louis2.wear.wff.XMLTag

internal fun <T> T.w(): Int where T : Container, T : XMLTag {
    return attributes.getValue("width").toInt()
}

internal fun <T> T.h(): Int where T : Container, T : XMLTag {
    return attributes.getValue("height").toInt()
}
