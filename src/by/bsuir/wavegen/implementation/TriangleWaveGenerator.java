package by.bsuir.wavegen.implementation;

import by.bsuir.modulation.Modulator;
import by.bsuir.wavegen.FrequencyWaveGenerator;

public class TriangleWaveGenerator extends FrequencyWaveGenerator {
    public TriangleWaveGenerator(double frequency) {
        super(frequency);
    }

    @Override
    public double[] generateWave(int totalSamples) {
        double[] wave = new double[totalSamples];
        double fraction = 2 * Math.PI * this.frequency / this.sampleRate;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = (2 / Math.PI * (Math.abs(((fraction * i + (3 * Math.PI / 2)) % (2 * Math.PI) - Math.PI)) - (Math.PI / 2)));
        }

        return wave;
    }

    @Override
    public double[] generateAMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fraction = 2 * Math.PI * this.frequency / this.sampleRate;
        double fi = 0;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = modulator.getModulatedValue(i, this.sampleRate) * (2 / Math.PI * (Math.abs(((fi + (3 * Math.PI / 2)) % (2 * Math.PI) - Math.PI)) - (Math.PI / 2)));
            fi += fraction;
        }

        return wave;
    }

    @Override
    public double[] generateFMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fraction = 2 * Math.PI * this.frequency / this.sampleRate;
        double fi = 0;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = (2 / Math.PI * (Math.abs(((fi + (3 * Math.PI / 2)) % (2 * Math.PI) - Math.PI)) - (Math.PI / 2)));
            fi += fraction * modulator.getModulatedValue(i, this.sampleRate);
        }

        return wave;
    }
}
