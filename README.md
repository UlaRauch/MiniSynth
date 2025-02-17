# MiniSynth App
Starting Point is this Tutorial: https://thewolfsound.com/android-synthesizer-2-graphical-user-interface-with-compose/

So far, the c++ part is taken unaltered (or not significantly) from wolfsound

Changes made:

- LiveData is replaced with StateFlow
- ui state is extracted to data classes
- ui design is changed
  - mark selected wavetable