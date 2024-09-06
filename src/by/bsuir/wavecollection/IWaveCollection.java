package by.bsuir.wavecollection;

import by.bsuir.wavegen.WaveGenerator;

public interface IWaveCollection {
    IWaveCollection add(WaveGenerator generator);
    double[] getWave(double duration, double frequency);
}
