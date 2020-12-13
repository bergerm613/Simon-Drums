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
        System.out.println("player drum clicked: " + drum);
        audioPlayer.drumAudioResponse(drum);

        //if that was the wrong response
        if (!game.checkResponse(drum)) {
            audioPlayer.playFailure();
            game.newGame();
        }
        else if (game.isFinishedRound()) {
            computerPlaySequence();
        }
    }

    private void computerPlaySequence() {
        System.out.println("Computer playing sequence");
        ArrayList<Drum> sequence = game.getDrumSequence();

        for (Drum currDrum : sequence) {
            audioPlayer.drumAudioResponse(currDrum);
        }
    }
}
