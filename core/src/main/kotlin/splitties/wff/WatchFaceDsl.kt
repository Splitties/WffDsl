package splitties.wff

import kotlinx.html.TagConsumer
import kotlinx.html.stream.createHTML

class WatchFaceDsl<T>(val consumer: TagConsumer<T>) {
    companion object {
        internal operator fun invoke() = WatchFaceDsl(createHTML(xhtmlCompatible = true))
    }
}
