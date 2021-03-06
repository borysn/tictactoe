package tictactoe.game.player;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.game.board.TicTacToeBoard;
import tictactoe.game.board.TicTacToePoint;
import tictactoe.game.engine.Move;
import tictactoe.game.engine.TicTacToeMove;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Test
    public void testGenerateRandomMove() {
        // init empty points
        TicTacToeBoard board = new TicTacToeBoard();
        List<TicTacToePoint> emptyPoints = board.getBoardPointList();

        // total of 9 moves
        for (int i = 0; i < 9; i++) {
            // generate move
            Move move = strategyMock.generateRandomMove(emptyPoints.stream().collect(Collectors.toList()));
            // to tictactoe gameboard point
            TicTacToePoint point = new TicTacToePoint(move.getX() - 1, move.getY() - 1);

            // move should be contained in empty points set
            Assert.assertTrue("Generated move exists in empty points set", emptyPoints.contains(point));

            // remove point from set
            emptyPoints.remove(point);
        }
    }

}
