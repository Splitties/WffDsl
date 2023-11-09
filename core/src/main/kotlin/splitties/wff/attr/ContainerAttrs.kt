package splitties.wff.attr

class ContainerAttrs private constructor() : AttrsHost() {
    companion object {
        operator fun invoke() = instance
        private val instance = ContainerAttrs()
    }
    val x by int()
    val y by int()
    val width by int()
    val height by int()
    val pivotX by float()
    val pivotY by float()
    val angle by float()
    val alpha by int()
    val scaleX by float()
    val scaleY by float()
}
