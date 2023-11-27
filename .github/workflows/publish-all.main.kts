#!/usr/bin/env kotlin

@file:DependsOn("io.github.typesafegithub:github-workflows-kt:1.6.0")

import io.github.typesafegithub.workflows.actions.actions.CheckoutV4
import io.github.typesafegithub.workflows.actions.actions.SetupJavaV3
import io.github.typesafegithub.workflows.actions.gradle.GradleBuildActionV2
import io.github.typesafegithub.workflows.actions.nexusactions.CreateNexusStagingRepoV1
import io.github.typesafegithub.workflows.actions.nexusactions.DropNexusStagingRepoV1
import io.github.typesafegithub.workflows.actions.nexusactions.ReleaseNexusStagingRepoV1
import io.github.typesafegithub.workflows.domain.*
import io.github.typesafegithub.workflows.domain.AbstractResult.Status.Success
import io.github.typesafegithub.workflows.domain.triggers.PullRequest
import io.github.typesafegithub.workflows.domain.triggers.WorkflowDispatch
import io.github.typesafegithub.workflows.dsl.JobBuilder
import io.github.typesafegithub.workflows.dsl.WorkflowBuilder
import io.github.typesafegithub.workflows.dsl.expressions.Contexts
import io.github.typesafegithub.workflows.dsl.expressions.expr
import io.github.typesafegithub.workflows.dsl.workflow
import io.github.typesafegithub.workflows.yaml.writeToFile

// GitHub Action runners preinstalled software: https://docs.github.com/en/actions/using-github-hosted-runners/about-github-hosted-runners/about-github-hosted-runners#preinstalled-software

val SONATYPE_USERNAME by Contexts.secrets
val SONATYPE_PASSWORD by Contexts.secrets
val SONATYPE_PROFILE_ID by Contexts.secrets

val GPG_KEY_ID by Contexts.secrets
val GPG_PRIVATE_KEY by Contexts.secrets
val GPG_PRIVATE_PASSWORD by Contexts.secrets

val gradle_publish_key by Contexts.secrets
val gradle_publish_secret by Contexts.secrets

workflow(
    sourceFile = __FILE__.toPath(),
    name = "Publish all",
    on = listOf(
        manualTrigger(),
        prOpened(branch = "release", "version.txt")
    )
) {
    val createStagingRepo = createStagingRepoJob()
    val buildAndUpload = job(
        id = "build-and-upload-to-maven-central",
        name = "Build and upload artifacts to Maven Central staging repository",
        runsOn = RunnerType.UbuntuLatest,
        needs = listOf(createStagingRepo)
    ) {
        checkout()
        setupJava()
        gradle(
            task = "publishMavenPublicationToMavenCentralStagingRepository",
            env = linkedMapOf(
                "sonatype_staging_repo_id" to expr { createStagingRepo.outputs.repositoryId },
                "sonatype_username" to expr { SONATYPE_USERNAME },
                "sonatype_password" to expr { SONATYPE_PASSWORD },
                "GPG_key_id" to expr { GPG_KEY_ID },
                "GPG_private_key" to expr { GPG_PRIVATE_KEY },
                "GPG_private_password" to expr { GPG_PRIVATE_PASSWORD }
            )
        )
    }
    val mavenCentralFinalization = finalizeMavenCentralPublication(
        createStagingRepo = createStagingRepo,
        buildAndUpload = buildAndUpload
    )
    job(
        id = "gradle-plugins-publishing",
        runsOn = RunnerType.UbuntuLatest,
        needs = listOf(buildAndUpload, mavenCentralFinalization),
        `if` = expr { buildAndUpload.result eq Success }
    ) {
        checkout()
        setupJava()
        gradle(
            task = "publishPlugins",
            properties = mapOf(
                "gradle.publish.key" to expr { gradle_publish_key },
                "gradle.publish.secret" to expr { gradle_publish_secret }
            )
        )
    }
}.writeToFile()

/* ------ Supporting code below. To be moved to a library at some point. ------ */

//region Supporting functions (manualTrigger, prOpened, checkout, setupJava, gradle)

fun manualTrigger() = WorkflowDispatch()

fun prOpened(
    branch: String,
    vararg triggerFiles: String
): PullRequest = PullRequest(
    types = listOf(PullRequest.Type.Opened),
    paths = triggerFiles.asList(),
    branches = listOf(branch)
)

// https://github.com/actions/checkout
fun JobBuilder<*>.checkout() = uses(action = CheckoutV4())

// https://github.com/actions/setup-java
fun JobBuilder<*>.setupJava(version: String = "17") = uses(
    action = SetupJavaV3(
        distribution = SetupJavaV3.Distribution.Temurin,
        javaVersion = version
    )
)

// https://github.com/gradle/gradle-build-action
fun JobBuilder<*>.gradle(
    gradleExecutable: String? = null,
    task: String,
    scan: Boolean = false,
    properties: Map<String, String> = emptyMap(),
    env: LinkedHashMap<String, String> = linkedMapOf()
) = uses(
    action = GradleBuildActionV2(
        gradleExecutable = gradleExecutable,
        arguments = buildList {
            add(task)
            if (scan) add("--scan")
            properties.forEach { (key, value) ->
                add("-P$key=$value")
            }
        }.joinToString(separator = " ")
    ),
    env = env
)

//endregion

//region Maven Central/OSSRH/Nexus/Sonatype (HOW MANY NAMES???) supporting functions.

fun WorkflowBuilder.createStagingRepoJob() = job(
    id = "create-staging-repository",
    name = "Create staging repository",
    runsOn = RunnerType.UbuntuLatest,
    outputs = CreateStagingRepoJobOutput()
) {
    jobOutputs.repositoryId = createStagingRepo().outputs.repositoryId
}

fun JobBuilder<*>.createStagingRepo(): ActionStep<CreateNexusStagingRepoV1.Outputs> = uses(
    action = CreateNexusStagingRepoV1(
        username = expr { SONATYPE_USERNAME },
        password = expr { SONATYPE_PASSWORD },
        stagingProfileId = expr { SONATYPE_PROFILE_ID },
        description = "${ expr { github.repository} }/${ expr { github.workflow} }#${ expr { github.run_number} }"
    )
)

class CreateStagingRepoJobOutput : JobOutputs() {
    var repositoryId by output()
}

fun WorkflowBuilder.finalizeMavenCentralPublication(
    createStagingRepo: Job<CreateStagingRepoJobOutput>,
    buildAndUpload: Job<*>,
) = job(
    id = "finalize-maven-central-publication",
    runsOn = RunnerType.UbuntuLatest,
    needs = listOf(createStagingRepo, buildAndUpload),
    `if` = expr { "${always()} && ${createStagingRepo.result eq Success}" }
) {
    uses(
        name = "Discard staging repository",
        `if` = expr { buildAndUpload.result neq Success },
        action = DropNexusStagingRepoV1(
            username = expr { SONATYPE_USERNAME },
            password = expr { SONATYPE_PASSWORD },
            stagingRepositoryId = expr { createStagingRepo.outputs.repositoryId },
        )
    )
    uses(
        name = "Release",
        `if` = expr { buildAndUpload.result eq Success },
        action = ReleaseNexusStagingRepoV1(
            username = expr { SONATYPE_USERNAME },
            password = expr { SONATYPE_PASSWORD },
            stagingRepositoryId = expr { createStagingRepo.outputs.repositoryId },
        )
    )
}

//endregion
