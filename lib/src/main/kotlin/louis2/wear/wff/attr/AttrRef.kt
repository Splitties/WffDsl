package louis2.wear.wff.attr

import louis2.wear.wff.common.attributes.ArithmeticExpression

@Suppress("unused") // Used for type safety and inference.
class AttrRef<T : ArithmeticExpression> internal constructor(
    val name: String
)

