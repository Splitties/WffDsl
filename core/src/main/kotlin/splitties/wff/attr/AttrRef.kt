package splitties.wff.attr

import splitties.wff.common.attributes.ArithmeticExpression

@Suppress("unused") // Used for type safety and inference.
class AttrRef<T : ArithmeticExpression> internal constructor(
    val name: String
)

