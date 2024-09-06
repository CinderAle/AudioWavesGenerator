package by.bsuir.waveconverter;

public class WaveByteConverter implements IWaveByteConverter {
    private static final int BITS_IN_BYTE = 8;

    public static byte[] convert(double[] wave, int bytesPerSample) {
        byte[] output = new byte[bytesPerSample * wave.length];
        int maxValue = (int)Math.pow(2, bytesPerSample * BITS_IN_BYTE - 1) - 1;

        for (int i = 0; i < wave.length; i++) {
            int value = (int)(wave[i] * maxValue);

            for(int j = 0; j < bytesPerSample; j++) {
                output[i * bytesPerSample + j] = (byte)((value >> (j * BITS_IN_BYTE)) & 0xFF);
            }
        }

        return output;
    }
}
