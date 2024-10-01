package by.bsuir.modulation;

public interface IModulator {
    double getModulatedValue(int sample, int sampleRate);
    double getRelativeValue(int sample, int sampleRate);
}
