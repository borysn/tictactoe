package tictactoe.game.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToeEngine implements Engine {

    private static final Logger logger = LoggerFactory.getLogger(TicTacToeEngine.class);
    private Game ticTacToeGame;

    public TicTacToeEngine() {
        this.ticTacToeGame = new TicTacToeGame();
    }

    @Override
    public void run() {
        // start game
        this.ticTacToeGame.startGame();
        // game loop flag
        boolean continueGame = true;

        // play until user quits
        while(continueGame) {
            this.ticTacToeGame.continueGame();
            continueGame = this.keepPlaying();
        }
    }

    protected boolean keepPlaying() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = new String();
        boolean invalidInput = true;

        System.out.print("Keep playing? (y/n): ");

        while(invalidInput) {
            try {
                input = br.readLine();
                if (input.toLowerCase().trim().equals("y") || input.toLowerCase().trim().equals("n")) {
                    invalidInput = false;
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
                logger.info("Could not read user option");
                // end game
                return false;
            }
        }

        return input.toLowerCase().trim().equals("y") ? true : false;
    }
}
