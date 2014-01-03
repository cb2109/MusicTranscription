package user.cb2109.musictranscription.audioin.format.wave;

import user.cb2109.musictranscription.audioin.format.AudioFileAbstracter;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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

        int start = header.getDataStart() + 8; // there are two meta data pieces in the way worth 8 bytes
        int numChannels = header.getNoChannels();
        int sampleSize = header.getBitsPerSample();
        int bytesPerSample = (sampleSize + 7) / 8;  // ceiling division

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

        // Ignoring the header for the format chunk
        // process the format subchunk
        h.setCompressionLevel(readLittleEndian(d[20], d[21]));
        h.setNumChannels(readLittleEndian(d[22], d[23]));
        h.setSampleRate(readLittleEndian(d[24], d[25], d[26], d[27]));
        h.setBlockAlign(readLittleEndian(d[28], d[29]));
        h.setBitsPerSample(readLittleEndian(d[30], d[31]));
        h.setDataStart(32);


        return h;

    }

    private byte[] extractRawData(File file) throws IOException, UnsupportedAudioFileException {
        AudioInputStream stream = AudioSystem.getAudioInputStream(file);

        ByteArrayOutputStream dataStream = new ByteArrayOutputStream();

        AudioSystem.write(stream, AudioFileFormat.Type.WAVE, dataStream);

        return dataStream.toByteArray();
    }

    private int readLittleEndian(byte... bytes) {
        int shiftCounter = 0;
        int result = 0;
        for(int i = bytes.length - 1; i >= 0; i--) {
            result |= bytes[i] << shiftCounter;
        }
        return result;
    }

    private String readStringFromBytes(byte... bytes) {

        return new String(bytes);

    }



}
