package tictactoe.game.player;

import tictactoe.game.board.TicTacToePoint;
import tictactoe.game.engine.Move;

import java.util.List;

public interface Strategy {
    Move generateMove(List<TicTacToePoint> gameBoardPoints);
    void reset();
    void addMoveMade(Move move);
}
