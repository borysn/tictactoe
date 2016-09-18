package tictactoe.game.player;

import tictactoe.game.engine.Move;
import tictactoe.game.engine.TicTacToeMove;

public class TicTacToeCpuStrategy implements Strategy {
    @Override
    public Move generateMove() {
        // generate random x pos (1-3 inclusive)
        int x = (int)(Math.random() * ((3 - 1) + 1)) + 1;
        // generate random y pos (1-3 inclusive)
        int y = (int)(Math.random() * ((3 - 1) + 1)) + 1;

        return new TicTacToeMove(x, y);
    }
}
