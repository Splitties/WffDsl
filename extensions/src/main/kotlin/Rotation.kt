package splitties.wff.extensions

import splitties.wff.Container
import splitties.wff.centerX
import splitties.wff.centerY
import kotlin.math.cos
import kotlin.math.sin
import splitties.wff.common.attributes.ArithmeticExpression as Exp

fun Container.rotateCoordinates(
    x: Float,
    y: Float,
    angleInDegrees: Float,
): Pair<Float, Float> = rotateCoordinates(
    cx = centerX,
    cy = centerY,
    x = x,
    y = y,
    angleInDegrees = angleInDegrees
)

fun rotateCoordinates(
    cx: Float,
    cy: Float,
    x: Float,
    y: Float,
    angleInDegrees: Float,
): Pair<Float, Float> {
    val angle = Math.toRadians(angleInDegrees.toDouble())

    val offsetX = x - cx // Offset backwards to match the center of the Unit Circle.
    val offsetY = y - cy // Offset backwards to match the center of the Unit Circle.

    val offsetRotatedX = offsetX * cos(angle) - offsetY * sin(angle)
    val offsetRotatedY = offsetX * sin(angle) + offsetY * cos(angle)

    val rotatedX = offsetRotatedX + cx // Offset back, forward.
    val rotatedY = offsetRotatedY + cy // Offset back, forward.
    return rotatedX.toFloat() to rotatedY.toFloat()
}

fun Container.rotateCoordinates(
    x: Float,
    y: Float,
    angleInDegrees: Exp.Float,
): Pair<Exp.Float, Exp.Float> = rotateCoordinates(
    cx = centerX,
    cy = centerY,
    x = x,
    y = y,
    angleInDegrees = angleInDegrees
)

fun rotateCoordinates(
    cx: Float,
    cy: Float,
    x: Float,
    y: Float,
    angleInDegrees: Exp.Float,
): Pair<Exp.Float, Exp.Float> {
    val angle = Exp {
        (angleInDegrees).toRadians()
    }

    val offsetX = Exp(x - cx) // Offset backwards to match the center of the Unit Circle.
    val offsetY = Exp(y - cy) // Offset backwards to match the center of the Unit Circle.

    val offsetRotatedX = Exp { offsetX * cos(angle) - offsetY * sin(angle) }
    val offsetRotatedY = Exp { offsetX * sin(angle) + offsetY * cos(angle) }

    val rotatedX = Exp { offsetRotatedX + cx } // Offset back, forward.
    val rotatedY = Exp { offsetRotatedY + cy } // Offset back, forward.
    return rotatedX to rotatedY
}

fun Container.rotateCoordinates(
    x: Exp.Float,
    y: Exp.Float,
    angleInDegrees: Exp.Float,
): Pair<Exp.Float, Exp.Float> = rotateCoordinates(
    cx = Exp(centerX),
    cy = Exp(centerY),
    x = x,
    y = y,
    angleInDegrees = angleInDegrees
)

fun rotateCoordinates(
    cx: Exp.Float,
    cy: Exp.Float,
    x: Exp.Float,
    y: Exp.Float,
    angleInDegrees: Exp.Float,
): Pair<Exp.Float, Exp.Float> {
    val angle = Exp {
        angleInDegrees.toRadians()
    }

    val offsetX = Exp { p(x - cx) } // Offset backwards to match the center of the Unit Circle.
    val offsetY = Exp { p(y - cy) } // Offset backwards to match the center of the Unit Circle.

    val offsetRotatedX = Exp { offsetX * cos(angle) - offsetY * sin(angle) }
    val offsetRotatedY = Exp { offsetX * sin(angle) + offsetY * cos(angle) }

    val rotatedX = Exp { offsetRotatedX + cx } // Offset back, forward.
    val rotatedY = Exp { offsetRotatedY + cy } // Offset back, forward.
    return rotatedX to rotatedY
}
