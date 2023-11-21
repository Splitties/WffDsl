package publishing

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.getValue
import org.gradle.plugins.signing.SigningExtension
import propertyOrEnv
import propertyOrEnvOrNull

context (Project)
internal fun SigningExtension.trySignAll() {
    useInMemoryPgpKeys(
        propertyOrEnvOrNull("GPG_key_id"), // If using a sub-key.
        propertyOrEnvOrNull("GPG_private_key") ?: return,
        propertyOrEnv("GPG_private_password")
    )
    val publishing: PublishingExtension by extensions
    sign(publishing.publications)
}
