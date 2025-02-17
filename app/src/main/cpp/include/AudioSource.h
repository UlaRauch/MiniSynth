#pragma once

namespace minisynth {
    class AudioSource {
    public:
        virtual ~AudioSource() = default;

        // TODO: generate samples as block
        // Return 1 sample of audio to be played back
        virtual float getSample() = 0;

        // A callback invoked when the audio stream is stopped
        virtual void onPlaybackStopped() = 0;
    };
}  // namespace minisynth