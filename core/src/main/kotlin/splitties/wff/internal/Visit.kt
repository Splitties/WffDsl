package splitties.wff.internal

import kotlinx.html.Tag
import kotlinx.html.visitTag

internal fun <T : Tag> T.visit(block: (T.() -> Unit)?) = visitTag { block?.invoke(this) }
