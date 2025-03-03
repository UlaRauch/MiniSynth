#include "WavetableFactory.h"
#include <cmath>
#include <vector>
#include "WavetableShape.h"
#include "MathConstants.h"

namespace minisynth {
    static constexpr auto WAVETABLE_LENGTH = 256;

    std::vector<float> generateSineWaveTable() {
        auto sineWaveTable = std::vector<float>(WAVETABLE_LENGTH);

        for (auto i = 0; i < WAVETABLE_LENGTH; i++) {
            sineWaveTable[i] = std::sin(2 * PI * static_cast<float>(i) / WAVETABLE_LENGTH);
        }

        return sineWaveTable;
    }

    std::vector<float> generateTriangleWaveTable() {
        auto triangleWaveTable = std::vector<float>(WAVETABLE_LENGTH, 0.f);

        constexpr auto HARMONICS_COUNT = 13;

        for (auto k = 1; k <= HARMONICS_COUNT; ++k) {
            for (auto j = 0; j < WAVETABLE_LENGTH; ++j) {
                const auto phase = 2.f * PI * 1.f * j / WAVETABLE_LENGTH;
                triangleWaveTable[j] += 8.f / std::pow(PI, 2.f) * std::pow(-1.f, k) *
                                        std::pow(2 * k - 1, -2.f) *
                                        std::sin((2.f * k - 1.f) * phase);
            }
        }

        return triangleWaveTable;
    }

    std::vector<float> generateSquareWaveTable() {
        auto squareWaveTable = std::vector<float>(WAVETABLE_LENGTH, 0.f);

        constexpr auto HARMONICS_COUNT = 7;

        for (auto k = 1; k <= HARMONICS_COUNT; ++k) {
            for (auto j = 0; j < WAVETABLE_LENGTH; ++j) {
                const auto phase = 2.f * PI * 1.f * j / WAVETABLE_LENGTH;
                squareWaveTable[j] += 4.f / PI * std::pow(2.f * k - 1.f, -1.f) *
                                      std::sin((2.f * k - 1.f) * phase);
            }
        }

        return squareWaveTable;
    }

    std::vector<float> generateSawWaveTable() {
        auto sawWaveTable = std::vector<float>(WAVETABLE_LENGTH, 0.f);

        constexpr auto HARMONICS_COUNT = 26;

        for (auto k = 1; k <= HARMONICS_COUNT; ++k) {
            for (auto j = 0; j < WAVETABLE_LENGTH; ++j) {
                const auto phase = 2.f * PI * 1.f * j / WAVETABLE_LENGTH;
                sawWaveTable[j] += 2.f / PI * std::pow(-1.f, k) * std::pow(k, -1.f) *
                                   std::sin(k * phase);
            }
        }

        return sawWaveTable;
    }

    std::vector<float> WavetableFactory::getWaveTable(WavetableShape shape) {
        switch (shape) {
            case WavetableShape::SINE:
                return sineWaveTable();
            case WavetableShape::TRIANGLE:
                return triangleWaveTable();
            case WavetableShape::SQUARE:
                return squareWaveTable();
            case WavetableShape::SAW:
                return sawWaveTable();
            default:
                return {WAVETABLE_LENGTH, 0.f};
        }
    }

    template<typename F>
    std::vector<float> generateWavetableOnce(std::vector<float>& wavetable, F&& generator) {
        if (wavetable.empty()) {
            wavetable = generator();
        }

        return wavetable;
    }

    std::vector<float> WavetableFactory::sineWaveTable() {
        return generateWavetableOnce(_sineWaveTable, &generateSineWaveTable);
    }

    std::vector<float> WavetableFactory::triangleWaveTable() {
        return generateWavetableOnce(_triangleWaveTable, &generateTriangleWaveTable);
    }

    std::vector<float> WavetableFactory::squareWaveTable() {
        return generateWavetableOnce(_squareWaveTable, &generateSquareWaveTable);
    }

    std::vector<float> WavetableFactory::sawWaveTable() {
        return generateWavetableOnce(_sawWaveTable, &generateSawWaveTable);
    }
}