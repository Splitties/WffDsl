pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
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
    version = "0.1.0"
}

rootProject.name = "WatchFaceDsl"

include {
    "core"()
    "plugins" {
        "gradle" {
            "watchface-builder"()
            "watchface-app"()
        }
    }
}

//region include DSL
class ModuleParentScope(
    private val name: String,
    private val parent: ModuleParentScope? = null
) {

    operator fun String.invoke(block: (ModuleParentScope.() -> Unit)? = null) {
        check(startsWith(':').not())
        val moduleName = ":$this"
        val projectName = "$parentalPath$moduleName"
        include(projectName)
        block?.let { buildNode ->
            ModuleParentScope(
                name = moduleName,
                parent = this@ModuleParentScope
            ).buildNode()
        }
    }

    private val parentalPath: String =
        generateSequence(this) { it.parent }
            .map { it.name }.toList().reversed().joinToString("")

}

inline fun include(block: ModuleParentScope.() -> Unit) {
    ModuleParentScope("").block()
}
//endregion
