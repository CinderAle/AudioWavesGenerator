package by.bsuir.wavegen.implementation;

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
}
