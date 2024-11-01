package by.bsuir.fourier;

import java.util.Arrays;

public class FastFourierTransformer implements IFourierTransformer {
    private double[] real;
    private double[] imag;

    public FastFourierTransformer() {
        real = new double[0];
        imag = new double[0];
    }

    @Override
    public double[] getAmplitudeSpectrum() {
        double[] amplitudeSpectrum = new double[real.length];
        for (int i = 0; i < real.length; i++) {
            amplitudeSpectrum[i] = Math.sqrt(real[i] * real[i] + imag[i] * imag[i]);
        }
        return amplitudeSpectrum;
    }

    @Override
    public double[] getPhaseSpectrum() {
        double[] phaseSpectrum = new double[real.length];
        for (int i = 0; i < real.length; i++) {
            phaseSpectrum[i] = Math.atan2(imag[i], real[i]);
        }
        return phaseSpectrum;
    }

    private void fft(double[] real, double[] imag) {
        int N = real.length;

        if (N <= 1) return;

        double[] evenReal = new double[N / 2];
        double[] evenImag = new double[N / 2];
        double[] oddReal = new double[N / 2];
        double[] oddImag = new double[N / 2];
        for (int k = 0; k < N / 2; k++) {
            evenReal[k] = real[k * 2];
            evenImag[k] = imag[k * 2];
            oddReal[k] = real[k * 2 + 1];
            oddImag[k] = imag[k * 2 + 1];
        }

        fft(evenReal, evenImag);
        fft(oddReal, oddImag);

        for (int k = 0; k < N / 2; k++) {
            double tReal = Math.cos(-2 * Math.PI * k / N) * oddReal[k] - Math.sin(-2 * Math.PI * k / N) * oddImag[k];
            double tImag = Math.sin(-2 * Math.PI * k / N) * oddReal[k] + Math.cos(-2 * Math.PI * k / N) * oddImag[k];

            real[k] = evenReal[k] + tReal;
            imag[k] = evenImag[k] + tImag;
            real[k + N / 2] = evenReal[k] - tReal;
            imag[k + N / 2] = evenImag[k] - tImag;
        }
    }

    @Override
    public void calculateSpectrum(double[] signal) {
        int N = signal.length;
        real = Arrays.copyOf(signal, N);
        imag = new double[N];
        fft(real, imag);
    }

    @Override
    public double[] restoreSignal() {
        fft(real, imag);

        for(int i = 0; i < real.length; i++) {
            real[i] /= real.length;
        }

        return real;
    }

    public void lowPassFilter(double frequency, int sampleRate) {
        for(int i = 0; i < real.length; i++) {
            double currFrequency = i * (double)sampleRate / real.length;
            if(currFrequency > frequency && currFrequency < real.length - frequency) {
                real[i] = 0;
                imag[i] = 0;
            }
        }
    }

    public void highPassFilter(double frequency, int sampleRate) {
        for(int i = 0; i < real.length; i++) {
            double currFrequency = i * (double)sampleRate / real.length;
            if(currFrequency < frequency || currFrequency > real.length - frequency) {
                real[i] = 0;
                imag[i] = 0;
            }
        }
    }

    public void bandPassFilter(double frequencyHi, double frequencyLo, int sampleRate) {
        lowPassFilter(frequencyLo, sampleRate);
        highPassFilter(frequencyHi, sampleRate);
    }
}
