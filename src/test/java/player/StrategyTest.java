package player;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tictactoe.game.board.TicTacToePointValue;
import tictactoe.game.player.Player;
import tictactoe.game.player.TicTacToeCpuStrategy;
import tictactoe.game.player.TicTacToePlayer;

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
