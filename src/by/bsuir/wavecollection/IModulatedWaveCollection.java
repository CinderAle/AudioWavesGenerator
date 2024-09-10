package by.bsuir.wavecollection;

import by.bsuir.modulation.Modulator;

public interface IModulatedWaveCollection {
    double[] getAMWaves(double duration, Modulator modulator);
    double[] getFMWaves(double duration, Modulator modulator);
}
