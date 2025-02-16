package rauch.ula.minisynth.ui.components

import android.widget.Button
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import rauch.ula.minisynth.synth.viewmodel.PlayButtonUiState
import rauch.ula.minisynth.synth.viewmodel.WaveTableButtonUiState

@Composable
fun WavetableButton(
    uiState: WaveTableButtonUiState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val buttonColors =
        if (!uiState.isSelected) {
            ButtonDefaults.buttonColors().copy(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            )
        } else {
            ButtonDefaults.buttonColors()
        }
    Button(
        modifier = modifier,
        colors = buttonColors,
        onClick = onClick,
    ) {
        Text(uiState.label)
    }
}

@Composable
fun PlayButton(
    modifier: Modifier = Modifier,
    uiState: PlayButtonUiState,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(uiState.icon, contentDescription = "Play button")
    }
}
