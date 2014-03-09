package user.cb2109.musictranscription.processing.fourier;

import org.apache.commons.math.complex.Complex;
import user.cb2109.musictranscription.audioin.format.FileAbstraction;
import user.cb2109.musictranscription.audioin.format.Frame;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Christopher Bates
 * Date: 09/03/14
 */
public class ShortTimeFourierTransform {

    public static final double WINDOW_SD = 0.2;

    private FourierWindow w;

    public ShortTimeFourierTransform() {
        this.w = new GaussianFourierWindow(WINDOW_SD);
    }

    public ShortTimeFourierTransform(FourierWindow windowFunction) {
        this.w = windowFunction;
    }

    public List<Complex> compute(FileAbstraction file) {

        int freqAxis = 1;

        ArrayList<Frame> frames = file.getFrames();
        ArrayList<Complex> output = new ArrayList<Complex>(frames.size());
        for(int middleOfWindow = 0; middleOfWindow < frames.size(); middleOfWindow++) {
            int t = 0;
            Double realSum = 0.0;
            Double imaginarySum = 0.0;
            for(Frame f : frames) {
                double window = w.eval(t - middleOfWindow);
                if(window > 0) {
                    double val = window * f.getSample();
                    realSum += val * Math.cos(t * freqAxis);
                    imaginarySum += val * Math.sin(t * freqAxis);
                }
                t++;
            }
            output.add(new Complex(realSum, imaginarySum));
        }




        return output;
    }


    private class STFTResult {
    }
}
