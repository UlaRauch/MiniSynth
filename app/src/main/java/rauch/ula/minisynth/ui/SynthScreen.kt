package rauch.ula.minisynth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import rauch.ula.minisynth.ui.panels.ControlsPanel
import rauch.ula.minisynth.ui.panels.WaveShapeSelection

@Composable
fun SynthScreen() = Surface(modifier = Modifier.fillMaxSize()) {
    Column {
        WaveShapeSelection()
        ControlsPanel()
    }
}