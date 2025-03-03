#pragma once

#include <memory>
#include <mutex>
#include "WavetableShape.h"
#include "WavetableFactory.h"

namespace minisynth {
    class WavetableOscillator;

    class AudioPlayer;

    constexpr auto samplingRate = 48000;

    class WavetableSynthesizer {
    public:
        WavetableSynthesizer();
        ~WavetableSynthesizer();

        void play();

        void stop();

        bool isPlaying() const;

        void setFrequency(float frequencyInHz);

        void setVolume(float VolumeInDb);

        void setWavetable(WavetableShape wavetableShape);

    private:
        std::atomic<bool> _isPlaying{false};
        std::mutex _mutex;
        WavetableFactory _wavetableFactory;
        WavetableShape _currentWavetable{WavetableShape::SINE};
        std::shared_ptr<WavetableOscillator> _oscillator;
        std::unique_ptr<AudioPlayer> _audioPlayer;
    };
}