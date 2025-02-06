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
import rauch.ula.minisynth.synth.model.WaveTableShapes
import rauch.ula.minisynth.ui.components.WavetableButton

@Composable
fun WaveShapeSelection(modifier: Modifier = Modifier) = Row(
    modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight(0.5f) // TODO: sizes
        .border(BorderStroke(5.dp, Color.DarkGray)),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .border(BorderStroke(5.dp, Color.Black)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (shape in WaveTableShapes.entries) {
                    WavetableButton(
                        modifier = modifier,
                        onClick = {},
                        label = shape.toString())
                }
            }
        }
}