package louis2.wear.wff.attr

class AnalogHandAttrs private constructor() : AttrsHost() {
    companion object {
        operator fun invoke() = instance
        private val instance = AnalogHandAttrs()
    }
    val resource by string()
    val x by int()
    val y by int()
    val width by int()
    val height by int()
    val pivotX by float()
    val pivotY by float()
    val alpha by int()
}
