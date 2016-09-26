package tictactoe.game.player;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.game.engine.TicTacToeMove;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class TicTacToeCpuStrategyTest {

    TicTacToeCpuStrategy strategyMock = spy(new TicTacToeCpuStrategy());

    @Test
    public void testGenerateMove() {
        doReturn(new TicTacToeMove(3,3)).when(strategyMock).generateRandomMove(new ArrayList<>());

        Assert.assertEquals("Should generate move 3,3",
                new TicTacToeMove(3,3),
                strategyMock.generateMove(new ArrayList<>()));
    }

}
