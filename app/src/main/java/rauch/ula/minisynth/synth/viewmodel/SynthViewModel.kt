package rauch.ula.minisynth.synth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rauch.ula.minisynth.synth.Constants
import rauch.ula.minisynth.synth.model.WavetableShape
import rauch.ula.minisynth.synth.model.WavetableSynthesizer
import rauch.ula.minisynth.synth.viewmodel.WavetableSynthesizerViewModel.LinearToExponentialConverter.exponentialToLinear
import rauch.ula.minisynth.synth.viewmodel.WavetableSynthesizerViewModel.LinearToExponentialConverter.linearToExponential
import rauch.ula.minisynth.synth.viewmodel.WavetableSynthesizerViewModel.LinearToExponentialConverter.rangePositionFromValue
import rauch.ula.minisynth.synth.viewmodel.WavetableSynthesizerViewModel.LinearToExponentialConverter.valueFromRangePosition
import kotlin.math.exp
import kotlin.math.ln

// TODO: complete uistate with wavetable, play etc.
// TODO: separate appstate from uistate
class SynthViewModel : ViewModel() {
    private var defaultFrequency = Constants.DEF_FREQUENCY
    private val frequencyRange = Constants.MIN_FREQUENCY..Constants.MAX_FREQUENCY

    private val defaultVolume = Constants.DEF_VOLUME
    private val volumeRange = Constants.MIN_VOLUME..Constants.MAX_VOLUME

    private var _volume = MutableStateFlow(defaultVolume)
    val volume = _volume.asStateFlow()

    private var wavetable = WavetableShape.SINE

    var synthesizer: WavetableSynthesizer? = null
        set(value) {
            field = value
            applyParameters()
        }

    private var _uiState = MutableStateFlow(SynthUiState.init())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value =
            SynthUiState.create(
                isPlaying = false,
                selectedShape = WavetableShape.SINE,
                frequency = defaultFrequency,
                frequencyThumbPosition = sliderPositionFromFrequencyInHz(defaultFrequency),
                volume = defaultVolume,
            )
    }

//    // TODO: was brauch ich noch?
//    private val _frequency = MutableStateFlow(defaultFrequency)
//    val frequency: StateFlow<Float> = _frequency.asStateFlow()

    fun applyParameters() {
        viewModelScope.launch {
            synthesizer?.setFrequency(uiState.value.frequencyControlUiState.frequency)
            synthesizer?.setWavetable(wavetable)
        }
    }

    fun togglePlay() {
        _uiState.update { it.copy(playButtonUiState = it.playButtonUiState.toggle()) }
        viewModelScope.launch {
            if (synthesizer?.isPlaying() == true) synthesizer?.stop() else synthesizer?.play()
        }
    }

    fun updateWaveTable(newWaveTable: WavetableShape) {
        _uiState.update {
            it.copy(wavetableSelectionUIState = WavetableSelectionUIState.create(newWaveTable))
        }
        viewModelScope.launch {
            synthesizer?.setWavetable(wavetable)
        }
    }

    fun onVolumeChange(volume: Float) {
        _uiState.update {
            it.copy(
                volumeControlUiState = VolumeControlUiState.create(volume),
            )
        }
        viewModelScope.launch {
            synthesizer?.setVolume(volume)
        }
    }

    fun onFrequencyChange(thumbPosition: Float) {
        val frequencyInHz = frequencyInHzFromSliderPosition(thumbPosition)

        _uiState.update {
            it.copy(
                frequencyControlUiState =
                    FrequencyControlUiState.create(
                        frequency = frequencyInHz,
                        thumbPosition = thumbPosition,
                    ),
            )
        }
        viewModelScope.launch {
            synthesizer?.setFrequency(frequencyInHz)
        }
    }

    // wolfsound
    private fun frequencyInHzFromSliderPosition(sliderPosition: Float): Float {
        val rangePosition = linearToExponential(sliderPosition)
        return valueFromRangePosition(frequencyRange, rangePosition)
    }

    fun sliderPositionFromFrequencyInHz(frequencyInHz: Float): Float {
        val rangePosition = rangePositionFromValue(frequencyRange, frequencyInHz)
        return exponentialToLinear(rangePosition)
    }

    // taken unchanged from wolfsound
    companion object LinearToExponentialConverter {
        private const val MINIMUM_VALUE = 0.001f

        fun linearToExponential(value: Float): Float {
            assert(value in 0f..1f)

            if (value < MINIMUM_VALUE) {
                return 0f
            }

            return exp(ln(MINIMUM_VALUE) - ln(MINIMUM_VALUE) * value)
        }

        fun valueFromRangePosition(
            range: ClosedFloatingPointRange<Float>,
            rangePosition: Float,
        ) = range.start + (range.endInclusive - range.start) * rangePosition

        fun rangePositionFromValue(
            range: ClosedFloatingPointRange<Float>,
            value: Float,
        ): Float {
            assert(value in range)

            return (value - range.start) / (range.endInclusive - range.start)
        }

        fun exponentialToLinear(rangePosition: Float): Float {
            assert(rangePosition in 0f..1f)

            if (rangePosition < MINIMUM_VALUE) {
                return rangePosition
            }

            return (ln(rangePosition) - ln(MINIMUM_VALUE)) / (-ln(MINIMUM_VALUE))
        }
    }
}
