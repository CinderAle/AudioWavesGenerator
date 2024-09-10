package by.bsuir.modulation;

public abstract class Modulator implements IModulator {
    protected double frequency;
    protected double amplitude;
    protected double result;

    public Modulator(double frequency, double amplitude) {
        this.frequency = frequency;
        this.amplitude = amplitude;
        this.result = 0.0;
    }
}
