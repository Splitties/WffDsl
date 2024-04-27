plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.6.2"
}

kotlin {
    compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
}

dependencies {
    api(project(":core"))
    api(project(":previews"))
    implementation(compose.desktop.currentOs)
}
