package rauch.ula.minisynth.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import rauch.ula.minisynth.R


@Composable
fun WavetableButton(
    modifier: Modifier,
    onClick: () -> Unit,
    label: String,
) {
    Button(modifier = modifier, onClick = onClick) {
        Text(label)
    }
}

@Composable
fun PlayButton(modifier: Modifier) {
    Button(modifier = modifier,
        onClick = {}) {
        Text(stringResource(R.string.play))
    }
}