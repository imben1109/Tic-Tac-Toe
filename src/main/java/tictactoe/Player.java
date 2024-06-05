package tictactoe;

public enum Player {
    X('X'), O('O');

    private final char charValue;

    Player(char charValue) {
        this.charValue = charValue;
    }

    public char getCharValue(){
        return this.charValue;
    }
}