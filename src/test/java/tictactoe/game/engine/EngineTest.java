package tictactoe.game.engine;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class EngineTest {

    private static Engine gameEngine;

    @BeforeClass
    public static void initGameEngine() {
        EngineTest.gameEngine = new TicTacToeEngine();
    }

    @Test
    public void gameEngineTest() {
        Assert.assertEquals("instance of", EngineTest.gameEngine instanceof TicTacToeEngine, true);
    }

}
