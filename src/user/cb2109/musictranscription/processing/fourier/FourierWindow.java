package user.cb2109.musictranscription.processing.fourier;

/**
 * Author: Christopher Bates
 * Date: 09/03/14
 *
 * A window function for Short Time Fourier Transforms
 */
public interface FourierWindow {

    public double eval(int x);

}
