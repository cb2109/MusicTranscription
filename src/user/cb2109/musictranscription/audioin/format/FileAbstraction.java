package user.cb2109.musictranscription.audioin.format;

import java.util.ArrayList;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public interface FileAbstraction extends Iterable<Frame> {
    public ArrayList<Frame> getFrames();
}
