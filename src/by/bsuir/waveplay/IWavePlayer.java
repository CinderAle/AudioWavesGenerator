package by.bsuir.waveplay;

import by.bsuir.wavegen.WaveGenerator;

public interface IWavePlayer {
    void play(double duration, double frequency);
    void addWave(WaveGenerator waveGenerator);
}
