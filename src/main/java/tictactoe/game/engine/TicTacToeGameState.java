package tictactoe.game.engine;

public enum TicTacToeGameState implements GameState {
    NOWINNER("NOWINNER"),
    WINNER("WINNER"),
    DRAW("DRAW");

    private String state;

    TicTacToeGameState(String state) {
        this.state = state;
    }

    @Override
    public String value() {
        return this.state;
    }
}
