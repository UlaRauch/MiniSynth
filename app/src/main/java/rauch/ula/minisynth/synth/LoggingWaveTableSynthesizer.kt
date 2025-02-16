package rauch.ula.minisynth.synth

import android.util.Log
import rauch.ula.minisynth.synth.model.WavetableShape
import rauch.ula.minisynth.synth.model.WavetableSynthesizer

class LoggingWavetableSynthesizer : WavetableSynthesizer {
    private var isPlaying = false

    override suspend fun play() {
        Log.d("LoggingWavetableSynthesizer", "play() called.")
        isPlaying = true
    }

    override suspend fun stop() {
        Log.d("LoggingWavetableSynthesizer", "stop() called.")
        isPlaying = false
    }

    override suspend fun isPlaying(): Boolean = isPlaying

    override suspend fun setFrequency(frequencyInHz: Float) {
        Log.d("LoggingWavetableSynthesizer", "Frequency set to $frequencyInHz Hz.")
    }

    override suspend fun setVolume(volumeInDb: Float) {
        Log.d("LoggingWavetableSynthesizer", "Volume set to $volumeInDb dB.")
    }

    override suspend fun setWavetable(wavetable: WavetableShape) {
        Log.d("LoggingWavetableSynthesizer", "Wavetable set to $wavetable")
    }
}
