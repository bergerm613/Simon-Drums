package touro.simondrums;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class AudioPlayer implements LineListener {
    Clip bassClip;
    Clip crashClip;
    Clip hiHatClip;
    Clip snareClip;
    Clip wahClip;

    InputStream inputStream;
    AudioInputStream audioIn;

    private boolean playCompleted = true;

    public AudioPlayer() {
        try {
            bassClip = setupAudioClip("bass-sound.wav");
            crashClip = setupAudioClip("crash-cymbal-sound.wav");
            hiHatClip = setupAudioClip("hi-hat-cymbal-sound.wav");
            snareClip = setupAudioClip("snare-sound.wav");
            wahClip = setupAudioClip("wah-wah-wah.wav");

            inputStream.close();
            audioIn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Clip setupAudioClip(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        inputStream = ListenerEvents.class.getClassLoader().getResourceAsStream(fileName);
        audioIn = AudioSystem.getAudioInputStream(inputStream);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.addLineListener(this);

        return clip;
    }


    public void drumAudioResponse(Drum drum) {
        System.out.println("drum audio playing for: " + drum);
        while(!playCompleted) {
                    // wait for the playback completes
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

        switch (drum) {
            case BASS:
                System.out.println("bass start");
                bassClip.setMicrosecondPosition(0); //restart clip
                bassClip.start();
                break;
            case CRASH:
                System.out.println("crash start");
                crashClip.setMicrosecondPosition(0);
                crashClip.start();
                break;
            case HIHAT:
                System.out.println("hi hat start");
                hiHatClip.setMicrosecondPosition(0);
                hiHatClip.start();
                break;
            case SNARE:
                System.out.println("snare start");
                snareClip.setMicrosecondPosition(0);
                snareClip.start();
                break;
        }
    }

    public void playFailure() {
        wahClip.setMicrosecondPosition(0);
        wahClip.start();
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            playCompleted = false;
            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
    }
}
