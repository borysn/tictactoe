package tictactoe.game.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tictactoe.game.engine.Move;
import tictactoe.game.engine.TicTacToeMove;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToePlayerStrategy implements Strategy {

    private final static Logger logger = LoggerFactory.getLogger(TicTacToePlayerStrategy.class);

    @Override
    public Move generateMove() {
        return this.playerMove();
    }

    private Move playerMove() {
        // init return
        Move move = new TicTacToeMove(3, 3);

        try {
            System.out.print("Enter move (comma separated): ");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            move = parseMove(input);

        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.info("Player selected invalid move");
        } finally {
            // return
            return move;
        }
    }

    private Move parseMove(String move) {
        try {
            int x = Integer.parseInt(move.split(",")[0].trim());
            int y = Integer.parseInt(move.split(",")[1].trim());

            return new TicTacToeMove(x, y);
        } catch (Exception e) {
            // catch any errors with parsing
            // return invalid move
            return new TicTacToeMove(0, 0);
        }
    }
}
