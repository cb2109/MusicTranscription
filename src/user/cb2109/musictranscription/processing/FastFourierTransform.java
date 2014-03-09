package user.cb2109.musictranscription.processing;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileAbstraction;
import user.cb2109.musictranscription.audioin.format.wave.WaveFrame;

import java.util.List;

/**
 * Author: Christopher Bates
 * Date: 07/01/14
 */
public class FastFourierTransform {

    public double[] computeFft(WaveFileAbstraction file) {

        final List<WaveFrame> frames = file.getFrames();
        double[] data = new double[frames.size()];
        int i = 0;
        for(WaveFrame frame : frames) {
            data[i] = frame.getLeft();
            i++;
        }

        new DoubleFFT_1D(data.length).realForward(data);

        for(i = 0; i < data.length; i++) {
            data[i] = Math.abs(data[i]);
        }


        return data;

    }


}
