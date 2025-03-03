#pragma once
#include <vector>

namespace minisynth {
    enum class WavetableShape;

    class WavetableFactory {
    public:
        std::vector<float> getWaveTable(WavetableShape wavetable);
        std::vector<float> sineWaveTable();
        std::vector<float> triangleWaveTable();
        std::vector<float> squareWaveTable();
        std::vector<float> sawWaveTable();

    private:
        std::vector<float> _sineWaveTable;
        std::vector<float> _triangleWaveTable;
        std::vector<float> _squareWaveTable;
        std::vector<float> _sawWaveTable;
    };
}  // namespace wavetablesynthesizer