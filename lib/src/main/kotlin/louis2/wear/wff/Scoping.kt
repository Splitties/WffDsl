package louis2.wear.wff

import kotlinx.html.Tag
import louis2.wear.wff.attr.AttrsHost

interface Container {
    val width: Int
    val height: Int
}

interface SupportsConditions : Container, Tag

interface CompareScope : Tag, Container, SupportsGroup, SupportsPart, SupportsClock, SupportsConditions

interface SupportsGroup : Tag, Container

interface SupportsPart : Tag, Container

interface SupportsClock : Tag, Container

interface SupportsVariants : Tag {
    val attrs: AttrsHost
}

interface SupportsBooleanConfiguration : Tag, Container

interface SupportsListConfiguration : Tag, Container

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

interface SupportsImage : Tag
