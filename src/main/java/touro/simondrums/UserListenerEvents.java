package touro.simondrums;

import javax.swing.*;
import java.util.ArrayList;

public class UserListenerEvents {
    SimonGame game;
    AudioPlayer audioPlayer = new AudioPlayer();
    JLabel highScore;
    JLabel currentScore;
    CompListenerEvents compListenerEvent;
    boolean gameIsPlaying;

    public UserListenerEvents(SimonGame game, JLabel highScore, JLabel currentScore) {
        this.game = game;
        this.highScore = highScore;
        this.currentScore = currentScore;
        this.compListenerEvent = new CompListenerEvents(game, audioPlayer);
    }

    public void newGame() {
        game.newGame();
        gameIsPlaying = true;
        currentScore.setText("Current Score: " + game.getCurrentScore());        compListenerEvent.playSequence();
    }

    public void drumClicked(Drum drum) {
        if (gameIsPlaying) {
            checkUserResponse(drum);
            if (game.isFinishedRound()) {
                compListenerEvent.playSequence();
            }
        } else {
            currentScore.setText("Current Score: " + game.getCurrentScore());
            audioPlayer.drumAudioResponse(drum);
        }
    }

    private void checkUserResponse(Drum drum) {
        //if that was the wrong response
        if (!game.checkResponse(drum)) {
            audioPlayer.playFailure();
            gameIsPlaying = false;
            highScore.setText("High Score: " + game.getHighScore());
        } else {
            audioPlayer.drumAudioResponse(drum);
        }
    }
}
