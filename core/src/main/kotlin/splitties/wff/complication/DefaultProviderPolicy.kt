package splitties.wff.complication

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import splitties.wff.*

/**
 * A watch face may wish to try and set one or more non-system data sources as the default data source for
 * a complication. If a complication data source can't be used for some reason -- such as
 * when it isn't installed, or it doesn't support the requested type -- then the next one is tried.
 * A system complication data source acts as a final fallback in case no non-system data sources can be used.
 *
 * If the DefaultComplicationDataSourcePolicy is empty, then no default is set.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/complication/default-provider-policy)
 */
@WffTagMarker
fun COMPLICATIONSLOT.defaultProviderPolicy(
    defaultSystemProvider: SystemProvider,
    defaultSystemProviderType: ComplicationType,
    primaryProvider: String? = null,
    primaryProviderType: ComplicationType? = null,
    secondaryProvider: String? = null,
    secondaryProviderType: ComplicationType? = null
) {
    require((primaryProvider == null) == (primaryProviderType == null))
    require((secondaryProvider == null) == (secondaryProviderType == null))
    if (secondaryProvider != null) require(primaryProvider != null)
    DEFAULTPROVIDERPOLICY(
        initialAttributes = attributesMapOf(
            "defaultSystemProvider", defaultSystemProvider.xmlValue(),
            "defaultSystemProviderType", defaultSystemProviderType.xmlValue(),
            "primaryProvider", primaryProvider,
            "primaryProviderType", primaryProviderType?.xmlValue(),
            "secondaryProvider", secondaryProvider,
            "secondaryProviderType", secondaryProviderType?.xmlValue()
        ),
        consumer = consumer,
    ).visit {}
}

class DEFAULTPROVIDERPOLICY(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>,
) : XMLTag(
    tagName = "DefaultProviderPolicy",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = true
)
