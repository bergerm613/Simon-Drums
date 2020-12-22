package touro.simondrums;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

public class AudioPlayer {
    InputStream inputStream;
    AudioInputStream audioIn;

    private void setupAudioClip(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        CountDownLatch syncLatch = new CountDownLatch(1);
        inputStream = AudioPlayer.class.getClassLoader().getResourceAsStream(fileName);
        audioIn = AudioSystem.getAudioInputStream(inputStream);
        Clip clip = AudioSystem.getClip();
        clip.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP) {
                syncLatch.countDown();
            }
        });
        clip.open(audioIn);
        clip.start();
        try {
            syncLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inputStream.close();
        audioIn.close();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playFailure() {
        playClip("wah-wah-wah.wav");
    }
}
