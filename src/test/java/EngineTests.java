import tictactoe.game.engine.EngineTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tictactoe.game.engine.TicTacToeGameStateTest;
import tictactoe.game.engine.TicTacToeMoveTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EngineTest.class, TicTacToeMoveTest.class, TicTacToeGameStateTest.class
})
public class EngineTests {
}
