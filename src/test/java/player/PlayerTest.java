package player;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tictactoe.game.board.TicTacToePointValue;
import tictactoe.game.player.Player;
import tictactoe.game.player.TicTacToeCpuStrategy;
import tictactoe.game.player.TicTacToePlayer;

public class PlayerTest {

    private static Player player;

    @BeforeClass
    public static void initPlayer() {
        PlayerTest.player = new TicTacToePlayer("TEST", TicTacToePointValue.X, new TicTacToeCpuStrategy());
    }

    @Test
    public void playerCreation() {
        Assert.assertEquals("Player name", this.player.getName(), "TEST");
        Assert.assertEquals("Player value", this.player.getPointValue().value(), TicTacToePointValue.X.value());
        Assert.assertEquals("Player strategy", this.player.getStrategy() instanceof TicTacToeCpuStrategy, true);
    }


}
