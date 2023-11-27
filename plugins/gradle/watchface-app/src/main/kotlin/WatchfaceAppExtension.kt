package splitties.wff

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

interface WatchfaceAppExtension {

    /**
     * Enabling this disables the generation of the xml from the builder code.
     *
     * Use it if you need to test manual edits of the xml located in `build/splitties/wff-dsl/builder-output/raw/watchface.xml`.
     *
     * DON'T FORGET TO TURN IT BACK OFF (default) after you're done with your experiments.
     */
    val skipBuilder: Property<Boolean>

    /**
     * Name of the file that contains the `main` function that calls `writeWatchFaceForApp`.
     *
     * You can omit the `.kt` extension.
     */
    val targetFile: Property<String>

    /**
     * Name of the child project that applies the `watchface-builder` and contains the Kotlin that
     * generates the WatchFace XML.
     *
     * Defaults to "`builder`".
     */
    val builderProjectName: Property<String>

    val doNotUseExtensions: Property<Boolean>

    val libraries: ListProperty<String>
}
