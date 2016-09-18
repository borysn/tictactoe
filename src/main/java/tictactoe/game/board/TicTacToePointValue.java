package tictactoe.game.board;

public enum TicTacToePointValue implements PointValue {
    EMPTY(" "),
    X("X"),
    O("O");

    private String value;

    TicTacToePointValue(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }
}
