package tictactoe.game.player;

import tictactoe.game.board.PointValue;

public class TicTacToePlayer implements Player {

    private String name;
    private int score;
    private PointValue pointValue;
    private Strategy strategy;

    public TicTacToePlayer(String name, PointValue value, Strategy strategy) {
        this.setName(name);
        this.updateScore(0);
        this.setTeam(value);
        this.setStrategy(strategy);
    }

    @Override
    public void setTeam(PointValue value) {
        this.pointValue = value;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void updateScore(int score) {
        this.score = score;
    }

    @Override
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public PointValue getPointValue() {
        return this.pointValue;
    }

    @Override
    public Strategy getStrategy() {
        return this.strategy;
    }
}
