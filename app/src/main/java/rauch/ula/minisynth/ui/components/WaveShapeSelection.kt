package rauch.ula.minisynth.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rauch.ula.minisynth.synth.model.WavetableShape
import rauch.ula.minisynth.synth.viewmodel.WavetableSelectionUIState

@Composable
fun WaveShapeSelection(
    uiState: WavetableSelectionUIState,
    modifier: Modifier = Modifier,
    onUpdateWavetable: (WavetableShape) -> Unit,
) = LazyVerticalGrid(
    modifier = modifier.fillMaxHeight(0.25f),
    columns = GridCells.Fixed(2),
    contentPadding = PaddingValues(8.dp), // TODO: padding
    verticalArrangement = Arrangement.Center,
) {
    for (wavetable in WavetableShape.entries) {
        uiState.getUiStateForShape(wavetable)?.let {
            item {
                WavetableButton(
                    modifier = Modifier.padding(4.dp), // TODO: padding
                    uiState = it,
                    onClick = {
                        onUpdateWavetable(wavetable)
                    },
                )
            }
        }
    }
}
