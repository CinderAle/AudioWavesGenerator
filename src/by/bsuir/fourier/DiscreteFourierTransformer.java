package by.bsuir.fourier;

public class DiscreteFourierTransformer implements IFourierTransformer {
    private double[] amplitudeSpectrum;
    private double[] phaseSpectrum;

    public DiscreteFourierTransformer() {
        amplitudeSpectrum = new double[0];
        phaseSpectrum = new double[0];
    }

    @Override
    public void calculateSpectrum(double[] signal) {
        int N = signal.length;
        amplitudeSpectrum = new double[N];
        phaseSpectrum = new double[N];

        for (int k = 0; k < N; k++) {
            double realPart = 0;
            double imaginaryPart = 0;

            for (int n = 0; n < N; n++) {
                double angle = -2 * Math.PI * k * n / N;
                realPart += signal[n] * Math.cos(angle);
                imaginaryPart += signal[n] * Math.sin(angle);
            }
            amplitudeSpectrum[k] = Math.sqrt(realPart * realPart + imaginaryPart * imaginaryPart);
            phaseSpectrum[k] = Math.atan2(imaginaryPart, realPart);
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

    @Override
    public double[] getAmplitudeSpectrum() {
        return amplitudeSpectrum;
    }

    @Override
    public double[] getPhaseSpectrum() {
        return phaseSpectrum;
    }
}
