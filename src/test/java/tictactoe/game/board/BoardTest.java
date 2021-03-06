package tictactoe.game.board;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest {

    private static Board gameBoard;

    @BeforeClass
    public static void initGameBoard() {
        BoardTest.gameBoard = new TicTacToeBoard();
    }

    @Test
    public void gameBoardTest() {
        Assert.assertEquals("3 x 3 size", BoardTest.gameBoard.getXSize() == 3 && BoardTest.gameBoard.getYSize() == 3, true);
    }

}
