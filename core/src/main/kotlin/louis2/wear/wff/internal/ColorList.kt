package louis2.wear.wff.internal

import louis2.wear.wff.Color

@PublishedApi
internal fun List<Color>.xmlValue(): String {
    return joinToString(separator = " ") { it.xmlValue() }
}
