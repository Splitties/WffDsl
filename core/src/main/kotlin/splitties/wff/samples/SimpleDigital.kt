package splitties.wff.samples

import splitties.wff.*
import splitties.wff.clock.TIMETEXT.HourFormat.SYNC_TO_DEVICE
import splitties.wff.clock.digitalClock
import splitties.wff.clock.timeText
import splitties.wff.common.variant.ambientVariant
import splitties.wff.group.part.text.FONT.Weight.THIN
import splitties.wff.group.part.text.font
import splitties.wff.group.part.text.formatter.template
import splitties.wff.group.part.text.partText
import splitties.wff.group.part.text.text

/**
 * Taken from https://github.com/android/wear-os-samples/blob/main/WatchFaceFormat/SimpleDigital/res/raw/watchface.xml
 */
context(WatchFaceDsl<T>)
internal fun <T> simpleDigital(): T = watchFace(width = 450, height = 450) {
    clockType(ClockType.DIGITAL)
    metadata("TICK_PER_SECOND", "15")
    previewTime("10:08:32")
    scene {
        digitalClock {
            comment(" SYNC_TO_DEVICE specifies to respect the device 12/24h setting ")
            comment(" Interactive mode version")
            timeText(
                format = "hh:mm",
                hourFormat = SYNC_TO_DEVICE,
                align = splitties.wff.Alignment.CENTER,
                y = 175,
                height = 100
            ) {
                ambientVariant(attrs.alpha, 0)
                font(
                    size = 128f,
                    color = Color.white
                )
            }
            comment(" Ambient mode version - thinner weight ")
            timeText(
                format = "hh:mm",
                align = splitties.wff.Alignment.CENTER,
                y = 175,
                height = 100,
                alpha = 0u
            ) {
                ambientVariant(attrs.alpha, 0xFF)
                font(
                    size = 128f,
                    weight = THIN,
                    color = Color.white
                )
            }
        }
        group(name = "hello_world") {
            partText(y = 285, height = 50) {
                ambientVariant(attrs.alpha, 0)
                text {
                    font(
                        size = 36f,
                        color = Color.white
                    ) {
                        template(stringResourceName = "greeting")
                    }
                }
            }
        }
    }
}
