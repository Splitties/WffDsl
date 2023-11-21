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
            tags = listOf("wear-os", "kotlin", "kotlin-dsl")
            implementationClass = "splitties.wff.WatchfaceAppPlugin"
        }
    }
}

dependencies {
    compileOnly(gradleKotlinDsl())
    implementation("com.android.application:com.android.application.gradle.plugin:8.1.2")
}

kotlin {
    jvmToolchain(17)
}
