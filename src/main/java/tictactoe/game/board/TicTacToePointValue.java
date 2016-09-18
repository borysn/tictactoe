package tictactoe.game.board;

public enum TicTacToePointValue implements PointValue {
    EMPTY(" "),
    YPOS1(" 1"),
    YPOS2(" 2"),
    YPOS3(" 3"),
    XPOS1("1"),
    XPOS2("2"),
    XPOS3("3"),
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
