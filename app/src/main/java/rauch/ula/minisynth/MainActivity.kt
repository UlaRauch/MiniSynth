package rauch.ula.minisynth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import rauch.ula.minisynth.synth.LoggingWavetableSynthesizer
import rauch.ula.minisynth.synth.model.NativeWavetableSynthesizer
import rauch.ula.minisynth.synth.viewmodel.SynthViewModel
import rauch.ula.minisynth.ui.SynthScreen
import rauch.ula.minisynth.ui.theme.MiniSynthTheme

class MainActivity : ComponentActivity() {
    private val synthesizer = NativeWavetableSynthesizer() // instantiates the c++ synthesizer
    private val synthesizerViewModel: SynthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycle.addObserver(synthesizer)

        synthesizerViewModel.synthesizer = synthesizer

        setContent {
            MiniSynthTheme {
                SynthScreen(viewModel = synthesizerViewModel)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        lifecycle.removeObserver(synthesizer)
    }

    override fun onResume() {
        super.onResume()
        synthesizerViewModel.applyParameters()
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiniSynthTheme {
        Greeting("Android")
    }
}
