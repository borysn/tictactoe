package tictactoe.game.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tictactoe.game.engine.Move;
import tictactoe.game.engine.TicTacToeMove;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class TicTacToeCpuStrategy implements Strategy {

    private static final Logger logger = LoggerFactory.getLogger(TicTacToeCpuStrategy.class);
    private Set<Move> moves;
    private List<Set<Move>> loggedMoves;

    public TicTacToeCpuStrategy() {
        this.moves = new LinkedHashSet<>();
        this.loggedMoves = new ArrayList<>();
        this.getLoggedMoves();
    }

    private void getLoggedMoves() {
        File logFile = new File(System.getProperty("user.dir") + "/data/cpumoves.log");
        try {
            FileReader fr = new FileReader(logFile.getAbsolutePath());
            BufferedReader br = new BufferedReader(fr);
            // traverse each file line
            br.lines().forEach(line -> {
                // store moves
                Set<Move> moves = new LinkedHashSet<>();
                // split line by "-"
                for (String point : line.split("-")) {
                    // extract points
                    int x = Integer.parseInt(point.split(",")[0].split("\\(")[1]);
                    int y = Integer.parseInt(point.split(",")[1].split("\\)")[0]);
                    Move move = new TicTacToeMove(x, y);
                    moves.add(move);
                }
                this.loggedMoves.add(moves);
            });
            br.close();
            fr.close();
        } catch (IOException e) {
            this.logger.error(e.getMessage());
            this.logger.info("Could not get logged cpu moves");
        }
    }

    @Override
    public Move generateMove() {
        // generate random x pos (1-3 inclusive)
        int x = (int)(Math.random() * ((3 - 1) + 1)) + 1;
        // generate random y pos (1-3 inclusive)
        int y = (int)(Math.random() * ((3 - 1) + 1)) + 1;

        Move move = new TicTacToeMove(x, y);
        return move;
    }

    @Override
    public void reset() {
        this.moves = new LinkedHashSet<>();
    }

    @Override
    public void addMoveMade(Move move) {
        this.moves.add(move);
    }
}
