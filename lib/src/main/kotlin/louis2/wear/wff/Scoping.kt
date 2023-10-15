package louis2.wear.wff

import kotlinx.html.Tag

sealed interface SceneOrGroup : Tag

interface VariantScope : Tag

interface AnalogHand : VariantScope
