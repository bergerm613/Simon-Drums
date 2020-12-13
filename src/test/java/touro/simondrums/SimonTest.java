package touro.simondrums;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SimonTest {

    @Test
    public void newGame(){
        //given
        SimonGame simon = new SimonGame();
        simon.buildSequence();
        simon.buildSequence();
        simon.buildSequence();
        simon.buildSequence();

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
            simon.buildSequence();
        }
        int currSize = drumSequence.size();

        //then
        assertEquals(currSize, 3);
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
        simon.buildSequence();
        drums = simon.getDrumSequence();
        index = drums.size() - 1;
        switch (drums.get(index)) {
            case BASS:
                response = simon.checkResponse(Drum.BASS);
                break;
            case SNARE:
                response = simon.checkResponse(Drum.SNARE);
                break;
            case TOM:
                response = simon.checkResponse(Drum.TOM);
                break;
            case CYMBAL:
                response = simon.checkResponse(Drum.CYMBAL);
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
        simon.buildSequence();
        drums = simon.getDrumSequence();
        index = drums.size() - 1;
        switch (drums.get(index)) {
            case BASS:
                response = simon.checkResponse(Drum.SNARE);
                break;
            case SNARE:
                response = simon.checkResponse(Drum.BASS);
                break;
            case TOM:
                response = simon.checkResponse(Drum.CYMBAL);
                break;
            case CYMBAL:
                response = simon.checkResponse(Drum.TOM);
                break;
            default:
                response = true;
        }

        //then
        assertFalse(response);
    }
}
