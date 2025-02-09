package rauch.ula.minisynth.ui.panels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import rauch.ula.minisynth.synth.viewmodel.FrequencyControlUiState
import rauch.ula.minisynth.ui.components.Frequency
import rauch.ula.minisynth.ui.components.Volume


@Composable
fun ControlsPanel(
    frequencyUiState: FrequencyControlUiState,
    onUpdateFrequency: (sliderPosition: Float) -> Unit
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    // TODO: nur test
//    Column {
//        Text(frequency.toString())
//        Button(onClick = onButtonClick) { Text(name) }
//    }

    Frequency(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(),
        uiState = frequencyUiState,
        onValueChange = onUpdateFrequency
    )
    Volume(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight()
    )
}