package rauch.ula.minisynth.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import rauch.ula.minisynth.synth.model.Wavetable

@Composable
fun WaveShapeSelection(
    modifier: Modifier = Modifier,
    onUpdateWavetable: (Wavetable) -> Unit
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight(0.25f), // TODO: sizes
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
) {
    for (wavetable in Wavetable.entries) {
        WavetableButton(
            modifier = modifier,
            onClick = {
                onUpdateWavetable(wavetable)
            },
            label = stringResource(wavetable.toResourceString())
        )
    }
}