package by.bsuir.fourier;

import java.util.Arrays;

public class FastFourierTransformer implements IFourierTransformer {
    private double[] amplitudeSpectrum;
    private double[] phaseSpectrum;

    public FastFourierTransformer() {
        amplitudeSpectrum = new double[0];
        phaseSpectrum = new double[0];
    }

    @Override
    public double[] getAmplitudeSpectrum() {
        return amplitudeSpectrum;
    }

    @Override
    public double[] getPhaseSpectrum() {
        return phaseSpectrum;
    }

    private void fft(double[] real, double[] imag) {
        int N = real.length;

        // Базовый случай
        if (N <= 1) return;

        // Разделение на четные и нечетные элементы
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

        // Рекурсивные вызовы
        fft(evenReal, evenImag);
        fft(oddReal, oddImag);

        // Объединение
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
        double[] real = Arrays.copyOf(signal, N);
        double[] imag = new double[N];

        // Выполнение БПФ
        fft(real, imag);

        // Вычисление амплитудного и фазового спектров
        amplitudeSpectrum = new double[N];
        phaseSpectrum = new double[N];
        for (int i = 0; i < N; i++) {
            amplitudeSpectrum[i] = Math.sqrt(real[i] * real[i] + imag[i] * imag[i]);
            phaseSpectrum[i] = Math.atan2(imag[i], real[i]);
        }
    }

    @Override
    public double[] restoreSignal() {
        int N = amplitudeSpectrum.length;
        double[] restoredSignal = new double[N];

        for (int t = 0; t < N; t++) {
            double sum = 0.0;
            for (int k = 0; k < N; k++) {
                sum += amplitudeSpectrum[k] * Math.cos(2 * Math.PI * k * t / N + phaseSpectrum[k]);
            }
            restoredSignal[t] = sum / N;
        }

        return restoredSignal;
    }
}
