package by.bsuir.wavegen;

import by.bsuir.modulation.Modulator;

public abstract class FrequencyWaveGenerator extends WaveGenerator {
    protected double frequency;

    public FrequencyWaveGenerator(double frequency) {
        super();
        this.frequency = frequency;
    }

    public abstract double[] generateAMWave(int totalSamples, Modulator modulator);
    public abstract double[] generateFMWave(int totalSamples, Modulator modulator);
}
