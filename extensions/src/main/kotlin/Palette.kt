package splitties.wff.extensions

import splitties.wff.Color

class Palette(
    val stableKey: String,
    val displayName: String = stableKey,
    val screenReaderText: String? = null,
    val icon: String? = null,
    val colors: List<Color.Static>
)
