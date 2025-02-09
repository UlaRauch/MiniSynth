package rauch.ula.minisynth.synth.model


import androidx.annotation.StringRes
import rauch.ula.minisynth.R

enum class Wavetable {
    SINE{
        @StringRes
        override fun toResourceString(): Int {
            return R.string.sine
        }
    },

    TRIANGLE{
        @StringRes
        override fun toResourceString(): Int {
            return R.string.triangle
        }
    },

    SQUARE{
        @StringRes
        override fun toResourceString(): Int {
            return R.string.square
        }
    },

    SAW{
        @StringRes
        override fun toResourceString(): Int {
            return R.string.sawtooth
        }
    };

    @StringRes
    abstract fun toResourceString(): Int
}