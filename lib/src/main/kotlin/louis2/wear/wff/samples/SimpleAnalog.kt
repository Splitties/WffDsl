package louis2.wear.wff.samples

import louis2.wear.wff.*
import louis2.wear.wff.clock.*
import louis2.wear.wff.common.variant.ambientVariant

/**
 * Taken from https://github.com/android/wear-os-samples/blob/main/WatchFaceFormat/SimpleAnalog/res/raw/watchface.xml
 */
context(WatchFaceDsl<T>)
internal fun <T> simpleAnalog(): T = watchFace(width = 450, height = 450) {
    clockType(ClockType.ANALOG)
    metadata("TICK_PER_SECOND", "15")
    previewTime("10:08:32")
    scene(backgroundColor = 0xFF_000000u) {
        analogClock {
            comment(" Interactive-mode hands ")
            secondHand(resource = "@drawable/second_hand", x = 224, y = 10, width = 2, height = 215, pivotY = 1f) {
                ambientVariant("alpha", 0)
                comment(" Second hand can either be 'Sweep' or 'Tick' in behaviour ")
                sweep(frequency = 15)
            }
            minuteHand(resource = "@drawable/minute_hand", x = 220, y = 75, width = 10, height = 150, pivotY = 1f) {
                ambientVariant("alpha", 0)
            }
            hourHand(resource = "@drawable/hour_hand", x = 220, y = 125, width = 10, height = 100, pivotY = 1f) {
                ambientVariant("alpha", 0)
            }
            comment(" Ambient versions - no second hand in ambient mode ")
            minuteHand(resource = "@drawable/minute_hand_ambient", x = 222, y = 75, width = 6, height = 150, pivotY = 1f, alpha = 0u) {
                ambientVariant("alpha", 0xFFu)
            }
            hourHand(resource = "@drawable/hour_hand_ambient", x = 222, y = 125, width = 6, height = 100, pivotY = 1f, alpha = 0u) {
                ambientVariant("alpha", 0xFFu)
            }
        }
    }
}

/*
<WatchFace width="450" height="450">
  <Metadata key="CLOCK_TYPE" value="ANALOG"/>
  <Metadata key="TICK_PER_SECOND" value="15"/>
  <Metadata key="PREVIEW_TIME" value="10:08:32"/>
  <Scene backgroundColor="#ff000000">
    <AnalogClock x="0" y="0" width="450" height="450">
      <!-- Interactive-mode hands -->
      <SecondHand resource="@drawable/second_hand" x="224" y="10" width="2" height="215" pivotX="0.5" pivotY="1">
        <Variant mode="AMBIENT" target="alpha" value="0"/>
        <!-- Second hand can either be 'Sweep' or 'Tick' in behaviour -->
        <Sweep frequency="15"/>
      </SecondHand>
      <MinuteHand resource="@drawable/minute_hand" x="220" y="75" width="10" height="150" pivotX="0.5" pivotY="1">
        <Variant mode="AMBIENT" target="alpha" value="0"/>
      </MinuteHand>
      <HourHand resource="@drawable/hour_hand" x="220" y="125" width="10" height="100" pivotX="0.5" pivotY="1">
        <Variant mode="AMBIENT" target="alpha" value="0"/>
      </HourHand>
      <!-- Ambient versions - no second hand in ambient mode -->
      <MinuteHand resource="@drawable/minute_hand_ambient" x="222" y="75" width="6" height="150" pivotX="0.5" pivotY="1" alpha="0">
        <Variant mode="AMBIENT" target="alpha" value="255"/>
      </MinuteHand>
      <HourHand resource="@drawable/hour_hand_ambient" x="222" y="125" width="6" height="100" pivotX="0.5" pivotY="1" alpha="0">
        <Variant mode="AMBIENT" target="alpha" value="255"/>
      </HourHand>
    </AnalogClock>
  </Scene>
</WatchFace>
 */
