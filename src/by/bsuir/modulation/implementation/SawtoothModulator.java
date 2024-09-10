package by.bsuir.modulation.implementation;

import by.bsuir.formula.implementation.SawtoothFormula;
import by.bsuir.modulation.Modulator;

public class SawtoothModulator extends Modulator {
    public SawtoothModulator(double frequency, double amplitude) {
        super(frequency, amplitude);
    }

    @Override
    public double getModulatedValue(int sample, int sampleRate) {
        this.result += new SawtoothFormula().calculate(2 * Math.PI * frequency * sample / sampleRate) * 2 * Math.PI * frequency / sampleRate;
        return result;
    }
}
