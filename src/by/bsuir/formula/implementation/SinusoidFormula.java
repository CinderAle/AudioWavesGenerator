package by.bsuir.formula.implementation;

import by.bsuir.formula.IFormula;

public class SinusoidFormula implements IFormula {
    @Override
    public double calculate(double argument) {
        return Math.sin(argument);
    }
}
