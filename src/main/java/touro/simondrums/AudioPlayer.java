package touro.simondrums;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

public class AudioPlayer {

    InputStream inputStream;
    AudioInputStream audioIn;

    private Clip setupAudioClip(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        CountDownLatch syncLatch = new CountDownLatch(1);
        inputStream = ListenerEvents.class.getClassLoader().getResourceAsStream(fileName);
        audioIn = AudioSystem.getAudioInputStream(inputStream);
        Clip clip = AudioSystem.getClip();
        clip.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP) {
                syncLatch.countDown();
            }
        });
        clip.open(audioIn);
        //TODO: Do we still need this if we have to redo this whole method every time anyway?
        clip.setMicrosecondPosition(0); //restart clip
        clip.start();
        try {
            syncLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inputStream.close();
        audioIn.close();

        return clip;
    }


    public void drumAudioResponse(Drum drum) {

        switch (drum) {
            case BASS:
                playClip("bass-sound.wav");
                break;
            case CRASH:
                playClip("crash-cymbal-sound.wav");
                break;
            case HIHAT:
                playClip("hi-hat-cymbal-sound.wav");
                break;
            case SNARE:
                playClip("snare-sound.wav");
                break;
        }
    }

    private void playClip(String s) {
        try {
            setupAudioClip(s);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playFailure() {
        playClip("wah-wah-wah.wav");
    }
}
