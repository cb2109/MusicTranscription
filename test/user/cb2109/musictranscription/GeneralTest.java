package user.cb2109.musictranscription;

import junit.framework.Assert;
import org.testng.annotations.Test;
import user.cb2109.musictranscription.audioin.format.SoundFileReconstructionException;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileAbstraction;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileProcessor;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileReconstructor;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
@Test
public class GeneralTest {

    private static final String SAMPLES = "samples/";
    private static final String OUTPUT = "output/";

    public void test() throws UnsupportedAudioFileException, IOException, InterruptedException, SoundFileReconstructionException {

        WaveFileProcessor reader = new WaveFileProcessor();
        WaveFileAbstraction file = reader.read(SAMPLES + "test.wav");

        /*
        for(WaveFrame frame : file.getFrames()) {
            System.out.println(frame.getLeft() + ", " + frame.getRight());
        } */

        WaveFileReconstructor r = new WaveFileReconstructor(OUTPUT + "test.wav");
        r.unabstract(file);

        File start = new File(SAMPLES + "test.wav");
        File product = new File(OUTPUT + "test.wav");
        compare(start, product);
    }

    private void compare(File start, File product) throws IOException {
        int i = -1;
        FileReader s = new FileReader(start);
        FileReader p = new FileReader(product);
        int actual;
        int produced;
        do {
            i++;
            actual = s.read();
            produced = p.read();
        } while(actual == produced && s.ready() && p.ready());

        if(s.ready() != p.ready()) {
            Assert.fail("S.ready = " + s.ready() + ". P.ready = " + p.ready());
        } else if(actual != produced) {
            Assert.fail("actual: " + actual + ". produced: " + produced + ". position: " + i);
        }
    }

}
