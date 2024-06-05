package tictactoe;

/**
 * Represents Board State
 */
public class BoardState {
    private String line;

    public BoardState(String line) {
        if (line.length() != 9) {
            throw new IllegalArgumentException("Required exactly 9 Characters");
        }
        for (char c : line.toCharArray()) {
            if (c != 'X' && c != 'O' && c != '=') {
                throw new IllegalArgumentException("Unexpected Character - " + c);
            }
        }
        this.line = line;
    }

    /**
     * get winner
     * @return winner   null if no winner
     */
    public Player getWinner() {
        Player winner = getHorizontalWinner();
        if (winner == null) {
            winner = getVerticalWinner();
        }
        if (winner == null) {
            winner = getDiagonalWinner();
        }
        return winner;
    }

    /**
     * get next player
     * @return next player
     */
    public Player getNextPlayer() {
        int numberOfX = 0;
        int numberOfO = 0;
        for (char c : line.toCharArray()) {
            if (c == Player.X.getCharValue()) {
                numberOfX++;
            } else if (c == Player.O.getCharValue()) {
                numberOfO++;
            }
        }
        return numberOfO > numberOfX ? Player.X : Player.O;
    }

    /**
     * get next winner
     * @return next winner  null if no next winner
     */
    public Player getNextWinner() {
        Player nextPlayer = getNextPlayer();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '=') {
                String nextPossibleBoardState = line.substring(0, i) + nextPlayer.toString() + line.substring(i + 1);
                if (new BoardState(nextPossibleBoardState).getWinner() == nextPlayer) {
                    return nextPlayer;
                }
            }
        }
        return null;
    }

    /**
     * is current game completed
     */
    public boolean isCompletedGame() {
        if (getWinner() != null) {
            return true;
        }
        for (char c : line.toCharArray()) {
            if (c == '=') {
                return false;
            }
        }
        return true;
    }

    private Player getHorizontalWinner() {
        for (int i = 0; i < 3; i++) {
            int countX = 0;
            int countO = 0;
            for (int j = 0; j < 3; j++) {
                char c = line.charAt(i * 3 + j);
                if (c == Player.X.getCharValue()) {
                    countX++;
                } else if (c == Player.O.getCharValue()) {
                    countO++;
                }
            }
            if (countX == 3) {
                return Player.X;
            }
            if (countO == 3) {
                return Player.O;
            }
        }
        return null;
    }

    private Player getVerticalWinner() {
        for (int i = 0; i < 3; i++) {
            int countX = 0;
            int countO = 0;
            for (int j = 0; j < 3; j++) {
                char c = line.charAt(j * 3 + i);
                if (c == Player.X.getCharValue()) {
                    countX++;
                } else if (c == Player.O.getCharValue()) {
                    countO++;
                }
            }
            if (countX == 3) {
                return Player.X;
            }
            if (countO == 3) {
                return Player.O;
            }
        }
        return null;
    }

    private Player getDiagonalWinner() {
        int countX1 = 0;
        int countO1 = 0;
        int countX2 = 0;
        int countO2 = 0;
        for (int i = 0; i < 3; i++) {
            char c1 = line.charAt(i * 3 + i);
            char c2 = line.charAt((2 - i) * 3 + i);
            if (c1 == Player.X.getCharValue()) {
                countX1++;
            } else if (c1 == Player.O.getCharValue()) {
                countO1++;
            }
            if (c2 == Player.X.getCharValue()) {
                countX2++;
            } else if (c2 == Player.O.getCharValue()) {
                countO2++;
            }
        }
        if (countX1 == 3 || countX2 == 3) {
            return Player.X;
        }
        if (countO1 == 3 || countO2 == 3) {
            return Player.O;
        }
        return null;
    }
}
