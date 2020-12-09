package touro.simondrums;

import java.util.*;

public class SimonGame {
    private ArrayList<Drum> drumSequence = new ArrayList<>();

    public SimonGame(){
    }

    public boolean checkResponse(Drum userResponse){
        int index = drumSequence.size()-1;
        if(userResponse.equals(drumSequence.get(index)))
        {
            return true;
        }
        else return false;
    }
    public ArrayList<Drum> buildSequence(){
        Random rand = new Random();
        List<Drum> drums =  Arrays.asList(Drum.values());
        int amountDrums = drums.size();

        Drum nextDrum = drums.get(rand.nextInt(amountDrums));
        drumSequence.add(nextDrum);

        return drumSequence;
    }
}
