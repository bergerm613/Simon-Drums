package touro.simondrums;

import java.util.ArrayList;

public class ListenerEvents {
    SimonGame game;
    AudioPlayer audioPlayer = new AudioPlayer();

    public ListenerEvents(SimonGame game) {
        this.game = game;
    }

    public void newGame() {
        game.newGame();
        computerPlaySequence();
    }

    public void drumClicked(Drum drum) {

        //if that was the wrong response
        if (!game.checkResponse(drum)) {
            audioPlayer.playFailure();
            //TODO: Should not automatically start new game. Should just disable everything till new game is started
            game.newGame();
        }
        else audioPlayer.drumAudioResponse(drum);
        if (game.isFinishedRound()) {
            computerPlaySequence();
        }
    }

    private void computerPlaySequence() {
        ArrayList<Drum> sequence = game.getDrumSequence();

        for (Drum currDrum : sequence) {
            audioPlayer.drumAudioResponse(currDrum);
        }
    }
}
