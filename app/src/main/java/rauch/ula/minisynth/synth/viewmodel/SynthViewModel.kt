package rauch.ula.minisynth.synth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rauch.ula.minisynth.synth.Constants
import rauch.ula.minisynth.synth.model.Wavetable
import rauch.ula.minisynth.synth.model.WavetableSynthesizer
import kotlin.math.exp
import kotlin.math.ln

class SynthViewModel : ViewModel() {
    private var defaultFrequency = Constants.DEF_FREQUENCY
    private val frequencyRange = Constants.MIN_FREQUENCY..Constants.MAX_FREQUENCY

    private var _uiState = MutableStateFlow(SynthUiState.init())
    val uiState = _uiState.asStateFlow()

    private var wavetable = Wavetable.SINE

    var synthesizer: WavetableSynthesizer? = null
        set(value) {
            field = value
            applyParameters()
        }

    init {
        _uiState.value = SynthUiState.create(
            frequency = defaultFrequency,
            thumbPosition = sliderPositionFromFrequencyInHz(defaultFrequency)
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

    fun updateWaveTable(newWaveTable: Wavetable) {
        wavetable = newWaveTable
        viewModelScope.launch {
            synthesizer?.setWavetable(wavetable)
        }
    }

    fun onFrequencyChange(thumbPosition: Float) {
        val frequencyInHz = frequencyInHzFromSliderPosition(thumbPosition)
        _uiState.update {
            Log.i(this.toString(), "Frequency changed to: $frequencyInHz")
            it.copy(
                frequencyControlUiState = FrequencyControlUiState.create(
                    frequency = frequencyInHz,
                    thumbPosition = thumbPosition
                )
            )
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
            rangePosition: Float
        ) =
            range.start + (range.endInclusive - range.start) * rangePosition

        fun rangePositionFromValue(
            range: ClosedFloatingPointRange<Float>,
            value: Float
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