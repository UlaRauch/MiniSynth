package rauch.ula.minisynth.synth.model

import androidx.annotation.StringRes
import rauch.ula.minisynth.R

enum class WavetableShape {
    SINE,
    TRIANGLE,
    SQUARE,
    SAW,
}

@StringRes
fun WavetableShape.toStringResource() =
    when (this) {
        WavetableShape.SINE -> R.string.sine
        WavetableShape.TRIANGLE -> R.string.triangle
        WavetableShape.SQUARE -> R.string.square
        WavetableShape.SAW -> R.string.sawtooth
    }
