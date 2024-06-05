package tictactoe;

import org.junit.jupiter.api.Test;
import tictactoe.CompletedGamesReport;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tictactoe.CompletedGamesAnalysis.generateCompletedGamesReport;

public class CompletedGamesAnalysisTest {

    @Test
    public void canGenerateReport() {
        List<String> lines = Arrays.asList("XX=OOO===", "XX=OOO===", "XX=OOO===", "XOX=OX=O=", "XX=OOO===", "XOXOXOXOX", "=========");
        CompletedGamesReport result = generateCompletedGamesReport(new ByteArrayInputStream(String.join("\n", lines).getBytes()));
        assertEquals(
                "Winner Summary \n" +
                        "==============\n" +
                        "Player X: 1\n" +
                        "Player O: 5\n" +
                        "Draw: 1\n",
                result.getContent()
        );
    }

    @Test
    public void canGenerateReportForHugeFile() {
        InputStream inputStream = CompletedGamesAnalysisTest.class.getResourceAsStream("/huge_completed_games.txt");
        CompletedGamesReport result = generateCompletedGamesReport(inputStream);
        assertEquals(
                "Winner Summary \n" +
                        "==============\n" +
                        "Player X: 1267200\n" +
                        "Player O: 1224960\n" +
                        "Draw: 464640\n",
                result.getContent()
        );
    }
}
