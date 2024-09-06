package by.bsuir.wavecollection;

import by.bsuir.wavegen.WaveGenerator;
import by.bsuir.wavegen.implementation.WhiteNoiseWaveGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class WaveCollection implements IWaveCollection {
    private final ArrayList<WaveGenerator> waves;
    private final int sampleRate;

    public WaveCollection(int sampleRate) {
        this.waves = new ArrayList<>();
        this.sampleRate = sampleRate;
    }

    public WaveCollection(int sampleRate, WaveGenerator waveGenerator) {
        this.waves = new ArrayList<>(List.of(new WaveGenerator[]{waveGenerator}));
        this.sampleRate = sampleRate;
    }

    public WaveCollection(int sampleRate, ArrayList<WaveGenerator> waves) {
        this.waves = waves;
        this.sampleRate = sampleRate;
    }

    @Override
    public IWaveCollection add(WaveGenerator generator) {
        this.waves.add(generator);
        generator.setSampleRate(this.sampleRate);
        return this;
    }

    @Override
    public double[] getWave(double duration, double frequency) {
        int totalSamples = (int)(this.sampleRate * duration);
        double[] samples = new double[totalSamples];

        for(WaveGenerator generator : waves) {
            Supplier<Double> frequencyFunction = generator.getClass().equals(WhiteNoiseWaveGenerator.class) ? () -> {
                Random random = new Random();
                return random.nextDouble(0, 1);
            } : () -> frequency;
            double[] wave = generator.generateWave(totalSamples, frequencyFunction);
            for(int i = 0; i < totalSamples; i++) {
                samples[i] += wave[i];
            }
        }

        return samples;
    }
}
