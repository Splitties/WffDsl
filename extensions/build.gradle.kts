plugins {
    id("kotlin-jvm-lib")
}

dependencies {
    api(project(":core"))

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

kotlin {
    compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
