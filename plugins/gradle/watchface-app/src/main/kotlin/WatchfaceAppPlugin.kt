package splitties.wff

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

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
                getByName("debug") {
                    applicationIdSuffix = ".debug"
                }
                getByName("release") {
                    isMinifyEnabled = true
                    isShrinkResources = false
                }
            }
        }
        androidComponents {
            onVariants { variant ->
                val task = tasks.register<ResCreatorTask>(
                    name = "generateWatchFaceXml${variant.name.replaceFirstChar { it.uppercaseChar() }}"
                ) {
                    val skipBuilder = extension.skipBuilder.convention(false).get()
                    val builderOutputDir = layout.buildDirectory.dir("splitties/wff-dsl/builder-output")
                    val rawDir = builderOutputDir.get().dir("raw")
                    val builderProject = extension.builderProject(project)
                    if (skipBuilder.not()) {
                        dependsOn(builderProject.tasks.named<JavaExec>("run") {
                            mainClass.set(extension.targetFile.map { it.removeSuffix(".kt") + "Kt" })
                            setArgs(listOf(rawDir))
                        })
                    }
                    inputDirectory.set(builderOutputDir)
                }
                variant.sources.res?.addGeneratedSourceDirectory(
                    task, ResCreatorTask::outputDirectory
                )
            }
        }
        afterEvaluate { // Run after evaluation, otherwise, we read into extension too soon.
            setupBuilderProject(extension)
        }
    }

    private fun Project.setupBuilderProject(extension: WatchfaceAppExtension) {
        val doNotUseExtensions by extension.doNotUseExtensions.convention(false)
        val useExtensions = doNotUseExtensions.not()

        val paths = extension.libraries.convention(objects.listProperty())
        val libraries = paths.get().map { path -> project(path) }
        val builderProject = extension.builderProject(project)
        builderProject.layout.buildDirectory.set(projectDir.resolve("build/splitties/wff-dsl/builder/"))
        builderProject.run {
            plugins.apply("org.jetbrains.kotlin.jvm")
            plugins.apply(ApplicationPlugin::class)

            sourceSets {
                main { kotlin.srcDir(projectDir.parentFile.resolve(project.name)) }
            }

            kotlin {
                compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
            }

            dependencies {
                "implementation"("org.splitties.wff:core:$thisProjectVersion")
                if (useExtensions) {
                    "implementation"("org.splitties.wff:extensions:$thisProjectVersion")
                }
                libraries.forEach { "implementation"(it) }
            }
        }
    }

    private fun WatchfaceAppExtension.builderProject(project: Project): Project {
        val builderProjectName = builderProjectName.get()
        return project.childProjects[builderProjectName]
            ?: error("The builderProject must be a child of this project. $builderProjectName wasn't found.")
    }

    //region Code normally generated by Gradle when used in .gradle.kts scripts.

    private inline fun Project.sourceSets(configure: SourceSetContainer.() -> Unit) {
        extensions.getByName<SourceSetContainer>("sourceSets").configure()
    }

    private fun SourceSetContainer.main(configure: SourceSet.() -> Unit) {
        named("main").configure(configure)
    }

    private val SourceSet.kotlin: SourceDirectorySet get() = extensions.getByName<SourceDirectorySet>("kotlin")

    private inline fun Project.kotlin(
        configure: KotlinJvmProjectExtension.() -> Unit
    ) {
        extensions.getByName<KotlinJvmProjectExtension>("kotlin").apply(configure)
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
    //endregion

    internal abstract class ResCreatorTask : DefaultTask() {

        @get:InputDirectory
        abstract val inputDirectory: DirectoryProperty

        @get:OutputDirectory
        abstract val outputDirectory: DirectoryProperty

        @TaskAction
        fun copyFile() {
            inputDirectory.get().asFile.copyRecursively(
                target = outputDirectory.get().asFile,
                overwrite = true
            )
        }
    }
}

