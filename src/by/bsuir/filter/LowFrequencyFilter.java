package by.bsuir.filter;

public class LowFrequencyFilter implements IFrequencyFilter {
    private final double[] signal;

    public LowFrequencyFilter(double[] signal) {
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
                    sum += signal[j];
                    count++;
                }
            }
            filteredSignal[i] = sum / count;
        }

        return filteredSignal;
    }
}
