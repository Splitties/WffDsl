package splitties.wff.previews

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.html.Tag
import splitties.wff.Alignment
import splitties.wff.GROUP
import splitties.wff.SCENE
import splitties.wff.WATCHFACE
import splitties.wff.WatchFaceDsl
import splitties.wff.clock.DIGITALCLOCK
import splitties.wff.clock.TIMETEXT
import splitties.wff.group.part.text.PARTTEXT
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WffPreview(watchface: WatchFaceDsl<TagTree>.() -> TagTree) {
    val tagTree = with(WatchFaceDsl(TagTreeConsumer())) {
        watchface()
    }

    val dp = with(LocalDensity.current) {
        (450.0f).toDp()
    }
    with(DrawResources(LocalFontFamilyResolver.current, LocalDensity.current)) {
        Canvas(modifier = Modifier.size(dp)) {
            tagTree.draw()
        }
    }
}

context(DrawScope, DrawResources)
private fun TagTree.draw() {
    watchface.drawWatchface()
}

context(DrawScope, TagTree, DrawResources)
private fun WATCHFACE.drawWatchface() {
    scene.draw()
}

context(DrawScope, TagTree, DrawResources)
private fun Tag.draw() {
    when (this) {
        is SCENE -> drawScene()
        is DIGITALCLOCK -> drawDigitalClock()
        is TIMETEXT -> drawTimeText()
        is GROUP -> drawGroup()
        is PARTTEXT -> drawPartText()
        else -> println("Can't draw unknown: $this")
    }
}

context(DrawScope, TagTree, DrawResources)
private fun SCENE.drawScene() {
    children.forEach {
        it.draw()
    }
}

context(DrawScope, TagTree, DrawResources)
private fun GROUP.drawGroup() {
    children.forEach {
        it.draw()
    }
}

context(DrawScope, TagTree, DrawResources)
private fun DIGITALCLOCK.drawDigitalClock() {
    children.forEach {
        it.draw()
    }
}

context(DrawScope, TagTree, DrawResources)
private fun TIMETEXT.drawTimeText() {
    val text = formatTime(this.format)

    val topLeft = Offset(attrs.x.value.toFloat(), attrs.y.value.toFloat())
    val size = Size(attrs.width.value.toFloat(), attrs.height.value.toFloat())
    val align = Alignment.valueOf(attributes["align"] ?: "START")

    drawText(textMeasurer = textMeasurer, text = text, topLeft = topLeft, size = size, align = align)
}

context(DrawScope, TagTree, DrawResources)
private fun PARTTEXT.drawPartText() {
    val text = content ?: "MISSING!"

    val topLeft = Offset(attrs.x.value.toFloat(), attrs.y.value.toFloat())
    val size = Size(attrs.width.value.toFloat(), attrs.height.value.toFloat())
    val align = Alignment.valueOf(attributes["align"] ?: "START")

    drawText(textMeasurer = textMeasurer, text = text, topLeft = topLeft, size = size, align = align)
}

context(DrawScope, TagTree, DrawResources)
private fun drawText(text: String, textMeasurer: TextMeasurer, topLeft: Offset, size: Size, align: Alignment) {
    val result = textMeasurer.measure(text, maxLines = 1, constraints = Constraints(maxWidth = size.width.toInt(), maxHeight = size.width.toInt()))

    drawText(result, topLeft = topLeft)
}

class DrawResources(resolver: FontFamily.Resolver, density: Density) {
    val time = LocalDateTime.now()
    val textMeasurer = TextMeasurer(resolver, density, LayoutDirection.Ltr)

    init {
        println("Density: ${density.density}")
    }

    fun formatTime(format: String): String {
        val formatter = DateTimeFormatter.ofPattern(format)
        return formatter.format(time)
    }
}
