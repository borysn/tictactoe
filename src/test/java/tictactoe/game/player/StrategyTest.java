package tictactoe.game.player;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tictactoe.game.board.TicTacToePointValue;

public class StrategyTest {

    private static Player player;

    @BeforeClass
    public static void initPlayer() {
        StrategyTest.player = new TicTacToePlayer("TEST", TicTacToePointValue.X, new TicTacToeCpuStrategy());
    }

    @Test
    public void strategyTest() {
        Assert.assertEquals("instance of correct strategy", player.getStrategy() instanceof TicTacToeCpuStrategy, true);
    }

}
