package touro.simondrums;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListenerEvents {
    SimonGame game;
    AudioPlayer audioPlayer = new AudioPlayer();
    boolean player;
    boolean gameIsPlaying;

    JPanel buttonPanel;

    public ListenerEvents(SimonGame game, JPanel buttonPanel) {
        this.game = game;
        this.buttonPanel = buttonPanel;
    }

    public void newGame() {
        game.newGame();
        gameIsPlaying = true;
        computerPlaySequence();
    }

    public void drumClicked(Drum drum) {
        changeColor(drum);

        if (player && gameIsPlaying) {
            //if that was the wrong response
            if (!game.checkResponse(drum)) {
                audioPlayer.playFailure();
                buttonPanel.setBackground(Color.WHITE);
                gameIsPlaying = false;
            } else audioPlayer.drumAudioResponse(drum);
            if (game.isFinishedRound()) {
                computerPlaySequence();
            }
        }
        else {
            audioPlayer.drumAudioResponse(drum);
            player = true;
        }
    }

    private void computerPlaySequence() {
        ArrayList<Drum> sequence = game.getDrumSequence();

        for (Drum currDrum : sequence) {
            player = false;
            changeColor(currDrum);
            drumClicked(currDrum);
        }
    }

    private void changeColor(Drum drum) {
        switch (drum) {
            case CRASH:
                buttonPanel.setBackground(Color.RED);
                break;
            case SNARE:
                buttonPanel.setBackground(Color.BLUE);
                break;
            case BASS:
                buttonPanel.setBackground(Color.GREEN);
                break;
            case HIHAT:
                buttonPanel.setBackground(Color.YELLOW);
                break;
        }
    }
}
