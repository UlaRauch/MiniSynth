package rauch.ula.minisynth.ui.panels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import rauch.ula.minisynth.R
import rauch.ula.minisynth.synth.Constants

@Composable
fun Frequency(modifier: Modifier = Modifier) {
    val sliderPosition = rememberSaveable { mutableStateOf(Constants.DEF_FREQUENCY) }

    FrequencyControlContent(
        modifier = modifier,
        value = sliderPosition.value,
        onValueChange = {
            sliderPosition.value = it
        },
        valueRange = Constants.MIN_FREQUENCY ..Constants.MAX_FREQUENCY,
        frequencyValueLabel = stringResource(
            R.string.frequency_value,
            sliderPosition.value)
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
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val sliderHeight = screenHeight / 4

    Slider(modifier = modifier
        .width(sliderHeight.dp)
        .rotate(270f),
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(modifier = modifier, text = frequencyValueLabel)
    }
}