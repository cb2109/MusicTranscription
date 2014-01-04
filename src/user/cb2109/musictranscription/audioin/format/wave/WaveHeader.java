package user.cb2109.musictranscription.audioin.format.wave;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public class WaveHeader {
    private String chunkId;
    private int chunkSize;
    private String chunkFormat;

    private String subchunkId;
    private int subchunkSize;
    private int compressionLevel;
    private int noChannels;
    private int sampleRate;
    private int byteRate;
    private int blockAlign;
    private int bitsPerSample;

    private int dataStart;
    private String dataHeader;
    private int dataSize;


    public void setChunkId(String id) {
        this.chunkId = id;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public void setChunkFormat(String format) {
        this.chunkFormat = format;
    }

    public void setSubchunkID(String id) {
        this.subchunkId = id;
    }
    public void setSubchunkSize(int size) {
        this.subchunkSize = size;
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

    public void setByteRate(int byteRate) {
        this.byteRate = byteRate;
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

    public void setDataHeader(String dataHeader) {
        this.dataHeader = dataHeader;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
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

    public int getByteRate() {
        return byteRate;
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

    public String getSubchunkId() {
        return subchunkId;
    }

    public int getSubchunkSize() {
        return subchunkSize;
    }

    public String getDataHeader() {
        return dataHeader;
    }

    public int getDataSize() {
        return dataSize;
    }
}
