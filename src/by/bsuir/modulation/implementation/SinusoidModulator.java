package by.bsuir.modulation.implementation;

import by.bsuir.formula.implementation.SinusoidFormula;
import by.bsuir.modulation.Modulator;

public class SinusoidModulator extends Modulator {
    public SinusoidModulator(double frequency, double amplitude) {
        super(frequency, amplitude);
        this.formula = new SinusoidFormula();
    }

    @Override
    public double getModulatedValue(int sample, int sampleRate) {
        this.result += formula.calculate(2 * Math.PI * sample * this.frequency / sampleRate) * 2 * Math.PI * this.frequency / sampleRate;
        return amplitude * result;
    }
}
