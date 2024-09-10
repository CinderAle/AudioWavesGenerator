package by.bsuir.wavegen.implementation;

import by.bsuir.wavegen.FrequencyWaveGenerator;

public class SawtoothWaveGenerator extends FrequencyWaveGenerator {
    public SawtoothWaveGenerator(double frequency) {
        super(frequency);
    }

    @Override
    public double[] generateWave(int totalSamples) {
        double[] wave = new double[totalSamples];
        double fraction = 2 * Math.PI * this.frequency / totalSamples;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = 1 / Math.PI * (((fraction * i + Math.PI) % (2 * Math.PI)) - Math.PI);
        }

        return wave;
    }
}
