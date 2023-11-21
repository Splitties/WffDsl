plugins {
    `kotlin-dsl`
}

fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"

dependencies {
    implementation(plugin(id = "com.gradle.plugin-publish", version = "1.2.1"))
    implementation(plugin(id = "org.jetbrains.kotlin.jvm", version = "1.9.20"))
}

kotlin {
    compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
}
