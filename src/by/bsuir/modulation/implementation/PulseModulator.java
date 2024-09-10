package by.bsuir.modulation.implementation;

import by.bsuir.formula.implementation.PulseFormula;
import by.bsuir.modulation.Modulator;

public class PulseModulator extends Modulator {
    private double dutyCycle;
    public PulseModulator(double frequency, double amplitude, double dutyCycle) {
        super(frequency, amplitude);
        this.dutyCycle = dutyCycle;
    }

    @Override
    public double getModulatedValue(int sample, int sampleRate) {
        PulseFormula formula = new PulseFormula(this.dutyCycle);
        this.result = formula.calculate(2 * Math.PI * sample * frequency / sampleRate);

        return amplitude * this.result;
    }
}
