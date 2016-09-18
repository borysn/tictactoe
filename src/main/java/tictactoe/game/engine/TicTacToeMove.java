package tictactoe.game.engine;


public class TicTacToeMove implements Move {

    private int x;
    private int y;

    public TicTacToeMove(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return this.x - this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TicTacToeMove) {
            TicTacToeMove move = (TicTacToeMove) o;
            return this.x == move.getX() && this.y == move.getY();
        }
        return false;
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
