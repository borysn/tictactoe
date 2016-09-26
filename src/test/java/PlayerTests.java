import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tictactoe.game.player.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlayerTest.class, StrategyTest.class, TicTacToePlayerTest.class,
        TicTacToePlayerStrategyTest.class, TicTacToeCpuStrategyTest.class
})
public class PlayerTests {
}
