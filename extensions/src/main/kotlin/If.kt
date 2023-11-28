package splitties.wff.extensions

import splitties.wff.CompareScope
import splitties.wff.SupportsConditions
import splitties.wff.common.*
import splitties.wff.common.attributes.ArithmeticExpression as Exp

context (SupportsConditions)
inline fun Exp.Boolean.ifTrue(
    crossinline block: CompareScope.() -> Unit
) {
    condition {
        expressions {
            expression("0", this@ifTrue)
        }
        compare("0", block)
    }
}

context (SupportsConditions)
inline fun Exp.Boolean.ifFalse(
    crossinline block: CompareScope.() -> Unit
) {
    condition {
        expressions {
            expression("0", Exp { !p(this@ifFalse) })
        }
        compare("0") { block() }
    }
}
