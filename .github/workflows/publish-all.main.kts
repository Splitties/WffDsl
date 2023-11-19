#!/usr/bin/env kotlin

@file:DependsOn("io.github.typesafegithub:github-workflows-kt:1.5.0")

import Publish_all_main.AbstractResult.Status.Success
import io.github.typesafegithub.workflows.actions.actions.CheckoutV4
import io.github.typesafegithub.workflows.actions.actions.SetupJavaV3
import io.github.typesafegithub.workflows.actions.gradle.GradleBuildActionV2
import io.github.typesafegithub.workflows.domain.*
import io.github.typesafegithub.workflows.domain.actions.Action
import io.github.typesafegithub.workflows.domain.actions.RegularAction
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

//region nexus-actions Bindings TODO: remove when github-workflows-kt is updated beyond 1.5.0

/**
 * Action: Create Nexus Staging Repo
 *
 * Creates a new staging repo on https://oss.sonatype.org/
 *
 * [Action on GitHub](https://github.com/nexus-actions/create-nexus-staging-repo)
 */
class CreateNexusStagingRepoV1 private constructor(
    /**
     * Your Sonatype username, same the Sonatype Jira one
     */
    val username: String,
    /**
     * Your Sonatype password, same the Sonatype Jira one
     */
    val password: String,
    /**
     * Your staging profile ID. You can get it at
     * https://oss.sonatype.org/#stagingProfiles;$staginProfileId
     */
    val stagingProfileId: String,
    /**
     * A description to identify the newly created repository
     */
    val description: String? = null,
    /**
     * The url of your nexus repository, defaults to OSSRH (https://oss.sonatype.org/service/local/)
     */
    val baseUrl: String? = null,
    /**
     * Type-unsafe map where you can put any inputs that are not yet supported by the binding
     */
    val _customInputs: Map<String, String> = mapOf(),
    /**
     * Allows overriding action's version, for example to use a specific minor version, or a newer
     * version that the binding doesn't yet know about
     */
    val _customVersion: String? = null,
) : RegularAction<CreateNexusStagingRepoV1.Outputs>("nexus-actions", "create-nexus-staging-repo",
    _customVersion ?: "v1") {
    constructor(
        vararg pleaseUseNamedArguments: Unit,
        username: String,
        password: String,
        stagingProfileId: String,
        description: String? = null,
        baseUrl: String? = null,
        _customInputs: Map<String, String> = mapOf(),
        _customVersion: String? = null,
    ) : this(username=username, password=password, stagingProfileId=stagingProfileId,
        description=description, baseUrl=baseUrl, _customInputs=_customInputs,
        _customVersion=_customVersion)

    @Suppress("SpreadOperator")
    override fun toYamlArguments(): java.util.LinkedHashMap<String, String> = linkedMapOf(
        *listOfNotNull(
            "username" to username,
            "password" to password,
            "staging_profile_id" to stagingProfileId,
            description?.let { "description" to it },
            baseUrl?.let { "base_url" to it },
            *_customInputs.toList().toTypedArray(),
        ).toTypedArray()
    )

    override fun buildOutputObject(stepId: String): Outputs = Outputs(stepId)

    class Outputs(
        stepId: String,
    ) : Action.Outputs(stepId) {
        /**
         * The ID of the newly created staging repository
         */
        val repositoryId: String = "steps.$stepId.outputs.repository_id"
    }
}


/**
 * Action: Drop Nexus Staging Repo
 *
 * Drops a staged repo, with a given ID, on https://oss.sonatype.org/.
 *
 * [Action on GitHub](https://github.com/nexus-actions/drop-nexus-staging-repo)
 */
class DropNexusStagingRepoV1 private constructor(
    /**
     * Your Sonatype username, same the Sonatype Jira one.
     */
    val username: String,
    /**
     * Your Sonatype password, same the Sonatype Jira one.
     */
    val password: String,
    /**
     * The ID of the staged repository to drop.
     */
    val stagingRepositoryId: String,
    /**
     * The url of your nexus repository, defaults to OSSRH (https://oss.sonatype.org/service/local/)
     */
    val baseUrl: String? = null,
    /**
     * Type-unsafe map where you can put any inputs that are not yet supported by the binding
     */
    val _customInputs: Map<String, String> = mapOf(),
    /**
     * Allows overriding action's version, for example to use a specific minor version, or a newer
     * version that the binding doesn't yet know about
     */
    val _customVersion: String? = null,
) : RegularAction<Action.Outputs>("nexus-actions", "drop-nexus-staging-repo", _customVersion ?:
"v1") {
    constructor(
        vararg pleaseUseNamedArguments: Unit,
        username: String,
        password: String,
        stagingRepositoryId: String,
        baseUrl: String? = null,
        _customInputs: Map<String, String> = mapOf(),
        _customVersion: String? = null,
    ) : this(username=username, password=password, stagingRepositoryId=stagingRepositoryId,
        baseUrl=baseUrl, _customInputs=_customInputs, _customVersion=_customVersion)

    @Suppress("SpreadOperator")
    override fun toYamlArguments(): java.util.LinkedHashMap<String, String> = linkedMapOf(
        *listOfNotNull(
            "username" to username,
            "password" to password,
            "staging_repository_id" to stagingRepositoryId,
            baseUrl?.let { "base_url" to it },
            *_customInputs.toList().toTypedArray(),
        ).toTypedArray()
    )

    override fun buildOutputObject(stepId: String): Action.Outputs = Outputs(stepId)
}

/**
 * Action: Close and Release Nexus Staged Repo
 *
 * Closes and releases a staged repo, with a given ID, on https://oss.sonatype.org/.
 *
 * [Action on GitHub](https://github.com/nexus-actions/release-nexus-staging-repo)
 */
class ReleaseNexusStagingRepoV1 private constructor(
    /**
     * Your Sonatype username, same the Sonatype Jira one.
     */
    val username: String,
    /**
     * Your Sonatype password, same the Sonatype Jira one.
     */
    val password: String,
    /**
     * The ID of the staged repository to close and release.
     */
    val stagingRepositoryId: String,
    /**
     * The description to use for the closed repository
     */
    val description: String? = null,
    /**
     * The url of your nexus repository, defaults to OSSRH (https://oss.sonatype.org/service/local/)
     */
    val baseUrl: String? = null,
    /**
     * This option disable the auto-release process, you will have to do it manually on
     * https://oss.sonatype.org/.
     */
    val closeOnly: Boolean? = null,
    /**
     * Type-unsafe map where you can put any inputs that are not yet supported by the binding
     */
    val _customInputs: Map<String, String> = mapOf(),
    /**
     * Allows overriding action's version, for example to use a specific minor version, or a newer
     * version that the binding doesn't yet know about
     */
    val _customVersion: String? = null,
) : RegularAction<Action.Outputs>("nexus-actions", "release-nexus-staging-repo", _customVersion ?:
"v1") {
    constructor(
        vararg pleaseUseNamedArguments: Unit,
        username: String,
        password: String,
        stagingRepositoryId: String,
        description: String? = null,
        baseUrl: String? = null,
        closeOnly: Boolean? = null,
        _customInputs: Map<String, String> = mapOf(),
        _customVersion: String? = null,
    ) : this(username=username, password=password, stagingRepositoryId=stagingRepositoryId,
        description=description, baseUrl=baseUrl, closeOnly=closeOnly,
        _customInputs=_customInputs, _customVersion=_customVersion)

    @Suppress("SpreadOperator")
    override fun toYamlArguments(): java.util.LinkedHashMap<String, String> = linkedMapOf(
        *listOfNotNull(
            "username" to username,
            "password" to password,
            "staging_repository_id" to stagingRepositoryId,
            description?.let { "description" to it },
            baseUrl?.let { "base_url" to it },
            closeOnly?.let { "close_only" to it.toString() },
            *_customInputs.toList().toTypedArray(),
        ).toTypedArray()
    )

    override fun buildOutputObject(stepId: String): Action.Outputs = Outputs(stepId)
}

//endregion

//region job.result, step.outcome, and step.conclusion TODO: Remove when it's integrated into github-workflows-kt
// see this PR: https://github.com/typesafegithub/github-workflows-kt/pull/1109

val Job<*>.result get() = object : AbstractResult("needs.$id.result") {}
val Step.outcome get() = object : AbstractResult("steps.$id.outcome") {}
val Step.conclusion get() = object : AbstractResult("steps.$id.conclusion") {}

abstract class AbstractResult internal constructor(
    private val value: String
) {

    infix fun eq(status: Status): String = "$value == $status"
    infix fun neq(status: Status): String = "$value != $status"

    override fun toString(): String = value

    enum class Status {
        Success,
        Failure,
        Cancelled,
        Skipped;

        override fun toString(): String = "'${name.lowercase()}'"
    }
}

//endregion
