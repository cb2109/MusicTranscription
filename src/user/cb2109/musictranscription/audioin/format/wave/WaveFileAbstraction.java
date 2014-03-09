package user.cb2109.musictranscription.audioin.format.wave;

import user.cb2109.musictranscription.audioin.format.FileAbstraction;
import user.cb2109.musictranscription.audioin.format.Frame;

import java.util.ArrayList;
import java.util.Iterator;
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

    public WaveHeader getHeader() {
        return header;
    }

    public ArrayList<WaveFrame> getWaveFrames() {
        return this.frames;
    }

    @Override
    public ArrayList<Frame> getFrames() {
        return new ArrayList<Frame>(frames);
    }

    @Override
    public Iterator<Frame> iterator() {
        // uses a dummy iterator to avoid casting issues
        return new Iterator<Frame>() {
            private Iterator<WaveFrame> framesIt = frames.iterator();
            @Override
            public boolean hasNext() {
                return framesIt.hasNext();
            }

            @Override
            public Frame next() {
                return framesIt.next();
            }

            @Override
            public void remove() {
                framesIt.remove();
            }
        };
    }
}
