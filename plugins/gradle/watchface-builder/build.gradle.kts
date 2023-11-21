import org.splitties.gradle.VersionFileWriter
import org.splitties.gradle.putVersionInCode

plugins {
    id("gradle-plugin")
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create(project.name) {
            id = "org.splitties.wff.watchface-builder"
            displayName = id // Not displayed on Gradle Plugins Portal anyway.
            description = "Provides a Kotlin DSL to output XML based Watch Face Format introduced in Wear OS 4. See the documentation before using."
            tags = listOf("wear-os", "kotlin", "kotlin-dsl")
            implementationClass = "splitties.wff.WatchfaceBuilderPlugin"
        }
    }
}

dependencies {
    compileOnly(gradleKotlinDsl())
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.9.20")
}

kotlin {
    jvmToolchain(17)
}

sourceSets { main { kotlin.srcDir("build/gen") } }

putVersionInCode(
    outputDirectory = layout.dir(provider { file("build/gen") }),
    writer = VersionFileWriter.Kotlin(
        fileName = "Version.kt",
        `package` = "splitties.wff",
        propertyName = "thisProjectVersion",
        const = true
    )
)
