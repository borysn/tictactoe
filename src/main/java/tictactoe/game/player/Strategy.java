package tictactoe.game.player;

import tictactoe.game.engine.Move;

public interface Strategy {
    Move generateMove();
    void reset();
    void addMoveMade(Move move);
}
