package louis2.wear.wff.common.attributes

import louis2.wear.wff.common.attributes.ArithmeticExpression
import louis2.wear.wff.common.attributes.ArithmeticExpression as Exp

sealed interface ArithmeticExpression {

    companion object {
        inline operator fun <T : ArithmeticExpression> invoke(
            block: ArithmeticExpressionScope.() -> T
        ): T = ArithmeticExpressionScope.block()
    }

    override fun toString(): kotlin.String
    fun interface Float : ArithmeticExpression
    fun interface Int : ArithmeticExpression
    fun interface Boolean : ArithmeticExpression
    fun interface String : ArithmeticExpression
}

data object ArithmeticExpressionScope {
    // "l" stands for "literal" as in "number literal".
    val Int.l get() = Exp.Int { this.toString() }
    val Float.l get() = Exp.Float { this.toString() }
    val Double.l get() = Exp.Float { this.toString() }

    fun Exp.Int.asFloat() = Exp.Float { toString() }

    fun round(input: Exp.Float) = Exp.Int { "round($input)" }
    fun floor(input: Exp.Float) = Exp.Float { "floor($input)" }
    fun ceil(input: Exp.Float) = Exp.Float { "ceil($input)" }
    fun fract(input: Exp.Float) = Exp.Float { "fract($input)" }

    fun sin(input: Exp.Float) = Exp.Float { "sin($input)" }
    fun cos(input: Exp.Float) = Exp.Float { "cos($input)" }
    fun tan(input: Exp.Float) = Exp.Float { "tan($input)" }
    fun asin(input: Exp.Float) = Exp.Float { "asin($input)" }
    fun acos(input: Exp.Float) = Exp.Float { "acos($input)" }
    fun atan(input: Exp.Float) = Exp.Float { "atan($input)" }

    fun abs(input: Exp.Float) = Exp.Float { "abs($input)" }
    fun abs(input: Exp.Int) = Exp.Int { "abs($input)" }

    fun clamp(
        value: Exp.Float,
        min: Exp.Float,
        max: Exp.Float
    ) = Exp.Float { "clamp($value, $min, $max)" }

    fun clamp(
        value: Exp.Int,
        min: Exp.Int,
        max: Exp.Int
    ) = Exp.Int { "clamp($value, $min, $max)" }

    fun rand(from: Exp.Float, to: Exp.Float) = Exp.Float { "rand($from, $to)" }
    fun rand(from: Exp.Int, to: Exp.Int) = Exp.Int { "rand($from, $to)" }

    fun log(input: Exp.Float) = Exp.Float { "log($input)" }
    fun log2(input: Exp.Float) = Exp.Float { "log2($input)" }
    fun log10(input: Exp.Float) = Exp.Float { "log10($input)" }

    fun sqrt(input: Exp.Float) = Exp.Float { "sqrt($input)" }
    fun sqrt(input: Exp.Int) = Exp.Float { "sqrt($input)" }
    fun cbrt(input: Exp.Float) = Exp.Float { "cbrt($input)" }
    fun cbrt(input: Exp.Int) = Exp.Float { "cbrt($input)" }

    fun exp(input: Exp.Float) = Exp.Float { "exp($input)" }
    fun exp(input: Exp.Int) = Exp.Float { "exp($input)" }
    fun expm1(input: Exp.Float) = Exp.Float { "expm1($input)" }
    fun expm1(input: Exp.Int) = Exp.Float { "expm1($input)" }

    fun deg(input: Exp.Float) = Exp.Float { "deg($input)" }
    fun rad(input: Exp.Float) = Exp.Float { "rad($input)" }
    fun rad(input: Exp.Int) = Exp.Float { "rad($input)" }

    // ^ 1/3 (number literal + expression)
    fun Float.pow(x: Exp.Float) = Exp.Float { "pow($this, $x)" }
    fun Int.pow(x: Exp.Float) = Exp.Float { "pow($this, $x)" }
    fun Float.pow(x: Exp.Int) = Exp.Float { "pow($this, $x)" }
    fun Int.pow(x: Exp.Int) = Exp.Float { "pow($this, $x)" }
    // ^ 2/3 (expression + number literal)
    fun Exp.Float.pow(x: Float) = Exp.Float { "pow($this, $x)" }
    fun Exp.Int.pow(x: Float) = Exp.Float { "pow($this, $x)" }
    fun Exp.Float.pow(x: Int) = Exp.Float { "pow($this, $x)" }
    fun Exp.Int.pow(x: Int) = Exp.Float { "pow($this, $x)" }
    // ^ 3/3 (expression + expression)
    fun Exp.Float.pow(x: Exp.Float) = Exp.Float { "pow($this, $x)" }
    fun Exp.Int.pow(x: Exp.Float) = Exp.Float { "pow($this, $x)" }
    fun Exp.Float.pow(x: Exp.Int) = Exp.Float { "pow($this, $x)" }
    fun Exp.Int.pow(x: Exp.Int) = Exp.Float { "pow($this, $x)" }

    // + 1/3 (number literal + expression)
    operator fun Float.plus(other: Exp.Float) = Exp.Float { "$this + $other" }
    operator fun Float.plus(other: Exp.Int) = Exp.Float { "$this + $other" }
    operator fun Int.plus(other: Exp.Int) = Exp.Int { "$this + $other" }
    operator fun Int.plus(other: Exp.Float) = Exp.Float { "$this + $other" }
    // + 2/3 (expression + number literal)
    operator fun Exp.Float.plus(other: Float) = Exp.Float { "$this + $other" }
    operator fun Exp.Float.plus(other: Int) = Exp.Float { "$this + $other" }
    operator fun Exp.Int.plus(other: Int) = Exp.Int { "$this + $other" }
    operator fun Exp.Int.plus(other: Float) = Exp.Float { "$this + $other" }
    // + 3/3 (expression + expression)
    operator fun Exp.Float.plus(other: Exp.Float) = Exp.Float { "$this + $other" }
    operator fun Exp.Int.plus(other: Exp.Float) = Exp.Float { "$this + $other" }
    operator fun Exp.Float.plus(other: Exp.Int) = Exp.Float { "$this + $other" }
    operator fun Exp.Int.plus(other: Exp.Int) = Exp.Int { "$this + $other" }

    // - 1/3 (number literal + expression)
    operator fun Float.minus(other: Exp.Float) = Exp.Float { "$this - $other" }
    operator fun Int.minus(other: Exp.Float) = Exp.Float { "$this - $other" }
    operator fun Float.minus(other: Exp.Int) = Exp.Float { "$this - $other" }
    operator fun Int.minus(other: Exp.Int) = Exp.Int { "$this - $other" }
    // - 2/3 (expression + number literal)
    operator fun Exp.Float.minus(other: Float) = Exp.Float { "$this - $other" }
    operator fun Exp.Int.minus(other: Float) = Exp.Float { "$this - $other" }
    operator fun Exp.Float.minus(other: Int) = Exp.Float { "$this - $other" }
    operator fun Exp.Int.minus(other: Int) = Exp.Int { "$this - $other" }
    // - 3/3 (expression + expression)
    operator fun Exp.Float.minus(other: Exp.Float) = Exp.Float { "$this - $other" }
    operator fun Exp.Int.minus(other: Exp.Float) = Exp.Float { "$this - $other" }
    operator fun Exp.Float.minus(other: Exp.Int) = Exp.Float { "$this - $other" }
    operator fun Exp.Int.minus(other: Exp.Int) = Exp.Int { "$this - $other" }

    // * 1/3 (number literal + expression)
    operator fun Float.times(other: Exp.Float) = Exp.Float { "$this * $other" }
    operator fun Int.times(other: Exp.Float) = Exp.Float { "$this * $other" }
    operator fun Float.times(other: Exp.Int) = Exp.Float { "$this * $other" }
    operator fun Int.times(other: Exp.Int) = Exp.Int { "$this * $other" }
    // * 2/3 (expression + number literal)
    operator fun Exp.Float.times(other: Float) = Exp.Float { "$this * $other" }
    operator fun Exp.Int.times(other: Float) = Exp.Float { "$this * $other" }
    operator fun Exp.Float.times(other: Int) = Exp.Float { "$this * $other" }
    operator fun Exp.Int.times(other: Int) = Exp.Int { "$this * $other" }
    // * 3/3 (expression + expression)
    operator fun Exp.Float.times(other: Exp.Float) = Exp.Float { "$this * $other" }
    operator fun Exp.Int.times(other: Exp.Float) = Exp.Float { "$this * $other" }
    operator fun Exp.Float.times(other: Exp.Int) = Exp.Float { "$this * $other" }
    operator fun Exp.Int.times(other: Exp.Int) = Exp.Int { "$this * $other" }

    // / 1/3 (number literal + expression)
    operator fun Float.div(other: Exp.Float) = Exp.Float { "$this / $other" }
    operator fun Int.div(other: Exp.Float) = Exp.Float { "$this / $other" }
    operator fun Float.div(other: Exp.Int) = Exp.Float { "$this / $other" }
    /** Decimals are kept for int / int, as per Watch Face Format documentation. */
    operator fun Int.div(other: Exp.Int) = Exp.Float { "$this / $other" }
    // / 2/3 (expression + number literal)
    operator fun Exp.Float.div(other: Float) = Exp.Float { "$this / $other" }
    operator fun Exp.Int.div(other: Float) = Exp.Float { "$this / $other" }
    operator fun Exp.Float.div(other: Int) = Exp.Float { "$this / $other" }
    /** Decimals are kept for int / int, as per Watch Face Format documentation. */
    operator fun Exp.Int.div(other: Int) = Exp.Float { "$this / $other" }
    // / 3/3 (expression + expression)
    operator fun Exp.Float.div(other: Exp.Float) = Exp.Float { "$this / $other" }
    operator fun Exp.Int.div(other: Exp.Float) = Exp.Float { "$this / $other" }
    operator fun Exp.Float.div(other: Exp.Int) = Exp.Float { "$this / $other" }
    /** Decimals are kept for int / int, as per Watch Face Format documentation. */
    operator fun Exp.Int.div(other: Exp.Int) = Exp.Float { "$this / $other" }

    // % 1/3 (number literal + expression)
    operator fun Float.rem(other: Exp.Float) = Exp.Float { "$this % $other" }
    operator fun Int.rem(other: Exp.Float) = Exp.Float { "$this % $other" }
    operator fun Float.rem(other: Exp.Int) = Exp.Float { "$this % $other" }
    operator fun Int.rem(other: Exp.Int) = Exp.Int { "$this % $other" }
    // % 2/3 (expression + number literal)
    operator fun Exp.Float.rem(other: Float) = Exp.Float { "$this % $other" }
    operator fun Exp.Int.rem(other: Float) = Exp.Float { "$this % $other" }
    operator fun Exp.Float.rem(other: Int) = Exp.Float { "$this % $other" }
    operator fun Exp.Int.rem(other: Int) = Exp.Int { "$this % $other" }
    // % 3/3 (expression + expression)
    operator fun Exp.Float.rem(other: Exp.Float) = Exp.Float { "$this % $other" }
    operator fun Exp.Int.rem(other: Exp.Float) = Exp.Float { "$this % $other" }
    operator fun Exp.Float.rem(other: Exp.Int) = Exp.Float { "$this % $other" }
    operator fun Exp.Int.rem(other: Exp.Int) = Exp.Int { "$this % $other" }

    /** Decimals are kept for int / int, as per Watch Face Format documentation. */
    fun Exp.Int.inv() = Exp.Int { "~$this" }

    operator fun Exp.Int.not() = Exp.Boolean { "!$this" }

    @Suppress("DANGEROUS_CHARACTERS")
    infix fun Int.`|`(other: Exp.Int) = Exp.Int { "$this | $other" }

    @Suppress("DANGEROUS_CHARACTERS")
    infix fun Exp.Int.`|`(other: Int) = Exp.Int { "$this | $other" }

    @Suppress("DANGEROUS_CHARACTERS")
    infix fun Exp.Int.`|`(other: Exp.Int) = Exp.Int { "$this | $other" }

    @Suppress("DANGEROUS_CHARACTERS")
    infix fun Exp.Boolean.`||`(other: Exp.Boolean) = Exp.Boolean { "$this || $other" }

    infix fun Int.`&`(other: Exp.Int) = Exp.Int { "$this & $other" }
    infix fun Exp.Int.`&`(other: Int) = Exp.Int { "$this & $other" }
    infix fun Exp.Int.`&`(other: Exp.Int) = Exp.Int { "$this & $other" }

    infix fun Exp.Boolean.`&&`(other: Exp.Boolean) = Exp.Boolean { "$this && $other" }

    fun p(expression: Exp.Float) = Exp.Float { "($expression)" }
    fun p(expression: Exp.Int) = Exp.Int { "($expression)" }
    fun p(expression: Exp.Boolean) = Exp.Boolean { "($expression)" }

    // < 1/3 (number literal + expression)
    infix fun Float.lt(other: Exp.Float) = Exp.Boolean { "$this < $other" }
    infix fun Int.lt(other: Exp.Float) = Exp.Boolean { "$this < $other" }
    infix fun Float.lt(other: Exp.Int) = Exp.Boolean { "$this < $other" }
    infix fun Int.lt(other: Exp.Int) = Exp.Boolean { "$this < $other" }
    // < 2/3 (expression + number literal)
    infix fun Exp.Float.lt(other: Float) = Exp.Boolean { "$this < $other" }
    infix fun Exp.Int.lt(other: Float) = Exp.Boolean { "$this < $other" }
    infix fun Exp.Float.lt(other: Int) = Exp.Boolean { "$this < $other" }
    infix fun Exp.Int.lt(other: Int) = Exp.Boolean { "$this < $other" }
    // < 3/3 (expression + expression)
    infix fun Exp.Float.lt(other: Exp.Float) = Exp.Boolean { "$this < $other" }
    infix fun Exp.Int.lt(other: Exp.Float) = Exp.Boolean { "$this < $other" }
    infix fun Exp.Float.lt(other: Exp.Int) = Exp.Boolean { "$this < $other" }
    infix fun Exp.Int.lt(other: Exp.Int) = Exp.Boolean { "$this < $other" }

    // > 1/3 (number literal + expression)
    infix fun Float.gt(other: Exp.Float) = Exp.Boolean { "$this > $other" }
    infix fun Int.gt(other: Exp.Float) = Exp.Boolean { "$this > $other" }
    infix fun Float.gt(other: Exp.Int) = Exp.Boolean { "$this > $other" }
    infix fun Int.gt(other: Exp.Int) = Exp.Boolean { "$this > $other" }
    // > 2/3 (expression + number literal)
    infix fun Exp.Float.gt(other: Float) = Exp.Boolean { "$this > $other" }
    infix fun Exp.Int.gt(other: Float) = Exp.Boolean { "$this > $other" }
    infix fun Exp.Float.gt(other: Int) = Exp.Boolean { "$this > $other" }
    infix fun Exp.Int.gt(other: Int) = Exp.Boolean { "$this > $other" }
    // > 3/3 (expression + expression)
    infix fun Exp.Float.gt(other: Exp.Float) = Exp.Boolean { "$this > $other" }
    infix fun Exp.Int.gt(other: Exp.Float) = Exp.Boolean { "$this > $other" }
    infix fun Exp.Float.gt(other: Exp.Int) = Exp.Boolean { "$this > $other" }
    infix fun Exp.Int.gt(other: Exp.Int) = Exp.Boolean { "$this > $other" }

    // <= 1/3 (number literal + expression)
    infix fun Float.lte(other: Exp.Float) = Exp.Boolean { "$this <= $other" }
    infix fun Int.lte(other: Exp.Float) = Exp.Boolean { "$this <= $other" }
    infix fun Float.lte(other: Exp.Int) = Exp.Boolean { "$this <= $other" }
    infix fun Int.lte(other: Exp.Int) = Exp.Boolean { "$this <= $other" }
    // <= 2/3 (expression + number literal)
    infix fun Exp.Float.lte(other: Float) = Exp.Boolean { "$this <= $other" }
    infix fun Exp.Int.lte(other: Float) = Exp.Boolean { "$this <= $other" }
    infix fun Exp.Float.lte(other: Int) = Exp.Boolean { "$this <= $other" }
    infix fun Exp.Int.lte(other: Int) = Exp.Boolean { "$this <= $other" }
    // <= 3/3 (expression + expression)
    infix fun Exp.Float.lte(other: Exp.Float) = Exp.Boolean { "$this <= $other" }
    infix fun Exp.Int.lte(other: Exp.Float) = Exp.Boolean { "$this <= $other" }
    infix fun Exp.Float.lte(other: Exp.Int) = Exp.Boolean { "$this <= $other" }
    infix fun Exp.Int.lte(other: Exp.Int) = Exp.Boolean { "$this <= $other" }

    // >= 1/3 (number literal + expression)
    infix fun Float.gte(other: Exp.Float) = Exp.Boolean { "$this >= $other" }
    infix fun Int.gte(other: Exp.Float) = Exp.Boolean { "$this >= $other" }
    infix fun Float.gte(other: Exp.Int) = Exp.Boolean { "$this >= $other" }
    infix fun Int.gte(other: Exp.Int) = Exp.Boolean { "$this >= $other" }
    // >= 2/3 (expression + number literal)
    infix fun Exp.Float.gte(other: Float) = Exp.Boolean { "$this >= $other" }
    infix fun Exp.Int.gte(other: Float) = Exp.Boolean { "$this >= $other" }
    infix fun Exp.Float.gte(other: Int) = Exp.Boolean { "$this >= $other" }
    infix fun Exp.Int.gte(other: Int) = Exp.Boolean { "$this >= $other" }
    // >= 3/3 (expression + expression)
    infix fun Exp.Float.gte(other: Exp.Float) = Exp.Boolean { "$this >= $other" }
    infix fun Exp.Int.gte(other: Exp.Float) = Exp.Boolean { "$this >= $other" }
    infix fun Exp.Float.gte(other: Exp.Int) = Exp.Boolean { "$this >= $other" }
    infix fun Exp.Int.gte(other: Exp.Int) = Exp.Boolean { "$this >= $other" }

    fun ternary(condition: Exp.Boolean, ifTrue: Exp.Float, ifFalse: Exp.Float) = Exp.Float {
        "$condition ? $ifTrue : $ifFalse"
    }

    fun ternary(condition: Exp.Boolean, ifTrue: Exp.Int, ifFalse: Exp.Float) = Exp.Float {
        "$condition ? $ifTrue : $ifFalse"
    }

    fun ternary(condition: Exp.Boolean, ifTrue: Exp.Float, ifFalse: Exp.Int) = Exp.Float {
        "$condition ? $ifTrue : $ifFalse"
    }

    fun ternary(condition: Exp.Boolean, ifTrue: Exp.Int, ifFalse: Exp.Int) = Exp.Int {
        "$condition ? $ifTrue : $ifFalse"
    }

    fun ternary(condition: Exp.Boolean, ifTrue: Exp.String, ifFalse: Exp.String) = Exp.String {
        "$condition ? $ifTrue : $ifFalse"
    }

    // == 1/3 (number literal + expression)
    infix fun Float.`==`(other: Exp.Float) = Exp.Boolean { "$this == $other" }
    infix fun Int.`==`(other: Exp.Float) = Exp.Boolean { "$this == $other" }
    infix fun Int.`==`(other: Exp.Int) = Exp.Boolean { "$this == $other" }
    // == 2/3 (expression + number literal)
    infix fun Exp.Float.`==`(other: Float) = Exp.Boolean { "$this == $other" }
    infix fun Exp.Float.`==`(other: Int) = Exp.Boolean { "$this == $other" }
    infix fun Exp.Int.`==`(other: Int) = Exp.Boolean { "$this == $other" }
    // == 3/3 (expression + expression)
    infix fun Exp.Float.`==`(other: Exp.Float) = Exp.Boolean { "$this == $other" }
    infix fun Exp.Int.`==`(other: Exp.Float) = Exp.Boolean { "$this == $other" }
    infix fun Exp.Float.`==`(other: Exp.Int) = Exp.Boolean { "$this == $other" }
    infix fun Exp.Int.`==`(other: Exp.Int) = Exp.Boolean { "$this == $other" }
    infix fun Exp.Boolean.`==`(other: Exp.Boolean) = Exp.Boolean { "$this == $other" }

    // != 1/3 (number literal + expression)
    infix fun Float.`!=`(other: Exp.Float) = Exp.Boolean { "$this != $other" }
    infix fun Int.`!=`(other: Exp.Float) = Exp.Boolean { "$this != $other" }
    infix fun Int.`!=`(other: Exp.Int) = Exp.Boolean { "$this != $other" }
    // != 2/3 (expression + number literal)
    infix fun Exp.Float.`!=`(other: Float) = Exp.Boolean { "$this != $other" }
    infix fun Exp.Float.`!=`(other: Int) = Exp.Boolean { "$this != $other" }
    infix fun Exp.Int.`!=`(other: Int) = Exp.Boolean { "$this != $other" }
    // != 3/3 (expression + expression)
    infix fun Exp.Float.`!=`(other: Exp.Float) = Exp.Boolean { "$this != $other" }
    infix fun Exp.Int.`!=`(other: Exp.Float) = Exp.Boolean { "$this != $other" }
    infix fun Exp.Float.`!=`(other: Exp.Int) = Exp.Boolean { "$this != $other" }
    infix fun Exp.Int.`!=`(other: Exp.Int) = Exp.Boolean { "$this != $other" }
    infix fun Exp.Boolean.`!=`(other: Exp.Boolean) = Exp.Boolean { "$this != $other" }
}
