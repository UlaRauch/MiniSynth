package rauch.ula.minisynth.synth.model

interface WavetableSynthesizer {
    suspend fun play()

    suspend fun stop()

    suspend fun isPlaying(): Boolean

    suspend fun setFrequency(frequencyInHz: Float)

    suspend fun setVolume(volumeInDb: Float)

    suspend fun setWavetable(wavetable: WavetableShape)
}
