#pragma once

namespace minisynth {
    enum class WavetableShape {
        SINE,
        TRIANGLE,
        SQUARE,
        SAW,
    };

    class WavetableSynthesizer {
    public:
        void play();
        void stop();
        bool isPlaying();
        void setFrequency(float frequencyInHz);
        void setVolume(float VolumeInDb);
        void setWavetable(WavetableShape wavetableShape);


    private:
        bool _isPlaying = false;
    };
}