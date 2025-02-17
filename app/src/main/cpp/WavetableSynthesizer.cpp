#include "Log.h"
#include "WavetableSynthesizer.h"
#include "WavetableSynthesizer.h"
#include "OboeAudioPlayer.h"
#include "WavetableOscillator.h"

namespace minisynth {
    WavetableSynthesizer::WavetableSynthesizer()
        : _oscillator{std::make_shared<A4Oscillator>(samplingRate)},
            _audioPlayer{std::make_unique<OboeAudioPlayer>(_oscillator, samplingRate)} {}

    WavetableSynthesizer::~WavetableSynthesizer() = default;

    bool WavetableSynthesizer::isPlaying() const {
        LOGD("isPlaying() called.");
        return _isPlaying;
    }

    void WavetableSynthesizer::play() {
        LOGD("play() called.");

        const auto result = _audioPlayer->play();
        if (result == 0) {
            _isPlaying = true;
        } else {
            LOGD("Could not start playblack.");
        }
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

        _audioPlayer->stop();
        _isPlaying = false;
    }
}