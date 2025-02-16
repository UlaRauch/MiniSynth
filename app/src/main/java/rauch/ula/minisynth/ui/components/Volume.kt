package rauch.ula.minisynth.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import rauch.ula.minisynth.synth.viewmodel.VolumeControlUiState

@Composable
fun Volume(
    modifier: Modifier = Modifier,
    uiState: VolumeControlUiState,
    onValueChange: (Float) -> Unit,
) {
    val volume = rememberSaveable { mutableStateOf(uiState.volume) }

    VolumeControlContent(
        modifier = modifier,
        uiState = uiState,
        onValueChange = onValueChange,
    )
}

@Composable
private fun VolumeControlContent(
    modifier: Modifier,
    uiState: VolumeControlUiState,
    onValueChange: (Float) -> Unit,
) {
    // TODO: move up to use in other composables
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val padding = screenHeight / 20

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(padding.dp),
    ) {
        Text(uiState.label)
        VerticalSlider(
            modifier = modifier.padding(vertical = padding.dp, horizontal = (padding / 2).dp),
            value = uiState.volume,
            valueRange = uiState.sliderRange,
            onValueChange = onValueChange,
        )
    }
}
