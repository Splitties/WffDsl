package splitties.wff

import splitties.wff.clock.*
import splitties.wff.common.*
import splitties.wff.common.attributes.ArithmeticExpression
import splitties.wff.common.attributes.SourceType
import splitties.wff.common.transform.animation
import splitties.wff.common.transform.gyro
import splitties.wff.common.transform.transform
import splitties.wff.common.variant.ambientVariant
import splitties.wff.complication.*
import splitties.wff.group.configuration.booleanConfiguration
import splitties.wff.group.configuration.booleanOption
import splitties.wff.group.part.animatedImage.*
import splitties.wff.group.part.draw.gradient.sweepGradient
import splitties.wff.group.part.draw.partDraw
import splitties.wff.group.part.draw.shape.arc
import splitties.wff.group.part.draw.style.stroke
import splitties.wff.group.part.image.image
import splitties.wff.group.part.image.imageFilter.hsbFilter
import splitties.wff.group.part.image.imageFilter.imageFilters
import splitties.wff.group.part.image.partImage
import splitties.wff.group.part.text.font
import splitties.wff.group.part.text.formatter.template
import splitties.wff.group.part.text.partText
import splitties.wff.group.part.text.text
import splitties.wff.userConfiguration.*
import kotlin.math.PI

context(WatchFaceDsl<T>)
internal fun <T> sampleWatchFace(
    w: Int = 480,
    h: Int = 480
): T = watchFace(width = w, height = h) {
    bitmapFonts {
        bitmapFont(name = "whatever") {
            for (c in 0..9) character(
                name = "$c",
                resource = "whatever/$c.png",
                width = 20,
                height = 30
            )
            word(
                name = "11",
                resource = "whatever/11-fancy.png",
                width = 20 * 2,
                height = 30
            )
        }
    }
    clockType(type = ClockType.DIGITAL)
    previewTime("07:35:15")
    stepGoal(9_000)
    userConfigurations {
        booleanConfiguration(
            id = "something",
            displayName = "Something",
            defaultValue = true
        )
        booleanConfiguration(
            id = "something.whatever",
            displayName = "Whatever if something",
            defaultValue = true
        )
        listConfiguration(
            id = "list.stuff",
            displayName = "Stuff",
            defaultValue = 0
        ) {
            listOption(
                id = 0,
                displayName = "First option"
            )
            listOption(
                id = 1,
                displayName = "Second option"
            )
            listOption(
                id = 2,
                displayName = "Third option"
            )
        }
        colorConfiguration(
            id = "bg",
            displayName = "Some color choices",
            defaultValue = "dark_blue"
        ) {
            colorOption(
                id = "dark_blue",
                color = Color.rgb(0x00_00_50)
            )
            colorOption(
                id = "black",
                color = Color.black
            )
        }
        colorConfiguration(
            id = "some_color_set",
            displayName = "Some color choices",
            defaultValue = "red&yellow"
        ) {
            colorOption(
                id = "red&yellow",
                colors = listOf(Color.rgb(0xFF0000), Color.rgb(0xFFFF00))
            )
            colorOption(
                id = "blue&yellow",
                colors = listOf(Color.rgb(0x0000FF), Color.rgb(0xFFFF00))
            )
        }
    }
    scene(backgroundColor = Color.configurable(id = "bg")) {
        val supportedTypes = listOf(ComplicationType.SHORT_TEXT, ComplicationType.RANGED_VALUE, ComplicationType.MONOCHROMATIC_IMAGE)
        complicationSlot(
            slotId = 0,
            supportedTypes = supportedTypes
        ) {
            boundingOval()
            defaultProviderPolicy(defaultSystemProvider = SystemProvider.WATCH_BATTERY, ComplicationType.RANGED_VALUE)
            complication(ComplicationType.SHORT_TEXT) {
                partText {
                    transform(attrs.x, { ternary(0.l `==` 0, 1, 2) }) {
                        animation(duration = 0.2f)
                    }
                    transform(attrs.y, ArithmeticExpression { ternary(0.l `==` 0, 0.l, 1.l) })
                    gyro(scaleX = ArithmeticExpression {
                        (5f / 90) * clamp(SourceType.Sensors.ACCELEROMETER_ANGLE_X, 0, 90) +
                                (5f / 90) * clamp(SourceType.Sensors.ACCELEROMETER_ANGLE_X, -90, 0)
                    })
                    text {
                        font(size = 30f) {
                            template(text = "Salut")
                        }
                    }
                }
            }
            complication(ComplicationType.RANGED_VALUE) {
                partImage {
                    image(resource = COMPLICATION.MONOCHROMATIC_IMAGE)
                    imageFilters { hsbFilter(hueRotate = 120f) }
                }
                partText { text { font(size = 30f) { template(text = COMPLICATION.TEXT) } } }
                partDraw {
                    arc(
                        centerX = centerX,
                        centerY = centerY,
                        width = this.width.toFloat(),
                        height = this.height.toFloat(),
                        startAngle = 0f,
                        endAngle = 0f
                    ) {
                        transform(attrs.startAngle, expression = { ternary(0.l `==` 0, 0f.l, 1.l) })
                        val circumference = this.width * PI
                        val dashesCount = 12
                        val dashMaxSpace = (circumference / dashesCount).toFloat()
                        stroke(
                            thickness = 8f,
                            dashIntervals = floatArrayOf(8f, dashMaxSpace - 8f)
                        ) {
                            sweepGradient(
                                centerX = this@arc.width / 2f,
                                centerY = this@arc.height / 2f,
                                startAngle = 0f,
                                endAngle = 360f,
                                colors = listOf(Color.rgb(0xFFFFFF), Color.rgb(0x00BBFF))
                            )
                        }
                    }
                }
            }
        }
        condition { default { condition { } } }
        booleanConfiguration(id = "something") {
            booleanOption(value = true) {
                group {
                    booleanConfiguration(id = "something.whatever") {
                        booleanOption(value = true) {
                            group {}
                        }
                        booleanOption(value = false) {
                            group {}
                        }
                    }
                }
            }
        }
        group(id = "g1") {
            partAnimatedImage {
                thumbnail("@drawable/whatever_thumb")
                screenReader("@string/whatever")
                animatedImages {
                    animatedImage(resource = "@drawable/whatever", ANIMATEDIMAGE.Format.AGIF)
                    sequenceImages {  }
                }
            }
            partAnimatedImage {
                thumbnail("@drawable/_x_preview")
                screenReader("@string/x") {
                    parameter(ArithmeticExpression("test value"))
                }
                sequenceImages {
                    image("@drawable/_1")
                    image("@drawable/_2")
                    image("@drawable/_3")
                }
            }
            partText {
                launch(target = LAUNCH.ALARM)
                text { font(size = 20f) { template(text = "See alarms") } }
            }
            condition {
                expressions { expression("alwaysTrue") { 0 `==` 0.l } }
                expressions { expression("alwaysTrue", ArithmeticExpression { 0 `==` 0.l }) }
                compare("alwaysTrue") {
                    group { }
                }
            }
            group(id = "g2") {}
            analogClock {
                hourHand(resource = "hands/hour.png") {
                    ambientVariant(
                        target = attrs.resource,
                        value = "hands/hour-ambient.png"
                    )
                }
                minuteHand(resource = "hands/minute.png") {
                    ambientVariant(
                        target = attrs.resource,
                        value = "hands/minute-ambient.png"
                    )
                }
                secondHand(resource = "hands/second.png") {
                    ambientVariant(
                        target = attrs.resource,
                        value = "hands/second-ambient.png"
                    )
                    sweep(frequency = 15)
                    tick(duration = .2f, strength = 1f)
                }
            }
        }
    }
}
