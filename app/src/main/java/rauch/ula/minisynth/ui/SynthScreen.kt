package rauch.ula.minisynth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import rauch.ula.minisynth.synth.viewmodel.SynthViewModel
import rauch.ula.minisynth.ui.components.WaveShapeSelection
import rauch.ula.minisynth.ui.panels.ControlsPanel

@Composable
fun SynthScreen(viewModel: SynthViewModel) = Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    val synthUiState by viewModel.uiState.collectAsState()

    Column {
        WaveShapeSelection(onUpdateWavetable = viewModel::updateWaveTable)
        ControlsPanel(
            frequencyUiState = synthUiState.frequencyControlUiState,
            onUpdateFrequency = viewModel::onFrequencyChange
        )
    }
}