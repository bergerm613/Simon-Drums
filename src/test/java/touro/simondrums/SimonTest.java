package touro.simondrums;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SimonTest {
    //this test fails
    @Test
    public void buildSequence() {
        //given
        SimonGame simon = new SimonGame();
        ArrayList<Drum> drums;
        int size;

        //when
        drums = simon.buildSequence();
        size = drums.size() - 1;

        //then
        switch (drums.get(size)) {
            case BASS:
                verify(drums).add(Drum.BASS);
                break;
            case SNARE:
                verify(drums).add(Drum.SNARE);
                break;
            case TOM:
                verify(drums).add(Drum.TOM);
                break;
            case CYMBAL:
                verify(drums).add(Drum.CYMBAL);
                break;
            default:
                fail();
        }
    }

    @Test
    public void checkResponse_Correct(){
        //given
        SimonGame simon = new SimonGame();
        ArrayList<Drum> drums;
        int size;
        boolean response;

        //when
        drums = simon.buildSequence();
        size = drums.size() - 1;
        switch (drums.get(size)) {
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
    public void checkResponse_Incorrect(){
        //given
        SimonGame simon = new SimonGame();
        ArrayList<Drum> drums;
        int size;
        boolean response;

        //when
        drums = simon.buildSequence();
        size = drums.size() - 1;
        switch (drums.get(size)) {
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
