package tictactoe.game.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tictactoe.game.board.TicTacToePoint;
import tictactoe.game.board.TicTacToePointValue;
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
import java.util.stream.Collectors;


public class TicTacToeCpuStrategy implements Strategy {

    private static final Logger logger = LoggerFactory.getLogger(TicTacToeCpuStrategy.class);
    private Set<Move> moves;
    private List<Set<Move>> loggedMoves;

    public TicTacToeCpuStrategy() {
        this.moves = new LinkedHashSet<>();
        this.loggedMoves = new ArrayList<>();
        this.getLoggedMoves();
    }

    protected void getLoggedMoves() {
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
    public Move generateMove(List<TicTacToePoint> gameBoardPoints) {
        // get empty points
        List<TicTacToePoint> emptyPoints = new ArrayList<>();
        emptyPoints.addAll(gameBoardPoints.stream()
                .filter(point -> point.getValue().equals(TicTacToePointValue.EMPTY.value()))
                .collect(Collectors.toList()));

        // get random move
        Move move = this.generateRandomMove(emptyPoints);

        // if emptypoints size is > 1
        if (emptyPoints.size() > 1) {
            // create test move set
            Set<Move> testMoveSet = new LinkedHashSet<>();
            testMoveSet.addAll(this.moves);
            testMoveSet.add(move);

            // test against current move set and logged moves
            while (!this.moves.contains(move) && this.isCurrentMoveSetTooSimilar(testMoveSet)) {
                move = this.generateRandomMove(emptyPoints);
                // re-generate test move set
                testMoveSet = new LinkedHashSet<>();
                testMoveSet.addAll(this.moves);
                testMoveSet.add(move);
            }
        }

        return move;
    }

    protected Move generateRandomMove(List<TicTacToePoint> emptyPoints) {
        // pick one empty point at random
        int total = emptyPoints.size();
        // chose random number between 0-total
        int random = (int)(Math.random() * total);
        TicTacToePoint point = emptyPoints.get(random);

        return new TicTacToeMove(point.getXPos()+1, point.getYPos()+1);
    }

    protected boolean isCurrentMoveSetTooSimilar(Set<Move> testMoveSet) {
        // iterate logged moves
        for (Set<Move> moves : this.loggedMoves) {
            // total
            int size = moves.size();
            // successful comparison tracker
            int matchCount = 0;

            // iterate moves
            for (Move move : moves) {
                if (testMoveSet.contains(move)) {
                    matchCount++;
                }
            }

            // average close to or over 90%, return true
            if (matchCount/size >= .9) {
                return true;
            }
        }
        return false;
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
