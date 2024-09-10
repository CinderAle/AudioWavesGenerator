package by.bsuir.formula.implementation;

import by.bsuir.formula.IFormula;

public class SawtoothFormula implements IFormula {
    @Override
    public double calculate(double argument) {
        return 1 / Math.PI * (((argument + Math.PI) % (2 * Math.PI)) - Math.PI);
    }
}
