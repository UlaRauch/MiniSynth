package rauch.ula.minisynth.synth.viewmodel

import androidx.annotation.StringRes
import rauch.ula.minisynth.R

data class SynthUiState(
    val frequencyControlUiState: FrequencyControlUiState,
) {
    companion object {
        fun init() = SynthUiState(frequencyControlUiState = FrequencyControlUiState.init())

        fun create(frequency: Float, thumbPosition: Float) = SynthUiState(
            frequencyControlUiState = FrequencyControlUiState.create(
                frequency,
                thumbPosition
            )
        )
    }
}

data class FrequencyControlUiState(
    val frequency: Float,
    val thumbPosition: Float,
    @StringRes val labelStringResourceId: Int
) {
    companion object {
        fun init() = FrequencyControlUiState(0f, 0f, R.string.frequency_value)

        fun create(frequency: Float, thumbPosition: Float) =
            FrequencyControlUiState(frequency, thumbPosition, R.string.frequency_value)
    }
}