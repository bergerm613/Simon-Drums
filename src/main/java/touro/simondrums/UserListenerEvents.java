package touro.simondrums;

import javax.swing.*;
import java.util.ArrayList;

public class UserListenerEvents {
    SimonGame game;
    AudioPlayer audioPlayer = new AudioPlayer();
    JLabel highScore;
    CompListenerEvents compListenerEvent;

    public UserListenerEvents(SimonGame game, JLabel highScore) {
        this.game = game;
        this.highScore = highScore;
        this.compListenerEvent = new CompListenerEvents(game, audioPlayer);
    }

    public void newGame() {
        game.newGame();
        compListenerEvent.playSequence();
    }

    public void drumClicked(Drum drum) {
        //if that was the wrong response
        if (!game.checkResponse(drum)) {
            audioPlayer.playFailure();
            highScore.setText("High Score: " + game.getHighScore());
        } else {
            audioPlayer.drumAudioResponse(drum);
        }
        if (game.isFinishedRound()) {
            compListenerEvent.playSequence();
        }
    }
}
