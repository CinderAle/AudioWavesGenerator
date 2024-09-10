package by.bsuir.modulation.implementation;

import by.bsuir.formula.implementation.TriangleFormula;
import by.bsuir.modulation.Modulator;

public class TriangleModulator extends Modulator {
    public TriangleModulator(double frequency, double amplitude) {
        super(frequency, amplitude);
    }

    @Override
    public double getModulatedValue(int sample, int sampleRate) {
        this.result += new TriangleFormula().calculate(2 * Math.PI * frequency * sample / sampleRate) * 2 * Math.PI * frequency / sampleRate;
        return amplitude * result;
    }
}
