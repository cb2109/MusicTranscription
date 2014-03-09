package user.cb2109.musictranscription.processing.fourier;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

import java.util.List;

/**
 * Author: Christopher Bates
 * Date: 07/01/14
 */
public class FastFourierTransform {

    public double[] computeFft(List<Double> values) {

        double[] data = new double[values.size()];
        int i = 0;
        for(Double value : values) {
            data[i] = value;
            i++;
        }

        new DoubleFFT_1D(data.length).realForward(data);

        for(i = 0; i < data.length; i++) {
            data[i] = Math.abs(data[i]);
        }


        return data;

    }


}
