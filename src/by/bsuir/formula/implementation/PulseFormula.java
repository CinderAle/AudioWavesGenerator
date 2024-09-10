package by.bsuir.formula.implementation;

import by.bsuir.formula.IFormula;

public class PulseFormula implements IFormula {
    private final double dutyCycle;

    public PulseFormula(double dutyCycle) {
        this.dutyCycle = dutyCycle;
    }

    @Override
    public double calculate(double argument) {
        double value = (argument % (2 * Math.PI)) / (2 * Math.PI);
        return value <= dutyCycle ? 1 : 0;
    }
}
