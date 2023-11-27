pluginManagement {
    includeBuild("convention-plugins")
}

plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
    id("org.splitties.settings-include-dsl") version "0.2.6"
    id("org.splitties.version-sync") version "0.2.6"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        mavenCentral()
        google()
    }
}

gradle.beforeProject {
    group = "org.splitties.wff"
}

rootProject.name = "WatchFaceDsl"

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

include {
    "core"()
    "extensions"()
    "plugins" {
        "gradle" {
            "watchface-app"()
        }
    }
}

