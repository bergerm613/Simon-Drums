package touro.simondrums;

import javax.swing.*;
import java.util.ArrayList;

public class ListenerEvents {
    SimonGame game;
    AudioPlayer audioPlayer = new AudioPlayer();
    JLabel highScore;
    JLabel currentScore;
    boolean player;
    boolean gameIsPlaying;

    public ListenerEvents(SimonGame game, JLabel highScore, JLabel currentScore) {
        this.game = game;
        this.highScore = highScore;
        this.currentScore = currentScore;
    }

    public void newGame() {
        game.newGame();
        gameIsPlaying = true;
        computerPlaySequence();
    }

    public void drumClicked(Drum drum) {

        if (player && gameIsPlaying) {
            //if that was the wrong response
            if (!game.checkResponse(drum)) {
                audioPlayer.playFailure();
                gameIsPlaying = false;
                highScore.setText("High Score: " + game.getHighScore());
                currentScore.setText("");
            } else audioPlayer.drumAudioResponse(drum);
            if (game.isFinishedRound()) {
                computerPlaySequence();
                currentScore.setText("Current Score: " + game.getCurrentScore());
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
            drumClicked(currDrum);
        }
    }
}
