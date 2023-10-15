package louis2.wear.wff

import kotlinx.html.Tag


interface Container : Tag {
    val width: Int
    val height: Int
}

sealed interface SceneOrGroup : Container

interface VariantScope : Tag

interface AnalogHand : VariantScope

interface LocalizationAware : Tag
