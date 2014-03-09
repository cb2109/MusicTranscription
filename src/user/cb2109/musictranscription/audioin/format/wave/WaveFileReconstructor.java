package user.cb2109.musictranscription.audioin.format.wave;

import user.cb2109.musictranscription.audioin.format.SoundFileReconstructionException;
import user.cb2109.musictranscription.audioin.format.UnabstractorI;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Christopher Bates
 * Date: 03/01/14
 */
public class WaveFileReconstructor implements UnabstractorI<WaveFileAbstraction> {

    private final String path;

    public WaveFileReconstructor(final String outputPath) {
        this.path = outputPath;
    }

    @Override
    public File unabstract(WaveFileAbstraction file) throws SoundFileReconstructionException, IOException {


        List<Byte> bytesToWrite = new ArrayList<Byte>();

        unabstractHeader(file.getHeader(), bytesToWrite);
        unabstractWaves(file, bytesToWrite);


        File f = new File(this.path);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
        for(Byte b : bytesToWrite) {
            out.write(b);
        }
        out.flush();


        return f;
    }

    private void unabstractHeader(WaveHeader header, final List<Byte> bytesToWrite) throws SoundFileReconstructionException {

        // RIFF chunk descriptor
        stringToBytes(header.getChunkId(), bytesToWrite);
        writeLittleEndian(header.getChunkSize(), 4, bytesToWrite);
        stringToBytes(header.getChunkFormat(), bytesToWrite);

        //fmt subchunk
        stringToBytes(header.getSubchunkId(), bytesToWrite);
        writeLittleEndian(header.getSubchunkSize(), 4, bytesToWrite);
        writeLittleEndian(header.getCompressionLevel(), 2, bytesToWrite);
        writeLittleEndian(header.getNoChannels(), 2, bytesToWrite);
        writeLittleEndian(header.getSampleRate(), 4, bytesToWrite);
        writeLittleEndian(header.getByteRate(), 4, bytesToWrite);
        writeLittleEndian(header.getBlockAlign(), 2, bytesToWrite);
        writeLittleEndian(header.getBitsPerSample(), 2, bytesToWrite);

        stringToBytes(header.getDataHeader(), bytesToWrite);
        writeLittleEndian(header.getDataSize(), 4, bytesToWrite);
    }

    private void unabstractWaves(WaveFileAbstraction file, List<Byte> bytesToWrite) throws SoundFileReconstructionException {
        WaveHeader header = file.getHeader();
        List<WaveFrame> frames = file.getWaveFrames();
        int noChannels = header.getNoChannels();
        int bps = (header.getBitsPerSample() + 7) / 8;  // ceiling division
        if(noChannels == 1) {
            for(WaveFrame frame : frames) {
                writeLittleEndian(frame.getLeft(), bps, bytesToWrite);
            }
        } else if(noChannels == 2) {
            for(WaveFrame frame : frames) {
                writeLittleEndian(frame.getLeft(), bps, bytesToWrite);
                writeLittleEndian(frame.getRight(), bps, bytesToWrite);
            }
        }
    }

    private void stringToBytes(String s, final List<Byte> output) {

        for(Character c : s.toCharArray()) {
            output.add((byte) c.charValue());
        }
    }

    private void writeLittleEndian(int number, int byteLength, final List<Byte> output) throws SoundFileReconstructionException {
        ByteBuffer buf = ByteBuffer.allocate(byteLength).order(ByteOrder.LITTLE_ENDIAN);
        if(byteLength == 2) {
            buf.putShort((short) number);
        } else if(byteLength == 4) {
            buf.putInt(number);
        } else if(byteLength == 1) {
            buf.put((byte) number);
        } else {
            throw new SoundFileReconstructionException("Tried to output a length of bytes not equal to a short or int: " +
                    "length = " + byteLength);
        }
        byte[] bytes = buf.array();

        for(Byte b : bytes) {
            output.add(b);
        }


    }
}
