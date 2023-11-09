package splitties.wff

import org.gradle.api.provider.Property

interface WatchfaceAppExtension {
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
}
