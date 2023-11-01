plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm") version "1.9.10"
    `java-library`
    `maven-publish`
}

java {
    withSourcesJar()
}

publishing {
    publications.create<MavenPublication>(name = "maven") {
        group = "com.louiscad.wff"
        artifactId = "core"
        version = "0.1.0"
        from(components["java"])
    }
}

repositories {
    mavenCentral()
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
