package tictactoe.game.player;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.game.engine.TicTacToeMove;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicTacToePlayerStrategyTest {

    TicTacToePlayerStrategy strategy = mock(TicTacToePlayerStrategy.class);

    @Test
    public void testGenerateMove() {
        // define playerMove return
        when(strategy.generateMove(new ArrayList<>()))
                .thenReturn(new TicTacToeMove(3,3));

        Assert.assertEquals("Player strategy generate move",
                strategy.generateMove(new ArrayList<>()),
                new TicTacToeMove(3,3));
    }

}
