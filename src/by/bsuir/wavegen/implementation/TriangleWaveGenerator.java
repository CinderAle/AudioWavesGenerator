package by.bsuir.wavegen.implementation;

import by.bsuir.wavegen.WaveGenerator;

import java.util.function.Supplier;

public class TriangleWaveGenerator extends WaveGenerator {
    @Override
    public double[] generateWave(int totalSamples, Supplier<Double> frequencyFunction) {
        double frequency = frequencyFunction.get();
        double[] wave = new double[totalSamples];
        double fraction = 2 * Math.PI * frequency / this.sampleRate;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = (2 / Math.PI * (Math.abs(((fraction * i + (3 * Math.PI / 2)) % (2 * Math.PI) - Math.PI)) - (Math.PI / 2)));
        }

        return wave;
    }
}
