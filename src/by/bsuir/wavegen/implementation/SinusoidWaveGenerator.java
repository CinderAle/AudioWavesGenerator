package by.bsuir.wavegen.implementation;

import by.bsuir.wavegen.FrequencyWaveGenerator;

public class SinusoidWaveGenerator extends FrequencyWaveGenerator {
    public SinusoidWaveGenerator(double frequency) {
        super(frequency);
    }

    @Override
    public double[] generateWave(int totalSamples) {
        double[] wave = new double[totalSamples];
        double fraction = 2.0 * Math.PI * this.frequency / this.sampleRate;

        for(int i = 0;i < totalSamples;i++) {
            wave[i] = Math.sin(i * fraction);
        }

        return wave;
    }
}

