#include "Log.h"
#include "WavetableSynthesizer.h"
#include "WavetableSynthesizer.h"
#include "OboeAudioPlayer.h"
#include "WavetableOscillator.h"

namespace minisynth {
    WavetableSynthesizer::WavetableSynthesizer()
        : _oscillator{std::make_shared<WavetableOscillator>(_wavetableFactory.getWaveTable(_currentWavetable), samplingRate)},
            _audioPlayer{std::make_unique<OboeAudioPlayer>(_oscillator, samplingRate)} {}

    WavetableSynthesizer::~WavetableSynthesizer() = default;

    bool WavetableSynthesizer::isPlaying() const {
        LOGD("isPlaying() called.");
        return _isPlaying;
    }

    void WavetableSynthesizer::play() {
        LOGD("play() called.");

        std::lock_guard<std::mutex> lock(_mutex);
        const auto result = _audioPlayer->play();
        if (result == 0) {
            _isPlaying = true;
        } else {
            LOGD("Could not start playblack.");
        }
    }

    void WavetableSynthesizer::stop() {
        LOGD("stop() called.");

        std::lock_guard<std::mutex> lock(_mutex);
        _audioPlayer->stop();
        _isPlaying = false;
    }

    void WavetableSynthesizer::setFrequency(float frequencyInHz) {
        LOGD("Frequency set to %.2f Hz.", frequencyInHz);
        _oscillator->setFrequency(frequencyInHz);
    }

    float dbToAmplitude(float dB) {
        return std::pow(10.f, dB / 20.f);
    }

    void WavetableSynthesizer::setVolume(float volumeInDb) {
        LOGD("Volume set to %.2f dBFS.", volumeInDb);
        const auto amplitude = dbToAmplitude(volumeInDb);
        _oscillator->setAmplitude(amplitude);
    }

    void WavetableSynthesizer::setWavetable(WavetableShape wavetable) {
        LOGD("Wavetable set to %d.", static_cast<int>(wavetable));
        if (_currentWavetable != wavetable) {
            _currentWavetable = wavetable;
            _oscillator->setWavetable(_wavetableFactory.getWaveTable(wavetable));
        }
    }
}