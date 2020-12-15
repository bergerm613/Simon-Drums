package touro.simondrums;

import java.util.ArrayList;

public class ListenerEvents {
    SimonGame game;
    AudioPlayer audioPlayer = new AudioPlayer();
    MyButton crash;
    MyButton snare;
    MyButton bass;
    MyButton hiHat;
    boolean player;

    public ListenerEvents(SimonGame game, MyButton crash, MyButton snare, MyButton bass, MyButton hiHat) {
        this.game = game;
        this.crash = crash;
        this.snare = snare;
        this.bass = bass;
        this.hiHat = hiHat;
    }

    public void newGame() {
        game.newGame();
        computerPlaySequence();
    }

    public void drumClicked(Drum drum) {

        if (player) {
            //if that was the wrong response
            if (!game.checkResponse(drum)) {
                audioPlayer.playFailure();
                //TODO: Should not automatically start new game. Should just disable everything till new game is started
                game.newGame();
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
            switch (currDrum) {
                case CRASH:
                    crash.doClick();
                    break;
                case SNARE:
                    snare.doClick();
                    break;
                case BASS:
                    bass.doClick();
                    break;
                case HIHAT:
                    hiHat.doClick();
                    break;
            }
        }
    }
}
