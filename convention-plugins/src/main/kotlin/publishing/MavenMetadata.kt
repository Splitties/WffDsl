@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.UnknownTaskException
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register

internal object Publishing {
    const val gitUrl = "https://github.com/Splitties/WatchFaceDsl.git"
    const val siteUrl = "https://github.com/Splitties/WatchFaceDsl"
    const val libraryDesc = "Use Kotlin to create a code-free WatchFace app with the new XML Watch Face Format introduced in Wear OS 4."
}

internal fun TaskContainer.emptyJavadocJar(): TaskProvider<Jar> {
    val taskName = "javadocJar"
    return try {
        named(name = taskName)
    } catch (e: UnknownTaskException) {
        register(name = taskName) { archiveClassifier by "javadoc" }
    }
}

internal fun MavenPublication.setupPom(
    gitUrl: String = Publishing.gitUrl,
    siteUrl: String = Publishing.siteUrl,
    libraryDesc: String = Publishing.libraryDesc
) = pom {
    if (name.isPresent.not()) {
        name by artifactId
    }
    description by libraryDesc
    url by siteUrl
    licenses {
        license {
            name by "The Apache Software License, Version 2.0"
            url by "https://www.apache.org/licenses/LICENSE-2.0.txt"
        }
    }
    developers {
        developer {
            id by "louiscad"
            name by "Louis CAD"
            email by "louis.cognault@gmail.com"
        }
    }
    scm {
        connection by gitUrl
        developerConnection by gitUrl
        url by siteUrl
    }
    if (gitUrl.startsWith("https://github.com")) issueManagement {
        system by "GitHub"
        url by gitUrl.replace(".git", "/issues")
    }
}
