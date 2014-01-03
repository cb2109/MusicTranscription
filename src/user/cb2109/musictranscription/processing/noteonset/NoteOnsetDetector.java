package user.cb2109.musictranscription.processing.noteonset;

import javax.sound.sampled.AudioInputStream;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public class NoteOnsetDetector implements NoteOnsetDetectorI {

    private AudioInputStream stream;

    public NoteOnsetDetector(AudioInputStream stream) {

        this.stream = stream;

    }

    public void detectNotes() {



    }


}
