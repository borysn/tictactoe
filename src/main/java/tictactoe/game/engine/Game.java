package tictactoe.game.engine;

import tictactoe.game.player.Player;

import java.util.Set;

public interface Game {
    void startGame();
    void initGame();
    void printGameStatus();
    void makeMove(Player player);
    void continueGame();
    void processGameState(GameState state, Player player);
    void reset();
    Set<Move> getMovesMade();
    GameState checkGameState();
}
