package touro.simondrums;

import java.util.ArrayList;

public class ComputerListenerEvents {
    private final SimonGame game;
    private final AudioPlayer audioPlayer;

    public ComputerListenerEvents(SimonGame game, AudioPlayer audioPlayer) {
        this.game = game;
        this.audioPlayer = audioPlayer;
    }

    public void drumClicked(Drum drum) {
        audioPlayer.drumAudioResponse(drum);
    }

    public void playSequence() {
        ArrayList<Drum> sequence = game.getDrumSequence();

        for (Drum currDrum : sequence) {
            drumClicked(currDrum);
        }
    }
}
