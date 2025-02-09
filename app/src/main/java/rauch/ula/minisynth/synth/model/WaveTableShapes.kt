package rauch.ula.minisynth.synth.model

import androidx.annotation.StringRes
import rauch.ula.minisynth.R

enum class WaveTableShapes {
    SINE,
    TRIANGLE,
    SQUARE,
    SAW
}

// TODO: maybe use this
@StringRes
fun WaveTableShapes.toResourceString() = when (this) {
    WaveTableShapes.SINE -> R.string.sine
    WaveTableShapes.TRIANGLE -> R.string.triangle
    WaveTableShapes.SQUARE -> R.string.square
    WaveTableShapes.SAW -> R.string.sawtooth
}