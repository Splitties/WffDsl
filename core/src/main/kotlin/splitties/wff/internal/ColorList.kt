package splitties.wff.internal

import splitties.wff.Color

@PublishedApi
internal fun List<Color>.xmlValue(): String {
    return joinToString(separator = " ") { it.xmlValue() }
}
