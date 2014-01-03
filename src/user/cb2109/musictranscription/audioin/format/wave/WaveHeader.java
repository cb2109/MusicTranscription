package user.cb2109.musictranscription.audioin.format.wave;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public class WaveHeader {
    private String chunkId;
    private int chunkSize;
    private String chunkFormat;

    private int compressionLevel;
    private int noChannels;
    private int sampleRate;
    private int blockAlign;
    private int bitsPerSample;
    private int dataStart;


    public void setChunkId(String id) {
        this.chunkId = id;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public void setChunkFormat(String format) {
        this.chunkFormat = format;
    }

    public void setCompressionLevel(int level) {
        this.compressionLevel = level;
    }

    public void setNumChannels(int channels) {
        this.noChannels = channels;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public void setBlockAlign(int blockAlign) {
        this.blockAlign = blockAlign;
    }

    public void setBitsPerSample(int bitsPerSample) {
        this.bitsPerSample = bitsPerSample;
    }

    public void setDataStart(int dataStart) {
        this.dataStart = dataStart;
    }

    public String getChunkId() {
        return chunkId;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public String getChunkFormat() {
        return chunkFormat;
    }

    public int getCompressionLevel() {
        return compressionLevel;
    }

    public int getNoChannels() {
        return noChannels;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public int getBlockAlign() {
        return blockAlign;
    }

    public int getBitsPerSample() {
        return bitsPerSample;
    }

    public int getDataStart() {
        return dataStart;
    }
}
