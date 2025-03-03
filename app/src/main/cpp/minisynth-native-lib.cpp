#include <jni.h>
#include <memory>
#include "Log.h"
#include "WavetableSynthesizer.h"
#include "WavetableShape.h"


extern "C" {
JNIEXPORT jlong JNICALL
Java_rauch_ula_minisynth_synth_model_NativeWavetableSynthesizer_create(
        JNIEnv *env,
        jobject thiz) {
    auto synthesizer = std::make_unique<minisynth::WavetableSynthesizer>();


    if (not synthesizer) {
        LOGD("Failed to create the synthesizer.");
        synthesizer.reset(nullptr);
    }

    return reinterpret_cast<jlong>(synthesizer.release());
}

JNIEXPORT void JNICALL
Java_rauch_ula_minisynth_synth_model_NativeWavetableSynthesizer_delete(
        JNIEnv *env,
        jobject thiz,
        jlong synthesizerHandle
    ) {
    auto* synthesizer = reinterpret_cast<minisynth::WavetableSynthesizer*>(synthesizerHandle);

    if (not synthesizer) {
        LOGD("Attempt to destroy an unitialized synthesizer.");
        return;
    }

    delete synthesizer;
}

JNIEXPORT void JNICALL
Java_rauch_ula_minisynth_synth_model_NativeWavetableSynthesizer_play(
        JNIEnv *env,
        jobject thiz,
        jlong synthesizerHandle
) {
    auto* synthesizer = reinterpret_cast<minisynth::WavetableSynthesizer*>(synthesizerHandle);

    if (synthesizer) {
        synthesizer->play();
    } else {
        LOGD("Synthesizer not created. Please, create the synthesizer first by calling create().");
    }
}

JNIEXPORT void JNICALL
Java_rauch_ula_minisynth_synth_model_NativeWavetableSynthesizer_stop(
        JNIEnv *env,
        jobject thiz,
        jlong synthesizerHandle
     ) {
    auto* synthesizer = reinterpret_cast<minisynth::WavetableSynthesizer*>(synthesizerHandle);

    if (synthesizer) {
        synthesizer->stop();
    } else {
        LOGD(
                "Synthesizer not created. Please, create the synthesizer first by "
                "calling create().");
    }
}


JNIEXPORT jboolean JNICALL
Java_rauch_ula_minisynth_synth_model_NativeWavetableSynthesizer_isPlaying(
        JNIEnv* env,
        jobject obj,
        jlong synthesizerHandle) {
    auto* synthesizer =
            reinterpret_cast<minisynth::WavetableSynthesizer*>(
                    synthesizerHandle);

    if (not synthesizer) {
        LOGD(
                "Synthesizer not created. Please, create the synthesizer first by "
                "calling create().");
        return false;
    }

    return synthesizer->isPlaying();
}

JNIEXPORT void JNICALL
Java_rauch_ula_minisynth_synth_model_NativeWavetableSynthesizer_setFrequency(
        JNIEnv* env,
        jobject obj,
        jlong synthesizerHandle,
        jfloat frequencyInHz) {
    auto* synthesizer =
            reinterpret_cast<minisynth::WavetableSynthesizer*>(
                    synthesizerHandle);
    const auto nativeFrequency = static_cast<float>(frequencyInHz);

    if (synthesizer) {
        synthesizer->setFrequency(nativeFrequency);
    } else {
        LOGD(
                "Synthesizer not created. Please, create the synthesizer first by "
                "calling create().");
    }
}

JNIEXPORT void JNICALL
Java_rauch_ula_minisynth_synth_model_NativeWavetableSynthesizer_setVolume(
        JNIEnv* env,
        jobject obj,
        jlong synthesizerHandle,
        jfloat volumeInDb) {
    auto* synthesizer =
            reinterpret_cast<minisynth::WavetableSynthesizer*>(
                    synthesizerHandle);
    const auto nativeVolume = static_cast<float>(volumeInDb);

    if (synthesizer) {
        synthesizer->setVolume(nativeVolume);
    } else {
        LOGD(
                "Synthesizer not created. Please, create the synthesizer first by "
                "calling create().");
    }
}

JNIEXPORT void JNICALL
Java_rauch_ula_minisynth_synth_model_NativeWavetableSynthesizer_setWavetable(
        JNIEnv* env,
        jobject obj,
        jlong synthesizerHandle,
        jint wavetable) {
    auto* synthesizer =
            reinterpret_cast<minisynth::WavetableSynthesizer*>(
                    synthesizerHandle);
    const auto nativeWavetable = static_cast<minisynth::WavetableShape>(wavetable);

    if (synthesizer) {
        synthesizer->setWavetable(nativeWavetable);
    } else {
        LOGD(
                "Synthesizer not created. Please, create the synthesizer first by "
                "calling create().");
    }
}
}