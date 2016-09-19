import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import player.PlayerTest;
import player.StrategyTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({PlayerTest.class, StrategyTest.class})
public class PlayerTests {
}
