package tictactoe.game.board;

import org.junit.Assert;
import org.junit.Test;

public class TicTacToePointTest {

    @Test
    public void testEquals() {
        TicTacToePoint point1 = new TicTacToePoint(0, 1);
        TicTacToePoint point2 = new TicTacToePoint(0, 1);
        TicTacToePoint point3 = new TicTacToePoint(2, 1);

        Assert.assertEquals("point1 == point2", true, point1.equals(point2));
        Assert.assertEquals("point2 != point3", false, point2.equals(point3));
    }

    @Test
    public void testSetValue() {
        TicTacToePoint point = new TicTacToePoint(0, 1);
        point.setValue(TicTacToePointValue.X.value());

        Assert.assertEquals("point value should be 'X'",
                TicTacToePointValue.X.value(),
                point.getValue());
    }

    @Test
    public void testSetPosition() {
        TicTacToePoint point = new TicTacToePoint(0,1);
        point.setPosition(2, 1);

        Assert.assertEquals("x pos should be 2", 2, point.getXPos());
        Assert.assertEquals("y pos should be 1", 1, point.getYPos());
    }

}
