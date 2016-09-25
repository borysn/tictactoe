import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import player.PlayerTest;
import player.StrategyTest;
import player.TicTacToePlayerStrategyTest;
import player.TicTacToePlayerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlayerTest.class, StrategyTest.class, TicTacToePlayerTest.class,
        TicTacToePlayerStrategyTest.class
})
public class PlayerTests {
}
