package user.cb2109.musictranscription.audioin.format;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public interface AudioFileAbstracter {

    public FileAbstraction read(String fileName) throws IOException, UnsupportedAudioFileException;

}
