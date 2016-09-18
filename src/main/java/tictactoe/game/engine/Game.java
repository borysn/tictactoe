package tictactoe.game.engine;

import tictactoe.game.player.Player;

public interface Game {
    void startGame();
    void initGame();
    void printGameStatus();
    void makeMove(Player player);
    void continueGame();
    void processGameState(GameState state, Player player);
    void reset();
    GameState checkGameState();
}
