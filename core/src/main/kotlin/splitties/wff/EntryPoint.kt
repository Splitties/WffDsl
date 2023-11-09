package splitties.wff

import java.io.File

/**
 * Designed to be the body of the main function as such:
 * ```kotlin
 * fun main(args: Array<String>) = writeWatchFaceForApp(args) {
 *     yourBeautifulWatchFace()
 * }
 * ```
 */
fun writeWatchFaceForApp(
    args: Array<String>,
    block: WatchFaceDsl<String>.() -> String
) {
    val rawDir = File(args.first())
    if (rawDir.exists().not()) rawDir.mkdirs()
    val targetFile = rawDir.resolve("watchface.xml")
    with(WatchFaceDsl()) {
        targetFile.writeText(block())
    }
}
