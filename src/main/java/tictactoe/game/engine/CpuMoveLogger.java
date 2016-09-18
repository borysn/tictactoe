package tictactoe.game.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tictactoe.game.player.TicTacToeCpuStrategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class CpuMoveLogger implements MoveLogger {

    private static final Logger logger = LoggerFactory.getLogger(TicTacToeCpuStrategy.class);
    private File logFile;

    public CpuMoveLogger() {
        try {
            this.initMoveLog();
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error(e.getMessage());
            this.logger.info("Could not initialize cpu move log.");
        }
    }

    @Override
    public void initMoveLog() {
        // log file
        File logFileDir = new File(System.getProperty("user.dir") + "/data");
        this.logFile = new File(System.getProperty("user.dir") + "/data/cpumoves.log");
        try {
            // check if log file dir exists
            if (!logFileDir.exists()) {
                // if not, create log file dir
                logFileDir.mkdir();
                // check if log file exists;
                if (!this.logFile.exists()) {
                    // create log file
                    this.logFile.createNewFile();
                }
            }
        } catch (IOException e) {
            this.logger.error(e.getMessage());
            this.logger.info("Could not edit cpu move log file.");
        }
    }

    @Override
    public void logMoves(Set<Move> moves) {
        try {
            // init file writer
            FileWriter bufferedWritter = new FileWriter(this.logFile.getAbsolutePath(), true);

            // traverse moves
            for (Move move : moves) {
                // write to log logFile
                String moveStr = "(" + move.getX() + "," + move.getY() + ")-";
                bufferedWritter.append(moveStr);
            }

            // add line break
            bufferedWritter.append("\n");
            // close writer
            bufferedWritter.close();
        } catch (IOException e) {
            this.logger.error(e.getMessage());
            this.logger.info("Could not log moves.");
        }
    }
}
