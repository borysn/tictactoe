package tictactoe.game.board;

import tictactoe.game.engine.Move;

public interface Board {
    void initBoard(int x, int y);
    void resetBoard();
    void setPointValue(PointValue value, Move move);
    void printBoard();
    int getXSize();
    int getYSize();
    boolean isValidMove(Move move);
    boolean hasWinningMove();
    boolean hasDraw();
}
