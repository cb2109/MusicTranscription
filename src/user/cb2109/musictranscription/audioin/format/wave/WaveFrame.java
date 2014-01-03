package user.cb2109.musictranscription.audioin.format.wave;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public class WaveFrame {
    private int sampleSize;
    private int left;
    private int right;

    public WaveFrame(int left, int right, int sampleSize) {
        this.left = left;
        this.right = right;
        this.sampleSize = sampleSize;
    }

    public WaveFrame(int mono, int sampleSize) {
        this.left = mono;
        this.right = mono;
        this.sampleSize = sampleSize;
    }


    public int getSampleSize() {
        return sampleSize;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}
