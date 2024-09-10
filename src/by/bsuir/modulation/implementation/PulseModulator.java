package by.bsuir.modulation.implementation;

import by.bsuir.formula.implementation.PulseFormula;
import by.bsuir.modulation.Modulator;

public class PulseModulator extends Modulator {
    public PulseModulator(double frequency, double amplitude, double dutyCycle) {
        super(frequency, amplitude);
        this.formula = new PulseFormula(dutyCycle);
    }

    @Override
    public double getModulatedValue(int sample, int sampleRate) {
        this.result = formula.calculate(2 * Math.PI * sample * frequency / sampleRate);

        return amplitude * this.result;
    }
}
