package tictactoe;

import tictactoe.game.engine.Engine;
import tictactoe.game.engine.TicTacToeEngine;

public class TicTacToe {
    public static void main(String [] args) {
        // begin tic tac toe game
        Engine gameEngine = new TicTacToeEngine();
        gameEngine.run();
    }
}
