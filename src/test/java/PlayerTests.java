import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tictactoe.game.player.PlayerTest;
import tictactoe.game.player.StrategyTest;
import tictactoe.game.player.TicTacToePlayerStrategyTest;
import tictactoe.game.player.TicTacToePlayerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlayerTest.class, StrategyTest.class, TicTacToePlayerTest.class,
        TicTacToePlayerStrategyTest.class
})
public class PlayerTests {
}
