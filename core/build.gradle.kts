plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm") version "1.9.20"
    `java-library`
    `maven-publish`
}

java {
    withSourcesJar()
}

publishing {
    publications.create<MavenPublication>(name = "maven") {
        artifactId = "core"
        from(components["java"])
    }
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-html:0.9.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(18)
    }
}

kotlin {
    compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
