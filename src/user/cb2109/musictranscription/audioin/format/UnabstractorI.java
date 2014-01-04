package user.cb2109.musictranscription.audioin.format;

import java.io.File;
import java.io.IOException;

/**
 * Author: Christopher Bates
 * Date: 03/01/14
 */
public interface UnabstractorI<FT extends FileAbstraction> {

    public File unabstract(FT file) throws SoundFileReconstructionException, IOException;

}
