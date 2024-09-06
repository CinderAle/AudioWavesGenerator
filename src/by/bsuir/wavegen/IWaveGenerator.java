package by.bsuir.wavegen;

import java.util.function.Supplier;

public interface IWaveGenerator {
    double[] generateWave(int totalSamples, Supplier<Double> frequencyFunction);
}
