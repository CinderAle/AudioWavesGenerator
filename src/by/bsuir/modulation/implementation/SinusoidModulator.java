package by.bsuir.modulation.implementation;

import by.bsuir.modulation.Modulator;

public class SinusoidModulator extends Modulator {
    public SinusoidModulator(double frequency, double amplitude) {
        super(frequency, amplitude);
    }

//    @Override
//    public double getModulatedValue(int sample, int sampleRate) {
//        return amplitude * (1 - Math.cos( 2 * Math.PI * this.frequency * sample / sampleRate));
//    }

    @Override
    public double getModulatedValue(int sample, int sampleRate) {
        this.result += Math.sin(2 * Math.PI * sample * this.frequency / sampleRate) * 2 * Math.PI * this.frequency / sampleRate;
        return amplitude * result;
    }
}
