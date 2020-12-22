package touro.simondrums;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class SimonTest {

    @Test
    public void newGame(){
        //given
        SimonGame simon = new SimonGame();

        //when
        simon.newGame();

        //then
        assertEquals(simon.getDrumSequence().size(), 1);
        assertFalse(simon.isFinishedRound());
    }
    @Test
    public void buildSequence() {
        //given
        SimonGame simon = new SimonGame();
        ArrayList<Drum> drumSequence = simon.getDrumSequence();

        //when
        for (int i = 0; i < 3; i++) {
            simon.growSequence();
        }
        int currSize = drumSequence.size();

        //then
        assertEquals(currSize, 4);
        for (int i = 0; i < currSize; i++) {
            assertThat(drumSequence.get(i), instanceOf(Drum.class));
        }
    }

    @Test
    public void checkResponse_Correct() {
        //given
        SimonGame simon = new SimonGame();
        ArrayList<Drum> drums;
        int index;
        boolean response;

        //when
        drums = simon.getDrumSequence();
        index = drums.size() - 1;

        switch (drums.get(index)) {
            case BASS:
                response = simon.checkResponse(Drum.BASS);
                break;
            case SNARE:
                response = simon.checkResponse(Drum.SNARE);
                break;
            case CRASH:
                response = simon.checkResponse(Drum.CRASH);
                break;
            case HIHAT:
                response = simon.checkResponse(Drum.HIHAT);
                break;
            default:
                response = false;
        }

        //then
        assertTrue(response);
    }

    @Test
    public void checkResponse_Incorrect() {
        //given
        SimonGame simon = new SimonGame();
        ArrayList<Drum> drums;
        int index;
        boolean response;

        //when
        drums = simon.getDrumSequence();
        index = drums.size() - 1;
        switch (drums.get(index)) {
            case BASS:
                response = simon.checkResponse(Drum.SNARE);
                break;
            case SNARE:
                response = simon.checkResponse(Drum.BASS);
                break;
            case CRASH:
                response = simon.checkResponse(Drum.HIHAT);
                break;
            case HIHAT:
                response = simon.checkResponse(Drum.CRASH);
                break;
            default:
                response = true;
        }

        //then
        assertFalse(response);
    }
}