package tictactoe.game.board;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.game.engine.Move;
import tictactoe.game.engine.TicTacToeMove;

import java.util.LinkedHashSet;
import java.util.Set;

public class TicTacToeBoardTest {

    @Test
    public void testInitBoard() {
        TicTacToeBoard board = new TicTacToeBoard();

        Assert.assertEquals("board x size == 3", 3, board.getXSize());
        Assert.assertEquals("board y size == 3", 3, board.getYSize());
    }

    @Test
    public void testResetBoard() {
        TicTacToeBoard board = new TicTacToeBoard();

        // set some board point values
        PointValue value1 = TicTacToePointValue.X;
        Move move1 = new TicTacToeMove(3,3);
        PointValue value2 = TicTacToePointValue.O;
        Move move2 = new TicTacToeMove(2,3);

        board.setPointValue(value1, move1);
        board.setPointValue(value2, move2);

        // reset board
        board.resetBoard();

        // make sure all point values are empty
        board.getBoardPointList().stream().forEach(point ->
            Assert.assertEquals("point should be empty",
                    TicTacToePointValue.EMPTY.value(),
                    point.getValue())
        );

    }

    @Test
    public void testSetPointValue() {
        TicTacToeBoard board = new TicTacToeBoard();

        // init point value and move
        PointValue value = TicTacToePointValue.X;
        Move move = new TicTacToeMove(3,3);

        // set value
        board.setPointValue(value, move);

        // create board point
        TicTacToePoint point = new TicTacToePoint(move.getX()-1, move.getY()-1);
        point.setValue(value.value());

        // check that board points list has the set values
        Assert.assertEquals("board contains point value",
                true, board.getBoardPointList().contains(point));
    }

    @Test
    public void testIsValidMove() {
        // init values
        TicTacToeBoard board = new TicTacToeBoard();
        Set<Move> movesMade = new LinkedHashSet<>();

        // test out of bounds
        Move move1 = new TicTacToeMove(0, 0);
        Move move2 = new TicTacToeMove(4, 4);
        movesMade.add(move1);
        movesMade.add(move2);

        Assert.assertEquals("invalid move",
                false, board.isValidMove(move1, movesMade));
        Assert.assertEquals("invalid move",
                false, board.isValidMove(move2, movesMade));

        // test move already made
        Move move3 = new TicTacToeMove(1,1);
        movesMade.add(move3);

        Assert.assertEquals("move already made",
                false, board.isValidMove(move3, movesMade));

        // test valid move
        Move move4 = new TicTacToeMove(1,2);
        Assert.assertEquals("valid move",
                true, board.isValidMove(move4, movesMade));
    }

    @Test
    public void testHasWinningMove() {
        // init board
        TicTacToeBoard board = new TicTacToeBoard();
        PointValue value = TicTacToePointValue.O;

        // test winning row
        board.setPointValue(value, new TicTacToeMove(1,1));
        board.setPointValue(value, new TicTacToeMove(2,1));
        board.setPointValue(value, new TicTacToeMove(3,1));

        Assert.assertEquals("winning row", true, board.hasWinningMove());

        // clear board
        board.resetBoard();

        // test winning column
        board.setPointValue(value, new TicTacToeMove(1,1));
        board.setPointValue(value, new TicTacToeMove(1,2));
        board.setPointValue(value, new TicTacToeMove(1,3));

        Assert.assertEquals("winning row", true, board.hasWinningMove());

        // clear board
        board.resetBoard();

        // test winning diagonal
        board.setPointValue(value, new TicTacToeMove(1,1));
        board.setPointValue(value, new TicTacToeMove(2,2));
        board.setPointValue(value, new TicTacToeMove(3,3));

        Assert.assertEquals("winning row", true, board.hasWinningMove());

        // clear board
        board.resetBoard();

        // test winning diagonal
        board.setPointValue(value, new TicTacToeMove(3,1));
        board.setPointValue(value, new TicTacToeMove(2,2));
        board.setPointValue(value, new TicTacToeMove(1,3));

        Assert.assertEquals("winning row", true, board.hasWinningMove());

    }

}
