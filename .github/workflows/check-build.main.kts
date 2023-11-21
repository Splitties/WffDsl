#!/usr/bin/env kotlin

@file:DependsOn("io.github.typesafegithub:github-workflows-kt:1.5.0")

import io.github.typesafegithub.workflows.actions.actions.CheckoutV4
import io.github.typesafegithub.workflows.actions.actions.SetupJavaV3
import io.github.typesafegithub.workflows.actions.gradle.GradleBuildActionV2
import io.github.typesafegithub.workflows.domain.RunnerType
import io.github.typesafegithub.workflows.domain.triggers.PullRequest
import io.github.typesafegithub.workflows.domain.triggers.WorkflowDispatch
import io.github.typesafegithub.workflows.dsl.JobBuilder
import io.github.typesafegithub.workflows.dsl.expressions.Contexts
import io.github.typesafegithub.workflows.dsl.expressions.expr
import io.github.typesafegithub.workflows.dsl.workflow
import io.github.typesafegithub.workflows.yaml.writeToFile

// GitHub Action runners preinstalled software: https://docs.github.com/en/actions/using-github-hosted-runners/about-github-hosted-runners/about-github-hosted-runners#preinstalled-software

val gradle_publish_key by Contexts.secrets
val gradle_publish_secret by Contexts.secrets

val GPG_KEY_ID by Contexts.secrets
val GPG_PRIVATE_KEY by Contexts.secrets
val GPG_PRIVATE_PASSWORD by Contexts.secrets

workflow(
    sourceFile = __FILE__.toPath(),
    name = "Check build",
    on = listOf(manualTrigger())
) {
    job(
        id = "check-build",
        name = "Check build",
        runsOn = RunnerType.UbuntuLatest,
        env = linkedMapOf(
            "GPG_key_id" to expr { GPG_KEY_ID },
            "GPG_private_key" to expr { GPG_PRIVATE_KEY },
            "GPG_private_password" to expr { GPG_PRIVATE_PASSWORD }
        )
    ) {
        checkout()
        setupJava()
        run(command = """echo "0.0.0" >> version.txt""")
        gradle(
            task = "publishPlugins --validate-only",
            properties = mapOf(
                "gradle.publish.key" to expr { gradle_publish_key },
                "gradle.publish.secret" to expr { gradle_publish_secret }
            )
        )
        gradle(task = "build")
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
