package by.bsuir.wavegen.implementation;

import by.bsuir.wavegen.WaveGenerator;

import java.util.function.Supplier;

public class SinusoidWaveGenerator extends WaveGenerator {
    @Override
    public double[] generateWave(int totalSamples, Supplier<Double> frequencyFunction) {
        double[] wave = new double[totalSamples];
        double frequency = frequencyFunction.get();
        double fraction = 2.0 * Math.PI * frequency / this.sampleRate;

        for(int i = 0;i < totalSamples;i++) {
            wave[i] = Math.sin(i * fraction);
        }

        return wave;
    }
}

