package player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import tictactoe.game.board.TicTacToePoint;
import tictactoe.game.engine.TicTacToeMove;
import tictactoe.game.player.Strategy;
import tictactoe.game.player.TicTacToePlayerStrategy;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class TicTacToePlayerStrategyTest {

    public static Strategy strategy = Mockito.mock(TicTacToePlayerStrategy.class);

    @Test
    public void testGenerateMove() {
        // define playerMove return
        when(strategy.generateMove(new ArrayList<TicTacToePoint>()))
                .thenReturn(new TicTacToeMove(3,3));

        Assert.assertEquals("Player strategy generate move",
                strategy.generateMove(new ArrayList<TicTacToePoint>()),
                new TicTacToeMove(3,3));
    }

}
