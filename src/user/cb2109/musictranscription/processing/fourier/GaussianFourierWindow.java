package user.cb2109.musictranscription.processing.fourier;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

/**
 * Author: Christopher Bates
 * Date: 09/03/14
 *
 * Gaussian window centred around 0
 */
public class GaussianFourierWindow implements FourierWindow {

    private double sd2;

    public GaussianFourierWindow(double standardDeviation) {
        this.sd2 = pow(standardDeviation, 2) * 2;
    }

    /**
     * Calculate the value of the window at x. Not the function is centred
     * around 0.
     *
     * Uses Gaussian equation with 0 mean, 1 height and no translation:
     * eval(x) = 1 * exp(-((x-0)^2)/(2*sd^2) + 0
     *         = exp(-(x^2 / 2sd^2))
     *
     *
     * @param x point to evaluate the window function at
     * @return the value of the gaussian window
     */
    public double eval(int x) {

        double x2 = pow(x, 2);
        double ePow = -1 * x2/sd2;
        return exp(ePow);

    }



}
