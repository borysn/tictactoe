package tictactoe.game.engine;

import java.util.Set;

public interface MoveLogger {
    void initMoveLog();
    void logMoves(Set<Move> moves);
}
