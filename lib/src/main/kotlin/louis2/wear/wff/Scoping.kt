package louis2.wear.wff

import kotlinx.html.Tag

interface Container : Tag {
    val width: Int
    val height: Int
}

interface SupportsConditions : Container

interface CompareScope : Container, SupportsGroup, SupportsPart, SupportsClock, SupportsConditions

interface SupportsGroup : Container

interface SupportsPart : Container

interface SupportsClock : Container

interface SupportsVariants : Tag

interface SupportsBooleanConfiguration : Container

interface AnalogHand : SupportsVariants

interface SupportsLocalization : Tag

interface TextScope : Tag

interface TextFormatterGroup : SupportsTemplate

interface TextDecoration : TextFormatterGroup

interface TextDecorationGroup : Tag

interface SupportsTemplate : Tag

interface Transformable : Tag

interface SupportsLaunch : Tag

interface SupportsGyro : Tag

interface SupportsScreenReader : Tag

interface Part : SupportsLocalization, Transformable, SupportsVariants, SupportsGyro, SupportsLaunch, SupportsScreenReader

interface StrokeAble : Tag
interface FillAble : Tag

interface SupportsGradients : Tag
