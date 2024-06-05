package tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandLineAppTest {
    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;
    private CommandLineApp app;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        app = new CommandLineApp();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    @Test
    public void canPrintHelp() {
        app.printHelp();
        assertEquals(
                "Please enter following command or Board State (e.g. =========) for analysis.\n" +
                        "REPORT          Generate Completed Game Analysis\n" +
                        "HELP            HELP\n" +
                        "EXIT            Exit",
                outputStreamCaptor.toString().trim()
        );
    }

    @Test
    public void canPrintCompletedGamesReport() {
        app.printCompletedGamesReport();
        assertEquals(
                "Winner Summary \n" +
                        "==============\n" +
                        "Player X: 3\n" +
                        "Player O: 2\n" +
                        "Draw: 65",
                outputStreamCaptor.toString().trim()
        );
    }

    @ParameterizedTest
    @CsvSource({
            "X=X=OX=O=,Next player can win",
            "=========,Next player cannot win",
            "XOX=OX=O=,This game is completed. The winner is player O",
            "XOXOXOXO=,This game is completed. The winner is player X"
    })
    public void canAnalyzeBoardState(String line, String expected) {
        app.determineBoardState(line);
        assertEquals(expected + "\n", outputStreamCaptor.toString());
    }
}
