package rauch.ula.minisynth.ui.panels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import rauch.ula.minisynth.synth.viewmodel.FrequencyControlUiState
import rauch.ula.minisynth.synth.viewmodel.VolumeControlUiState
import rauch.ula.minisynth.ui.components.Frequency
import rauch.ula.minisynth.ui.components.Volume

@Composable
fun ControlsPanel(
    frequencyControlUiState: FrequencyControlUiState,
    volumeControlUiState: VolumeControlUiState,
    onUpdateFrequency: (sliderPosition: Float) -> Unit,
    onUpdateVolume: (volume: Float) -> Unit,
) = Row(
    modifier =
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    horizontalArrangement = Arrangement.SpaceAround,
    verticalAlignment = Alignment.CenterVertically,
) {
    Frequency(
        modifier =
            Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight(),
        uiState = frequencyControlUiState,
        onValueChange = onUpdateFrequency,
    )
    Volume(
        modifier =
            Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight(),
        uiState = volumeControlUiState,
        onValueChange = onUpdateVolume,
    )
}
