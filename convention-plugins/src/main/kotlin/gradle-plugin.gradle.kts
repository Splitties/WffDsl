import gradle.kotlin.dsl.accessors._3446ec0516f3690bb745eb6b3e74e05f.signing
import publishing.trySignAll

plugins {
    id("com.gradle.plugin-publish")
    signing
}

dependencies {
    compileOnly(gradleKotlinDsl())
}

gradlePlugin {
    vcsUrl = Publishing.gitUrl
    website = Publishing.siteUrl
}

signing { trySignAll() }
