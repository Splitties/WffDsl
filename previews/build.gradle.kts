plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.6.2"
}

dependencies {
    api(project(":core"))

    implementation(compose.desktop.currentOs)

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

kotlin {
    compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
