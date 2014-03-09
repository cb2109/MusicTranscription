package user.cb2109.musictranscription.fastfouriertest;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import org.testng.annotations.Test;

/**
 * Author: Christopher Bates
 * Date: 07/01/14
 */
public class FFTTest {

    @Test
    public void fftTest() {

        DoubleFFT_1D fft = new DoubleFFT_1D(10);
        double[] data = {1d, 2d, 3d, 4d, 5d, 6, 7, 8, 9, 10};
        fft.realForward(data);

        for (double aData : data) {
            System.out.println(aData);
        }

    }
}
