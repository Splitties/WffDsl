package splitties.wff

import splitties.wff.samples.simpleDigital
import kotlin.test.Test

class SampleWatchFaceTest {

    @Test
    fun main() {
        with(WatchFaceDsl()) {
            sampleWatchFace()
        }
        with(WatchFaceDsl()) {
            simpleDigital()
        }
    }
}
