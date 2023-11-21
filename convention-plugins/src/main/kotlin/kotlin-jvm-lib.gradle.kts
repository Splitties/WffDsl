import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*
import publishing.trySignAll

plugins {
    java
    kotlin("jvm")
    `maven-publish`
    signing
}

java { withSourcesJar() }
kotlin { jvmToolchain(8) }
signing { trySignAll() }

dependencies {
    testImplementation(kotlin("test"))
}

publishing {
    publications.withType<MavenPublication> {
        artifact(tasks.emptyJavadocJar())
        setupPom()
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
        mavenCentralStagingPublishing(
            project = project,
            repositoryId = System.getenv("sonatype_staging_repo_id")
        )
        sonatypeSnapshotsPublishing(project = project)
    }
}
