package tictactoe;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

import static tictactoe.CompletedGamesAnalysis.generateCompletedGamesReport;

public class CommandLineApp {
    private File tempGamesInputFile;

    /**
     * Command Line User Interface for Tic Tac Toe Analysis
     *
     *     It would create tempoarty file for storing board state in case there are huge number of board state for analysis.
     */
    public CommandLineApp() {
        InputStream incompleteGames = this.getClass().getResourceAsStream("/incompleted_games.txt");
        File file = null;
        try {
            file = File.createTempFile("tictactoe", ".txt");
            file.deleteOnExit();
            System.out.println("Tic Tac Toe Analysis Temporary Store: " + file);

            Files.copy(incompleteGames, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            tempGamesInputFile = file;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * print Help
     */
    public void printHelp() {
        System.out.println(
                "Please enter following command or Board State (e.g. =========) for analysis.\n" +
                        "REPORT          Generate Completed Game Analysis\n" +
                        "HELP            HELP\n" +
                        "EXIT            Exit\n"
        );
    }

    /**
     * print completed games report
     */
    public void printCompletedGamesReport() {
        try (InputStream is = new FileInputStream(tempGamesInputFile)) {
            CompletedGamesReport report = generateCompletedGamesReport(is);
            System.out.println(report.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Determine Board State
     *      if game is completed, it prints winner.
     *      if game is not completed, it prints winner for next game.
     */
    public void determineBoardState(String line) {
        try {
            BoardState boardState = new BoardState(line);
            boolean isCompletedGame = boardState.isCompletedGame();
            if (isCompletedGame){
                Player winner = boardState.getWinner();
                if (winner != null ){
                    System.out.println("This game is completed. The winner is player " + winner);
                } else {
                    System.out.println("This game is completed. No one win this game");
                }
            } else {
                String message = (boardState.getNextWinner() == null) ? "Next player cannot win" : "Next player can win";
                System.out.println(message);
            }
            Files.write(tempGamesInputFile.toPath(), ("\n" + line).getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        CommandLineApp app = new CommandLineApp();
        System.out.println(
                "===============================\n" +
                        "Welcome to Tic Tac Toe Analysis\n" +
                        "==============================="
        );
        app.printHelp();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine().toUpperCase();
            switch (line) {
                case "REPORT":
                    app.printCompletedGamesReport();
                    break;
                case "EXIT":
                    return;
                case "", "HELP":
                    app.printHelp();
                    break;
                default:
                    app.determineBoardState(line);
                    break;
            }
        }
    }

}
