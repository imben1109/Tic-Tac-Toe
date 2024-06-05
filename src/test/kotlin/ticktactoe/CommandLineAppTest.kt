package ticktactoe

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import tictactoe.CommandLineApp
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals


class CommandLineAppTest {
    private val standardIn = System.`in`
    private val standardOut = System.out
    private lateinit var app: CommandLineApp
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        app = CommandLineApp()
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown(){
        System.setOut(PrintStream(standardOut))
        System.setIn(standardIn)
    }
    
    @Test
    fun `can print help`(){
        app.printHelp()
        assertEquals("""
            Please enter following command or Board State (e.g. =========) for analysis.
            REPORT          Generate Completed Game Analysis
            HELP            HELP
            EXIT            Exit
            
        """.trimIndent(), outputStreamCaptor.toString())
    }

    @Test
    fun `can print completed games report`(){
        app.printCompletedGamesReport()
        assertEquals("""
            Winner Summary 
            ==============
            Player X: 3 
            Player O: 2 
            Draw: 65 
            
        """.trimIndent(), outputStreamCaptor.toString())
    }

    @ParameterizedTest
    @CsvSource(
        "X=X=OX=O=,Next player can win",
        "=========,Next player cannot win",
        "XOX=OX=O=,This game is completed. The winner is player O",
        "XOXOXOXO=,This game is completed. The winner is player X"
    )
    fun `can analyze board state`(line: String, expected: String){
        app.determineBoardState(line)
        assertEquals(expected + "\n", outputStreamCaptor.toString())
    }
}