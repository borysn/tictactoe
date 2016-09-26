package tictactoe.game.board;

import org.junit.Assert;
import org.junit.Test;

public class TicTacToePointValueTest {

    @Test
    public void testValue() {
        TicTacToePointValue pointValue = TicTacToePointValue.X;

        Assert.assertEquals("value should be 'X'",
                TicTacToePointValue.X.value(),
                pointValue.value());
    }

}
