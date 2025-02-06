package rauch.ula.minisynth.ui.panels

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ControlsPanel() = Row(
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    Frequency(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight()
    )
    Volume(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight()
    )
}