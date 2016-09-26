package tictactoe.game.engine;

import org.junit.Assert;
import org.junit.Test;

public class TicTacToeGameStateTest {

    @Test
    public void testValue() {
        TicTacToeGameState state = TicTacToeGameState.NOWINNER;

        Assert.assertEquals("state should equal `NOWINNER`",
                TicTacToeGameState.NOWINNER.value(),
                state.value());
    }

}
