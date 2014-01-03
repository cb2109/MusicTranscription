package user.cb2109.musictranscription.audioin.format.wave;

import user.cb2109.musictranscription.audioin.format.FileAbstraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public class WaveFileAbstraction implements FileAbstraction {

    private ArrayList<WaveFrame> frames;
    private WaveHeader header;

    public WaveFileAbstraction(WaveHeader header, List<WaveFrame> frames) {
        this.header = header;
        this.frames = new ArrayList<WaveFrame>(frames);
    }

    public ArrayList<WaveFrame> getFrames() {
        return frames;
    }

    public WaveHeader getHeader() {
        return header;
    }
}
