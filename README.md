# MiniSynth App
WORK IN PROGRESS
Starting Point is this tutorial: https://thewolfsound.com/android-synthesizer-2-graphical-user-interface-with-compose/

Goals
  - create one or several simple instrument(s)
  - practice C++ by understanding the code and later altering functionality

So far, the c++ part is taken unaltered (or not significantly) from wolfsound

Changes made so far in the kotlin part:

- replaced LiveData with StateFlow
- extracted ui state to data classes
- changed ui design
  - vertical controllers
  - mark selected wavetable
