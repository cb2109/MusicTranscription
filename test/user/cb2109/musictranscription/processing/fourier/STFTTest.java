package user.cb2109.musictranscription.processing.fourier;

import org.testng.annotations.Test;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileAbstraction;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileProcessor;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Author: Christopher Bates
 * Date: 09/03/14
 */
@Test
public class STFTTest {

    private static final String SAMPLES = "samples/";

    public void testSimple() throws UnsupportedAudioFileException, IOException {
        ShortTimeFourierTransform ft = new ShortTimeFourierTransform();

        WaveFileProcessor reader = new WaveFileProcessor();
        WaveFileAbstraction file = reader.read(SAMPLES + "test.wav");

        double[] data = ft.compute(file, 200);

        /*for (double aData : data) {
            System.out.println(aData);
        } */
    }

}
