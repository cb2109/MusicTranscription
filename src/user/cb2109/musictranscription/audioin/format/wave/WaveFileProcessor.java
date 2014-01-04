package user.cb2109.musictranscription.audioin.format.wave;

import user.cb2109.musictranscription.audioin.format.AudioFileAbstracter;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public class WaveFileProcessor implements AudioFileAbstracter {

    public WaveFileAbstraction read(String fileName) throws IOException, UnsupportedAudioFileException {

        byte[] rawData = extractRawData(new File(fileName));

        WaveHeader header = extractHeader(rawData);

        return new WaveFileAbstraction(header, processSamples(rawData, header));

    }

    private List<WaveFrame> processSamples(byte[] d, WaveHeader header) throws UnsupportedAudioFileException {

        ArrayList<WaveFrame> frames = new ArrayList<WaveFrame>();

        int start = header.getDataStart();
        int numChannels = header.getNoChannels();
        int sampleSize = (header.getBitsPerSample() + 7) / 8;    // ceiling division

        int i = start;
        if(numChannels == 2) {
            while(i < d.length) {
                byte[] sampleLeft = new byte[sampleSize];
                byte[] sampleRight = new byte[sampleSize];
                System.arraycopy(d, i, sampleLeft, 0, sampleSize);
                i+= sampleSize;
                System.arraycopy(d, i, sampleRight, 0, sampleSize);
                i+= sampleSize;
                WaveFrame frame = new WaveFrame(
                        readLittleEndian(sampleLeft),
                        readLittleEndian(sampleRight),
                        sampleSize);

                frames.add(frame);

            }
        } else if(numChannels == 1) {
            while(i < d.length) {
                byte[] sample = new byte[sampleSize];
                System.arraycopy(d, i, sample, 0, sampleSize);

                i+= sampleSize;
                WaveFrame frame = new WaveFrame(
                        readLittleEndian(sample),
                        sampleSize);

                frames.add(frame);
            }
        } else {
            throw new UnsupportedAudioFileException("WaveFileProcessor: The WAVE file had 0 or >2 channels");
        }


        return frames;

    }

    private WaveHeader extractHeader(byte[] d) {


        WaveHeader h = new WaveHeader();

        // process chunk descriptor
        h.setChunkId(readStringFromBytes(d[0], d[1], d[2], d[3]));
        h.setChunkSize(readLittleEndian(d[4], d[5], d[6], d[7]));
        h.setChunkFormat(readStringFromBytes(d[8], d[9], d[10], d[11]));

        // process the format subchunk
        h.setSubchunkID(readStringFromBytes(d[12], d[13], d[14], d[15]));
        h.setSubchunkSize(readLittleEndian(d[16], d[17], d[18], d[19]));
        h.setCompressionLevel(readLittleEndian(d[20], d[21]));
        h.setNumChannels(readLittleEndian(d[22], d[23]));
        h.setSampleRate(readLittleEndian(d[24], d[25], d[26], d[27]));
        h.setByteRate(readLittleEndian(d[28], d[29], d[30], d[31]));
        h.setBlockAlign(readLittleEndian(d[32], d[33]));
        h.setBitsPerSample(readLittleEndian(d[34], d[35]));

        h.setDataHeader(readStringFromBytes(d[36], d[37], d[38], d[39]));
        h.setDataSize(readLittleEndian(d[40], d[41], d[42], d[43]));

        h.setDataStart(44);


        return h;

    }

    private byte[] extractRawData(File file) throws IOException, UnsupportedAudioFileException {
        AudioInputStream stream = AudioSystem.getAudioInputStream(file);

        ByteArrayOutputStream dataStream = new ByteArrayOutputStream();

        AudioSystem.write(stream, AudioFileFormat.Type.WAVE, dataStream);

        return dataStream.toByteArray();
    }

    private int readLittleEndian(byte... bytes) {

        ByteBuffer buf = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);

        return (bytes.length < 4 ? buf.getShort() : buf.getInt());
    }

    private String readStringFromBytes(byte... bytes) {

        return new String(bytes);

    }



}
