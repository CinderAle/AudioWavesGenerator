package by.bsuir.fourier;

public interface IFourierTransformer {
    double[] getAmplitudeSpectrum();
    double[] getPhaseSpectrum();
    void calculateSpectrum(double[] signal);
    double[] restoreSignal();
}
