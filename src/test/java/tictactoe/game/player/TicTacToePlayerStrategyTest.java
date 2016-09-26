package tictactoe.game.player;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.game.engine.TicTacToeMove;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicTacToePlayerStrategyTest {

    TicTacToePlayerStrategy strategyMock = mock(TicTacToePlayerStrategy.class);

    @Test
    public void testGenerateMove() {
        // define playerMove return
        when(strategyMock.generateMove(new ArrayList<>()))
                .thenReturn(new TicTacToeMove(3,3));

        Assert.assertEquals("Player strategy generate move",
                strategyMock.generateMove(new ArrayList<>()),
                new TicTacToeMove(3,3));
    }

    @Test
    public void testParseMove() {
        TicTacToePlayerStrategy strategy = new TicTacToePlayerStrategy();

        String badMove1 = "3.3";
        String badMove2 = "33";
        String badMove3 = "asdf";
        String correctMove = "3,3";

        Assert.assertEquals("badMove1 should return invalid move",
                new TicTacToeMove(0,0),
                strategy.parseMove(badMove1));

        Assert.assertEquals("badMove2 should return invalid move",
                new TicTacToeMove(0,0),
                strategy.parseMove(badMove2));

        Assert.assertEquals("badMove3 should return invalid move",
                new TicTacToeMove(0,0),
                strategy.parseMove(badMove3));

        Assert.assertEquals("correctMove should return a valid move",
                new TicTacToeMove(3,3),
                strategy.parseMove(correctMove));
    }

}
