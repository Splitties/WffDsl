import org.splitties.gradle.VersionFileWriter
import org.splitties.gradle.putVersionInCode

plugins {
    id("gradle-plugin")
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create(project.name) {
            id = "org.splitties.wff.watchface-app"
            displayName = id // Not displayed on Gradle Plugins Portal anyway.
            description = "Packages a code-free Wear OS 4+ Watch Face app made with the Kotlin WatchFaceDsl."
            tags = listOf("wear-os", "kotlin", "kotlin-dsl", "android")
            implementationClass = "splitties.wff.WatchfaceAppPlugin"
        }
    }
}

dependencies {
    compileOnly(gradleKotlinDsl())
    fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"
    implementation(plugin("com.android.application", "8.1.2"))
    implementation(plugin("org.jetbrains.kotlin.jvm", "1.9.20"))
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
