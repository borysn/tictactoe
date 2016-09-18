package tictactoe.game.engine;

public class TicTacToeMove implements Move {

    private int x;
    private int y;

    public TicTacToeMove(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
