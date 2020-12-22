package touro.simondrums;

import java.util.*;

public class SimonGame {
    private int highScore = 0;
    private int currentScore = 0;

    private final ArrayList<Drum> drumSequence = new ArrayList<>();
    private int index = 0;
    private boolean finishedRound = false;
    private final Random rand = new Random();
    private final List<Drum> drums = Arrays.asList(Drum.values());
    private final int amountDrums = drums.size();

    public SimonGame() {
        newGame();
    }

    public void newGame() {
        index = 0;
        finishedRound = false;
        drumSequence.clear();
        growSequence();
        currentScore = 0;
    }

    public boolean checkResponse(Drum userResponse) {
        int size = drumSequence.size();
        finishedRound = false;

        if (!userResponse.equals(drumSequence.get(index))) {
            return false;
        }
        else {
            if (index < size - 1) {
                index += 1;
            } else {
                currentScore++;
                finishedRound = true;
                growSequence();
                index = 0;

                highScore = Math.max(highScore, currentScore);
            }
            return true;
        }
    }

    public int getHighScore() {
        return highScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void growSequence() {
        Drum nextDrum = drums.get(rand.nextInt(amountDrums));
        drumSequence.add(nextDrum);
    }

    public ArrayList<Drum> getDrumSequence() {
        return drumSequence;
    }

    public boolean isFinishedRound() {
        return finishedRound;
    }
}
