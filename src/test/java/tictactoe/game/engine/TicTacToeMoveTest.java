package tictactoe.game.engine;

import org.junit.Assert;
import org.junit.Test;

public class TicTacToeMoveTest {

    @Test
    public void testEquals() {
        TicTacToeMove move1 = new TicTacToeMove(3,3);
        TicTacToeMove move2 = new TicTacToeMove(3,3);
        TicTacToeMove move3 = new TicTacToeMove(2,3);

        Assert.assertEquals("move1 == move2", true, move1.equals(move2));
        Assert.assertEquals("move2 != move3", false, move2.equals(move3));
    }

}
