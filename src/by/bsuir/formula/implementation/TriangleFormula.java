package by.bsuir.formula.implementation;

import by.bsuir.formula.IFormula;

public class TriangleFormula implements IFormula {

    @Override
    public double calculate(double argument) {
        return (2 / Math.PI * (Math.abs(((argument + (3 * Math.PI / 2)) % (2 * Math.PI) - Math.PI)) - (Math.PI / 2)));
    }
}
