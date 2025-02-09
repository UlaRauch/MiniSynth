package rauch.ula.minisynth.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import rauch.ula.minisynth.synth.viewmodel.FrequencyControlUiState

@Composable
fun Frequency(
    modifier: Modifier = Modifier,
    uiState: FrequencyControlUiState,
    onValueChange: (Float) -> Unit
) {
    val sliderPosition = rememberSaveable { mutableFloatStateOf(uiState.thumbPosition) } // TODO: take value from viewItem instead

    FrequencyControlContent(
        modifier = modifier,
        value = sliderPosition.floatValue,
        onValueChange = {
            sliderPosition.floatValue = it
            onValueChange(it)
        },
        valueRange = 0F..1F,
        frequencyValueLabel = String.format(
            stringResource(uiState.labelStringResourceId),
            uiState.frequency
        )
    )
}

@Composable
private fun FrequencyControlContent(
    modifier: Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    frequencyValueLabel: String
) {
    // TODO: move up to use in other composables
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val padding = screenHeight / 20

    Column(
        modifier = modifier.padding(vertical = padding.dp, horizontal = (padding/2).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(padding.dp)
    ) {
        Text(frequencyValueLabel)
        VerticalSlider(
            modifier,
            value = value,
            valueRange = valueRange,
            onValueChange = onValueChange
        )
    }
}