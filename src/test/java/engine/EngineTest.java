package engine;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tictactoe.game.engine.Engine;
import tictactoe.game.engine.TicTacToeEngine;

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
