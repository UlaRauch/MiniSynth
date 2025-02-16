#include "Log.h"
#include "WavetableSynthesizer.h"
#include "include/WavetableSynthesizer.h"

namespace minisynth {
    bool WavetableSynthesizer::isPlaying() {
        LOGD("isPlaying() called.");
        return _isPlaying;
    }

    void WavetableSynthesizer::play() {
        LOGD("play() called.");
        _isPlaying = true;
    }

    void WavetableSynthesizer::setFrequency(float frequencyInHz) {
        LOGD("Frequency set to %.2f Hz.", frequencyInHz);
    }

    void WavetableSynthesizer::setVolume(float volumeInDb) {
        LOGD("Volume set to %.2f dBFS.", volumeInDb);
    }

    void WavetableSynthesizer::setWavetable(WavetableShape wavetable) {
        LOGD("Wavetable set to %d.", static_cast<int>(wavetable)); // TODO: ist immer 0
    }

    void WavetableSynthesizer::stop() {
        LOGD("stop() called.");
        _isPlaying = false;
    }
}