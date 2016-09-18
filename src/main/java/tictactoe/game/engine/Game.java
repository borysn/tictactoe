package tictactoe.game.engine;

import tictactoe.game.player.Player;

public interface Game {
    void startGame();
    void initGame();
    void printGameStatus();
    void makeMove(Player player, Move move);
    void continueGame();
    boolean isGameOver();
}
