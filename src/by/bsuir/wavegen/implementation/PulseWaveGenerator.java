package by.bsuir.wavegen.implementation;

import by.bsuir.modulation.Modulator;
import by.bsuir.wavegen.FrequencyWaveGenerator;

public class PulseWaveGenerator extends FrequencyWaveGenerator {

    private final double dutyCycle;

    public PulseWaveGenerator(double frequency, double dutyCycle) {
        super(frequency);
        this.dutyCycle = dutyCycle;
    }

    @Override
    public double[] generateWave(int totalSamples) {
        double[] wave = new double[totalSamples];

        for (int i = 0; i < totalSamples; i++) {
            double value = (fraction * i) % (2 * Math.PI) / (2 * Math.PI);
            wave[i] = (value <= this.dutyCycle ? 1 : -1);
        }

        return wave;
    }

    @Override
    public double[] generateAMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fi = 0;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = modulator.getModulatedValue(i, this.sampleRate) * (fi % (2 * Math.PI) / (2 * Math.PI) <= this.dutyCycle ? 1 : -1);
            fi += fraction;
        }

        return wave;
    }

    @Override
    public double[] generateFMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fi = 0;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = (fi % (2 * Math.PI) / (2 * Math.PI) <= this.dutyCycle ? 1 : -1);
            fi += fraction * modulator.getModulatedValue(i, this.sampleRate);
        }

        return wave;
    }
}
