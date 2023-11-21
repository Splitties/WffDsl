import org.gradle.kotlin.dsl.*

plugins {
    kotlin("jvm") apply false // Puts the Kotlin Gradle Plugin on the classpath
}

require(rootProject == project) {
    "This plugin is designed to be used in the root build.gradle[.kts] file only."
}
