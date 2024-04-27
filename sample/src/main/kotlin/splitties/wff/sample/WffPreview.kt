package splitties.wff.sample

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import splitties.wff.previews.WffPreview

@Preview
@Composable
fun SimpleDigitalPreview() {
    WffPreview { simpleDigital() }
}

fun main() {
    val width = (450f / 1.5f).dp
    singleWindowApplication(state = WindowState(size = DpSize(width, width))) {
        SimpleDigitalPreview()
    }
}