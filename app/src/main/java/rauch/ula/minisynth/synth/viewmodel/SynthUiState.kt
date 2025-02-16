package rauch.ula.minisynth.synth.viewmodel

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import rauch.ula.minisynth.R
import rauch.ula.minisynth.synth.Constants
import rauch.ula.minisynth.synth.model.WavetableShape
import rauch.ula.minisynth.synth.model.toStringResource

data class SynthUiState(
    val playButtonUiState: PlayButtonUiState,
    val wavetableSelectionUIState: WavetableSelectionUIState,
    val frequencyControlUiState: FrequencyControlUiState,
    val volumeControlUiState: VolumeControlUiState,
) {
    companion object {
        fun init() =
            SynthUiState(
                playButtonUiState = PlayButtonUiState.init(),
                wavetableSelectionUIState = WavetableSelectionUIState.init(),
                frequencyControlUiState = FrequencyControlUiState.init(),
                volumeControlUiState = VolumeControlUiState.init(),
            )

        fun create(
            isPlaying: Boolean,
            selectedShape: WavetableShape,
            frequency: Float,
            frequencyThumbPosition: Float,
            volume: Float,
        ) = SynthUiState(
            playButtonUiState = PlayButtonUiState.create(isPlaying),
            wavetableSelectionUIState = WavetableSelectionUIState.create(selectedShape),
            frequencyControlUiState =
                FrequencyControlUiState.create(
                    frequency,
                    frequencyThumbPosition,
                ),
            volumeControlUiState = VolumeControlUiState.create(volume),
        )
    }
}

data class PlayButtonUiState(
    val isPlaying: Boolean,
) {
    // TODO: replace Icons
    val icon = if (isPlaying) Icons.Default.AddCircle else Icons.Default.PlayArrow

    fun toggle() = copy(isPlaying = !isPlaying)

    companion object {
        fun init() = PlayButtonUiState(isPlaying = false)

        fun create(isPlaying: Boolean) = PlayButtonUiState(isPlaying)
    }
}

data class WavetableSelectionUIState(
    val shapes: List<WaveTableButtonUiState>,
) {
    fun getUiStateForShape(shape: WavetableShape) = shapes.find { it.shape == shape }

    companion object {
        fun init() = WavetableSelectionUIState(emptyList())

        fun create(selectedShape: WavetableShape) =
            WavetableSelectionUIState(
                WavetableShape.entries.map {
                    WaveTableButtonUiState.create(it, it == selectedShape)
                },
            )
    }
}

data class WaveTableButtonUiState(
    val shape: WavetableShape,
    val isSelected: Boolean,
) {
    val label: String
        @Composable
        get() = stringResource(shape.toStringResource())

    companion object {
        fun init() = WaveTableButtonUiState(shape = WavetableShape.SINE, isSelected = false)

        fun create(
            shape: WavetableShape,
            isSelected: Boolean,
        ) = WaveTableButtonUiState(shape, isSelected)
    }
}

// TODO: calculate frequency
data class FrequencyControlUiState(
    val frequency: Float,
    val thumbPosition: Float,
) {
    val sliderRange = 0f..1f
    val label: String
        @Composable
        get() = String.format(stringResource(labelStringResourceId), frequency)

    companion object {
        @StringRes
        private val labelStringResourceId = R.string.frequency_value

        fun init() = FrequencyControlUiState(0f, 0f)

        fun create(
            frequency: Float,
            thumbPosition: Float,
        ) = FrequencyControlUiState(frequency, thumbPosition)
    }
}

data class VolumeControlUiState(
    val volume: Float,
) {
    val sliderRange = Constants.MIN_VOLUME..Constants.MAX_VOLUME
    val label: String
        @Composable
        get() = String.format(stringResource(labelStringResourceId), volume)

    companion object {
        @StringRes
        private val labelStringResourceId = R.string.volume_value

        fun init() = VolumeControlUiState(0f)

        fun create(volume: Float) = VolumeControlUiState(volume)
    }
}
