package tictactoe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BoardStateTest {

    @ParameterizedTest
    @CsvSource({
            "XOX=OX=O,Required exactly 9 Characters",
            "XX=OOO==T,Unexpected Character - T",
    })
    public void canThrowIllegalArgumentException(String boardState, String expectedMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new BoardState(boardState));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"XOX=OX=O=,O", "XX=OOO===,O",  "XOXOXOXO=,X", "XOXOXO=OX,X", "=========,"})
    public void canDetermineWinner(String line, Player expectedWinner) {
        BoardState boardState = new BoardState(line);
        assertEquals(expectedWinner, boardState.getWinner());
    }

    @ParameterizedTest
    @CsvSource({"X=X=OX=O=, O", "OOOXX====, X"})
    public void canDetermineNextPlayer(String line, Player expectedNextPlayer) {
        BoardState boardState = new BoardState(line);
        assertEquals(expectedNextPlayer, boardState.getNextPlayer());
    }

    @ParameterizedTest
    @CsvSource({"X=X=OX=O=,O", "=====X==O,", "X=XO=OO==,X"})
    public void canDetermineNextWinner(String line, Player expectedNextWinner) {
        BoardState result = new BoardState(line);
        assertEquals(expectedNextWinner, result.getNextWinner());
    }

    @ParameterizedTest
    @CsvSource({"X=X=OX=O=,false", "=====X==O,false", "XOX=OX=O=,true, XXOOOXXOX, true"})
    public void canDetermineIfCompletedGame(String line, boolean expected) {
        BoardState result = new BoardState(line);
        assertEquals(expected, result.isCompletedGame());
    }
}