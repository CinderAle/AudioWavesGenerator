package by.bsuir.wavegen.implementation;

import by.bsuir.modulation.Modulator;
import by.bsuir.wavegen.FrequencyWaveGenerator;

public class SinusoidWaveGenerator extends FrequencyWaveGenerator {
    public SinusoidWaveGenerator(double frequency) {
        super(frequency);
    }

    @Override
    public double[] generateWave(int totalSamples) {
        double[] wave = new double[totalSamples];

        for(int i = 0;i < totalSamples;i++) {
            wave[i] = Math.sin(i * fraction);
        }

        return wave;
    }

    @Override
    public double[] generateAMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fi = 0;

        for(int i = 0;i < totalSamples;i++) {
            wave[i] = modulator.getModulatedValue(i, this.sampleRate) * Math.sin(fi);
            fi += fraction;

        }

        return wave;
    }

    @Override
    public double[] generateFMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fi = 0;

        for(int i = 0;i < totalSamples;i++) {
            wave[i] = Math.sin(fi);
            fi += modulator.getModulatedValue(i, this.sampleRate) * fraction;

        }

        return wave;
    }

}

