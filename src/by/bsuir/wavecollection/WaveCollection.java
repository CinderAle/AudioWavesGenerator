package by.bsuir.wavecollection;

import by.bsuir.modulation.Modulator;
import by.bsuir.wavegen.FrequencyWaveGenerator;
import by.bsuir.wavegen.WaveGenerator;

import java.util.ArrayList;
import java.util.List;

public class WaveCollection implements IWaveCollection, IModulatedWaveCollection {
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
    public double[] getWave(double duration) {
        int totalSamples = (int)(this.sampleRate * duration);
        double[] samples = new double[totalSamples];

        for(WaveGenerator generator : waves) {
            double[] wave = generator.generateWave(totalSamples);
            for(int i = 0; i < totalSamples; i++) {
                samples[i] += wave[i] / waves.size();
            }
        }

        return samples;
    }

    @Override
    public double[] getAMWaves(double duration, Modulator modulator) {
        int totalSamples = (int)(this.sampleRate * duration);
        double[] samples = new double[totalSamples];

        for(WaveGenerator generator : waves) {
            double[] wave;
            if(generator instanceof FrequencyWaveGenerator) {
                wave = ((FrequencyWaveGenerator) generator).generateAMWave(totalSamples, modulator);
            }
            else {
                wave = generator.generateWave(totalSamples);
            }
            for(int i = 0; i < totalSamples; i++) {
                samples[i] += wave[i];
            }
        }

        return samples;
    }

    @Override
    public double[] getFMWaves(double duration, Modulator modulator) {
        int totalSamples = (int)(this.sampleRate * duration);
        double[] samples = new double[totalSamples];

        for(WaveGenerator generator : waves) {
            double[] wave;
            if(generator instanceof FrequencyWaveGenerator) {
                wave = ((FrequencyWaveGenerator) generator).generateFMWave(totalSamples, modulator);
            }
            else {
                wave = generator.generateWave(totalSamples);
            }
            for(int i = 0; i < totalSamples; i++) {
                samples[i] += wave[i] / waves.size();
            }
        }

        return samples;
    }
}
