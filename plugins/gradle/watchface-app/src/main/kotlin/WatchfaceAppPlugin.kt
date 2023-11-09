package splitties.wff

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.OutputDirectory
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.the

@Suppress("unused") // referenced in build.gradle.kts
class WatchfaceAppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.applyPlugin()
    }

    private fun Project.applyPlugin() {
        plugins.apply("com.android.application")
        val extension = extensions.create<WatchfaceAppExtension>("watchFaceApp")
        extension.builderProjectName.convention("builder")
        android {
            compileSdk = 34
            defaultConfig {
                minSdk = 33
                targetSdk = 33
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = true
                    isShrinkResources = false
                }
            }
        }
        androidComponents {
            onVariants { variant ->
                val task = tasks.register<ResCreatorTask>(
                    name = "generateWatchFaceXml${variant.name}"
                ) {
                    val builderProjectName = extension.builderProjectName.get()
                    val builderProject = project.childProjects[builderProjectName]
                        ?: error("The builderProject must be a child of this project. $builderProjectName wasn't found.")
                    dependsOn(builderProject.tasks.named<JavaExec>("run") {
                        mainClass.set(extension.targetFile.map { it.removeSuffix(".kt") + "Kt" })
                        val rawDir = outputDirectory.dir("raw").get()
                        setArgs(listOf(rawDir))
                    })
                }
                variant.sources.res?.addGeneratedSourceDirectory(
                    task, ResCreatorTask::outputDirectory
                )
            }
        }
    }

    private inline fun Project.android(
        configure: ApplicationExtension.() -> Unit
    ) {
        the<ApplicationExtension>().apply(configure)
    }

    private inline fun Project.androidComponents(
        configure: ApplicationAndroidComponentsExtension.() -> Unit
    ) {
        the<ApplicationAndroidComponentsExtension>().apply(configure)
    }

    internal abstract class ResCreatorTask : DefaultTask() {
        @get:OutputDirectory
        abstract val outputDirectory: DirectoryProperty
    }
}

