package splitties.wff

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByName
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

@Suppress("unused") // referenced in build.gradle.kts
class WatchfaceBuilderPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.applyPlugin()
    }

    private fun Project.applyPlugin() {
        plugins.apply("org.jetbrains.kotlin.jvm")
        plugins.apply(ApplicationPlugin::class)

        val sourceSets = extensions.getByName<SourceSetContainer>("sourceSets")
        sourceSets.named("main").configure {
            this.extensions.getByName<SourceDirectorySet>("kotlin").srcDirs("src")
        }

        extensions.getByName<KotlinJvmProjectExtension>("kotlin").apply {
            compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
        }

        dependencies {
            "implementation"("org.splitties.wff:core:$thisProjectVersion")
        }
    }
}
