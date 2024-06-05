package ticktactoe

import tictactoe.BoardState
import tictactoe.Player
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class BoardStateTest {

    @ParameterizedTest
    @CsvSource(
        "XOX=OX=O,Required exactly 9 Characters",
        "XX=OOO==T,Unexpected Character - T",
    )
    fun `can throw illegal argument exception` (boardState: String, expectedMessage: String){
        val exception = assertThrows(IllegalArgumentException::class.java) { BoardState(boardState) }
        assertEquals(expectedMessage, exception.message)
    }

    @ParameterizedTest
    @CsvSource("XOX=OX=O=,O", "XX=OOO===,O",  "XOXOXOXO=,X", "XOXOXO=OX,X", "=========,")
    fun `can determine winner`(line: String, expectedWinner: Player?){
        val boardState = BoardState(line)
        assertEquals(expectedWinner, boardState.getWinner())
    }

    @ParameterizedTest
    @CsvSource("X=X=OX=O=, O", "OOOXX====, X")
    fun `can determine next player`(line: String, expectedNextPlayer: Player){
        val boardState = BoardState(line)
        assertEquals(expectedNextPlayer, boardState.getNextPlayer())
    }

    @ParameterizedTest
    @CsvSource("X=X=OX=O=,O", "=====X==O,", "X=XO=OO==,X")
    fun `can determine next winner`(line: String, expectedNextWinner: Player?) {
        val result = BoardState(line)
        assertEquals(expectedNextWinner, result.getNextWinner())
    }

    @ParameterizedTest
    @CsvSource("X=X=OX=O=,false", "=====X==O,false", "XOX=OX=O=,true, XXOOOXXOX, true")
    fun `can determine if completed game`(line: String, expected: Boolean) {
        val result = BoardState(line)
        assertEquals(expected, result.isCompletedGame())
    }

}