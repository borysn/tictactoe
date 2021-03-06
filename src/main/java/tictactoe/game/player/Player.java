package tictactoe.game.player;

import tictactoe.game.board.PointValue;

public interface Player {
    void setTeam(PointValue value);
    void setName(String name);
    void updateScore(int score);
    void setStrategy(Strategy strategy);
    String getName();
    int getScore();
    PointValue getPointValue();
    Strategy getStrategy();
}
