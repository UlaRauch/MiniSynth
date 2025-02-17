#pragma once

#include <memory>

namespace minisynth {
    class AudioSource;
    class AudioPlayer;

    enum class WavetableShape {
        SINE,
        TRIANGLE,
        SQUARE,
        SAW,
    };

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
        bool _isPlaying = false;
        std::shared_ptr<AudioSource> _oscillator;
        std::unique_ptr<AudioPlayer> _audioPlayer;
    };
}