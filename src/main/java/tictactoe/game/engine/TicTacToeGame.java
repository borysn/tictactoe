package tictactoe.game.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tictactoe.game.board.Board;
import tictactoe.game.board.TicTacToeBoard;
import tictactoe.game.board.TicTacToePointValue;
import tictactoe.game.player.Player;
import tictactoe.game.player.TicTacToePlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TicTacToeGame implements Game {

    private static final Logger logger = LoggerFactory.getLogger(TicTacToeGame.class);
    private Map<String, Player> players;
    private String playerName;
    private String cpuPlayerName;
    private BufferedReader br;
    private Board gameBoard;
    private CpuStrategy cpuStrategy;

    public TicTacToeGame() {
        // init players store
        this.players = new HashMap<>();
        // init cpu player name
        this.cpuPlayerName = "CPU";
        // init console input
        this.br = new BufferedReader(new InputStreamReader(System.in));
        // init gameboard
        this.gameBoard = new TicTacToeBoard();
        this.logger.info("Game board initialized");
        // init cpu strategy
        this.cpuStrategy = new TicTacToeCpuStrategy();
    }


    @Override
    public void startGame() {
        this.logger.info("Starting TicTacToe game engine");
        this.initGame();
    }

    @Override
    public void initGame() {
        // get player name
        String playerName = this.getPlayerNameFromUser();
        // set player
        this.initPlayer(new TicTacToePlayer(playerName, TicTacToePointValue.O));
        // set cpu player
        this.initCpuPlayer();
    }

    @Override
    public void printGameStatus() {
        System.out.println();
        // print separator
        System.out.println("--------------------------");

        // print player names and score
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            System.out.println("Player: " + entry.getKey() + "\n\tScore: " + entry.getValue().getScore());
        }
        // print game board
        this.gameBoard.printBoard();
    }

    @Override
    public void makeMove(Player player, Move move) {
        // log
        this.logger.info("Player: " + player.getName() + " made move (" + move.getX() + ", " + move.getY() + ")");

        // set game board value
        this.gameBoard.setPointValue(player.getPointValue(), move);

        // check if player won
        if (this.gameBoard.hasWinningMove()) {
            // update player score
            player.updateScore(player.getScore() + 1);
            // display win
            System.out.println("Player: " + player.getName() + " wins!");
        }
    }

    @Override
    public void continueGame() {
        // loop until game is over
        while(!isGameOver()) {
            // print game status
            this.printGameStatus();

            // cpu moves first
            this.cpuMove();

            // print game status
            this.printGameStatus();

            // check if game is over
            if (this.isGameOver()) {
                // exit game loop
                break;
            }

            // player moves second
            this.playerMove();
        }
        // print final game status
        this.printGameStatus();

        // reset game board
        this.gameBoard.resetBoard();
    }

    @Override
    public boolean isGameOver() {
        // check for win or draw
        if (this.gameBoard.hasWinningMove()) {
            System.out.println("This game has ended with a winning player.");
            return true;
        } else if (this.gameBoard.hasDraw()) {
            System.out.println("This game has ended with a draw.");
            return true;
        }

        return  false;
    }

    private void cpuMove() {
        // get player
        Player player = players.get(this.cpuPlayerName);
        Move move = this.cpuStrategy.generateMove();
        while (!this.gameBoard.isValidMove(move)) {
            move = this.cpuStrategy.generateMove();
        }
        this.makeMove(player, move);
    }

    private void playerMove() {
        // get player
        Player player = players.get(this.playerName);
        // get pos
        int x = 0;
        int y = 0;

        Move move = new TicTacToeMove(x, y);

        try {
            System.out.print("Enter move (comma separated): ");
            String input = br.readLine();
            move = parseMove(input);

            // get valid move
            while (!this.gameBoard.isValidMove(move)) {
                    System.out.print("Enter comma move (comma separated): ");
                    input = br.readLine();
                    move = parseMove(input);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.info("Player selected invalid move");
        }

        this.makeMove(player, move);
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

    private String getPlayerNameFromUser() {
        // read player name in from console
        System.out.println();
        System.out.print("Enter player name: ");
        String playerName = new String();

        try {
            playerName = this.br.readLine();
            System.out.println();
        } catch (IOException e) {
            this.logger.error(e.getMessage());
            this.logger.error("Could not set player name");
        } finally {
            this.logger.info("Human player initialized");
            this.logger.info("Playername: " + playerName);
            this.playerName = playerName;
            return playerName;
        }
    }

    private void initPlayer(Player player) {
        this.players.put(player.getName(), player);
    }

    private void initCpuPlayer() {
        Player player = new TicTacToePlayer(this.cpuPlayerName, TicTacToePointValue.X);
        this.players.put(player.getName(), player);
        System.out.println();
        this.logger.info("CPU player initialized");
    }
}
