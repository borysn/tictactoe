package tictactoe.game.player;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.game.board.TicTacToePointValue;

public class TicTacToePlayerTest {

    public static TicTacToePointValue pointValue = TicTacToePointValue.X;
    public static Strategy strategy = new TicTacToePlayerStrategy();
    public static String name = "player1";
    public static Player player = new TicTacToePlayer(name, pointValue, strategy);

    @Test
    public void testName() {
        Assert.assertEquals("Player name test", TicTacToePlayerTest.name,
                TicTacToePlayerTest.player.getName());
    }

    @Test
    public void testStrategy() {
        Assert.assertEquals("Player startegy", true,
                TicTacToePlayerTest.player.getStrategy() instanceof TicTacToePlayerStrategy);
    }

    @Test
    public void testPointValue() {
        Assert.assertEquals("Player point value", TicTacToePlayerTest.pointValue.value(),
                TicTacToePlayerTest.player.getPointValue().value());
    }

    @Test
    public void testScore() {
        // update score
        TicTacToePlayerTest.player.updateScore(2);
        Assert.assertEquals("Player update score", 2, TicTacToePlayerTest.player.getScore());
    }

}
