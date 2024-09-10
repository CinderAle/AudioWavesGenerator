package by.bsuir.waveplay;

import by.bsuir.modulation.Modulator;

public interface IModulatedPlayer {
    void playAM(double duration, Modulator modulator);
    void playFM(double duration, Modulator modulator);
}
