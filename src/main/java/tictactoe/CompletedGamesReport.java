package tictactoe;

public class CompletedGamesReport {
    private int numberOfXWinner;
    private int numberOfOWinner;
    private int numberOfDraw;

    public CompletedGamesReport(int numberOfXWinner, int numberOfOWinner, int numberOfDraw) {
        this.numberOfXWinner = numberOfXWinner;
        this.numberOfOWinner = numberOfOWinner;
        this.numberOfDraw = numberOfDraw;
    }

    public String getContent() {
        return "Winner Summary \n" +
                "==============\n" +
                "Player X: " + numberOfXWinner + "\n" +
                "Player O: " + numberOfOWinner + "\n" +
                "Draw: " + numberOfDraw + "\n";
    }
}
