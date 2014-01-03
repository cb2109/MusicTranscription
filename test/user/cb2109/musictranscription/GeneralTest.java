package user.cb2109.musictranscription;

import org.testng.annotations.Test;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileProcessor;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileAbstraction;
import user.cb2109.musictranscription.audioin.format.wave.WaveFrame;
import user.cb2109.musictranscription.tools.WaveFormatGraph;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
@Test
public class GeneralTest {

    private static final String SAMPLES = "samples/";

    public void test() throws UnsupportedAudioFileException, IOException, InterruptedException {

        WaveFileProcessor reader = new WaveFileProcessor();
        WaveFileAbstraction file = reader.read(SAMPLES + "test.wav");

        for(WaveFrame frame : file.getFrames()) {
            System.out.println(frame.getLeft() + ", " + frame.getRight());
        }

        WaveFormatGraph.draw(file);

    }

}
