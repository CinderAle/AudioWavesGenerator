package by.bsuir.wavegen.implementation;

import by.bsuir.modulation.Modulator;
import by.bsuir.wavegen.FrequencyWaveGenerator;

public class SawtoothWaveGenerator extends FrequencyWaveGenerator {
    public SawtoothWaveGenerator(double frequency) {
        super(frequency);
    }

    @Override
    public double[] generateWave(int totalSamples) {
        double[] wave = new double[totalSamples];

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = 1 / Math.PI * (((fraction * i + Math.PI) % (2 * Math.PI)) - Math.PI);
        }

        return wave;
    }

    @Override
    public double[] generateAMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fi = 0;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = (1 / Math.PI * (((fi + Math.PI) % (2 * Math.PI)) - Math.PI)) * modulator.getModulatedValue(i, this.sampleRate);
            fi += fraction;
        }

        return wave;
    }

    @Override
    public double[] generateFMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fi = 0;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = (1 / Math.PI * (((fi + Math.PI) % (2 * Math.PI)) - Math.PI));
            fi += fraction * modulator.getModulatedValue(i, this.sampleRate);
        }

        return wave;
    }
}
