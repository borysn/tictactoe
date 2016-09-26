import tictactoe.game.board.BoardTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tictactoe.game.board.TicTacToePointTest;
import tictactoe.game.board.TicTacToePointValueTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BoardTest.class, TicTacToePointValueTest.class, TicTacToePointTest.class
})
public class BoardTests {

}
