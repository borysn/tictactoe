package tictactoe.game.player;

import tictactoe.game.board.PointValue;

public class TicTacToePlayer implements Player {

    private String name;
    private int score;
    private PointValue pointValue;

    public TicTacToePlayer(String name, PointValue value) {
        this.setName(name);
        this.updateScore(0);
        this.setTeam(value);
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
}
