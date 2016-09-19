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
import java.util.*;
import java.util.stream.Collectors;

public class TicTacToeGame implements Game {

    private static final Logger logger = LoggerFactory.getLogger(TicTacToeGame.class);

    private Set<Player> players;
    private Map<Move, Player> movesMade;
    private Board gameBoard;
    private boolean isGameOver;
    private MoveLogger cpuMoveLogger;

    public TicTacToeGame() {
        // game over
        this.isGameOver = false;
        // init players store
        this.players = new LinkedHashSet<>();
        // init moves store
        this.movesMade = new HashMap<>();
        // init moves logger for cpu
        this.cpuMoveLogger = new CpuMoveLogger();
        // init gameboard
        this.gameBoard = new TicTacToeBoard();
        this.logger.info("Game board initialized");
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
        Move move = player.getStrategy().generateMove(this.gameBoard.getBoardPointList());

        // make sure move is valid
        while (!this.gameBoard.isValidMove(move, this.movesMade.keySet())) {
            move = player.getStrategy().generateMove(this.gameBoard.getBoardPointList());
        }

        // make move
        this.move(player, move);
        player.getStrategy().addMoveMade(move);
        this.movesMade.put(move, player);
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
            System.out.println("Player: " + player.getName() + " wins!");
            player.updateScore(player.getScore() + 1);
            this.isGameOver = true;
            // if cpu player lost
            if (!player.getName().equals(this.getCpuPlayer().getName())) {
                this.logCpuMoves();
            }
        } else if (state.value().equals(TicTacToeGameState.DRAW.value())) {
            this.isGameOver = true;
            this.logCpuMoves();
        }
    }

    @Override
    public void reset() {
        // reset game board
        this.gameBoard.resetBoard();

        // reset moves made
        this.movesMade = new HashMap<>();

        // reset game over
        this.isGameOver = false;

        // reset player strategies
        this.players.forEach(p -> p.getStrategy().reset());
    }

    @Override
    public Map<Move, Player> getMovesMade() {
        return this.movesMade;
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

    private void logCpuMoves() {
        // get moves made by cpu
        Set<Map.Entry<Move, Player>> entries = this.movesMade.entrySet().stream()
                .filter(e -> e.getValue().getName().equals(this.getCpuPlayer().getName()))
                .collect(Collectors.toSet());

        // moves
        Set<Move> moves = new LinkedHashSet<>();

        // add moves to set
        entries.forEach(e -> moves.add(e.getKey()));

        // log moves
        this.cpuMoveLogger.logMoves(moves);
    }

    private void move(Player player, Move move) {
        // log
        this.logger.info("Player: " + player.getName() + " made move (" + move.getX() + ", " + move.getY() + ")");

        // set game board value
        this.gameBoard.setPointValue(player.getPointValue(), move);
    }

    private Player getCpuPlayer() {
        return this.players.iterator().next();
    }

    private String getPlayerNameFromUser() {
        // read player name in from console
        System.out.println();
        System.out.print("Enter player name: ");
        String playerName = new String();

        // init console input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            playerName = br.readLine();
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
