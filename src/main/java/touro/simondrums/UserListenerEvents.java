package touro.simondrums;

import javax.swing.*;

public class UserListenerEvents {
    private final SimonGame game;
    private final AudioPlayer audioPlayer = new AudioPlayer();
    private final JLabel highScore;
    private final JLabel currentScore;
    private final ComputerListenerEvents compListenerEvent;
    private boolean gameIsPlaying;

    public UserListenerEvents(SimonGame game, JLabel highScore, JLabel currentScore) {
        this.game = game;
        this.highScore = highScore;
        this.currentScore = currentScore;
        this.compListenerEvent = new ComputerListenerEvents(game, audioPlayer);
    }

    public void newGame() {
        game.newGame();
        gameIsPlaying = true;
        currentScore.setText("Current Score: " + game.getCurrentScore());
        compListenerEvent.playSequence();
    }

    public void drumClicked(Drum drum) {
        if (gameIsPlaying) {
            checkUserResponse(drum);
            if (game.isFinishedRound()) {
                compListenerEvent.playSequence();
            }
        } else {
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
            currentScore.setText("Current Score: " + game.getCurrentScore());
            audioPlayer.drumAudioResponse(drum);
        }
    }
}
