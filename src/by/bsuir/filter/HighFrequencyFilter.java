package by.bsuir.filter;

public class HighFrequencyFilter implements IFrequencyFilter {
    private final double[] signal;

    public HighFrequencyFilter(double[] signal) {
        this.signal = signal;
    }

    @Override
    public double[] filter(int windowSize) {
        double[] filteredSignal = new double[signal.length];

        for (int i = 0; i < signal.length; i++) {
            double sum = 0;
            int count = 0;
            for (int j = i - windowSize; j <= i + windowSize; j++) {
                if (j >= 0 && j < signal.length) {
                    sum += (j == i) ? signal[j] * (windowSize + 1) : -signal[j];
                    count++;
                }
            }
            filteredSignal[i] = sum / count;
        }

        return filteredSignal;
    }
}
