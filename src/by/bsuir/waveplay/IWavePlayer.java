package by.bsuir.waveplay;

import by.bsuir.wavegen.WaveGenerator;

public interface IWavePlayer {
    void play(double duration);
    void addWave(WaveGenerator waveGenerator);
}
