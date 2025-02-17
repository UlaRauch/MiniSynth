#pragma once

#include <cstdint>

namespace minisynth {
    class AudioPlayer {
    public:
        virtual ~AudioPlayer() = default;

        // Start the audio device
        virtual int32_t play() = 0;

        virtual void stop() = 0;
    };
}