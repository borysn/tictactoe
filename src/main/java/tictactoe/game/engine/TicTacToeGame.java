package tictactoe.game.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tictactoe.game.board.Board;
import tictactoe.game.board.TicTacToeBoard;
import tictactoe.game.board.TicTacToePointValue;
import tictactoe.game.player.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class TicTacToeGame implements Game {

    private static final Logger logger = LoggerFactory.getLogger(TicTacToeGame.class);

    private Set<Player> players;
    private BufferedReader br;
    private Board gameBoard;
    private Strategy strategy;
    private boolean isGameOver;

    public TicTacToeGame() {
        // game over
        this.isGameOver = false;
        // init players store
        this.players = new LinkedHashSet<>();
        // init console input
        this.br = new BufferedReader(new InputStreamReader(System.in));
        // init gameboard
        this.gameBoard = new TicTacToeBoard();
        this.logger.info("Game board initialized");
        // init cpu strategy
        this.strategy = new TicTacToeCpuStrategy();
    }


    @Override
    public void startGame() {
        this.logger.info("Starting TicTacToe game engine");
        this.initGame();
    }

    @Override
    public void initGame() {
        // set cpu player
        this.initCpuPlayer();
        // get player name
        String playerName = this.getPlayerNameFromUser();
        // set player
        this.players.add(new TicTacToePlayer(playerName, TicTacToePointValue.O, new TicTacToePlayerStrategy()));
    }

    @Override
    public void printGameStatus() {
        System.out.println();
        // print separator
        System.out.println("--------------------------");

        // print player names and score
        for (Player player : this.players) {
            System.out.println("Player: " + player.getName() + "\n\tScore: " + player.getScore());
        }
        // print game board
        this.gameBoard.printBoard();
    }

    @Override
    public void makeMove(Player player) {
        // get player
        Move move = player.getStrategy().generateMove();

        // make sure move is valid
        while (!this.gameBoard.isValidMove(move)) {
            move = player.getStrategy().generateMove();
        }

        // make move
        this.move(player, move);
    }

    @Override
    public void continueGame() {
        // loop until game is over
        while(!this.isGameOver) {
            // each player makes their move
            for (Player player : this.players) {
                // print game status
                this.printGameStatus();
                // make player move
                this.makeMove(player);
                // check game state
                this.processGameState(this.checkGameState(), player);
                // break if game is over
                if (this.isGameOver) {
                    break;
                }
            }
        }
        // print final game status
        this.printGameStatus();
        // reset game
        this.reset();
    }

    @Override
    public void processGameState(GameState state, Player player) {
        // if game state shows player is a winner, update score
        if (state.value().equals(TicTacToeGameState.WINNER.value())) {
            player.updateScore(player.getScore() + 1);
            this.isGameOver = true;
        } else if (state.value().equals(TicTacToeGameState.DRAW.value())) {
            this.isGameOver = true;
        }
    }

    @Override
    public void reset() {
        // reset game board
        this.gameBoard.resetBoard();

        // reset game over
        this.isGameOver = false;
    }

    @Override
    public GameState checkGameState() {
        // check for win or draw
        if (this.gameBoard.hasWinningMove()) {
            System.out.println("This game has ended with a winning player.");
            return TicTacToeGameState.WINNER;
        } else if (this.gameBoard.hasDraw()) {
            System.out.println("This game has ended with a draw.");
            return TicTacToeGameState.DRAW;
        }

        return TicTacToeGameState.NOWINNER;
    }

    private void move(Player player, Move move) {
        // log
        this.logger.info("Player: " + player.getName() + " made move (" + move.getX() + ", " + move.getY() + ")");

        // set game board value
        this.gameBoard.setPointValue(player.getPointValue(), move);
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
            return playerName;
        }
    }

    private void initCpuPlayer() {
        Player player = new TicTacToePlayer("CPU", TicTacToePointValue.X, new TicTacToeCpuStrategy());
        this.players.add(player);
        System.out.println();
        this.logger.info("CPU player initialized");
    }
}
