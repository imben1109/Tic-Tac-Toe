package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CompletedGamesAnalysis {
    public static  CompletedGamesReport generateCompletedGamesReport(InputStream inputStream) {
        int numberOfOWinner = 0;
        int numberOfXWinner = 0;
        int numberOfDraw = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()){
                    Player winner = new BoardState(line).getWinner();
                    if (winner == Player.O) {
                        numberOfOWinner++;
                    } else if (winner == Player.X) {
                        numberOfXWinner++;
                    } else {
                        numberOfDraw++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new CompletedGamesReport(numberOfXWinner, numberOfOWinner, numberOfDraw);
    }
}
