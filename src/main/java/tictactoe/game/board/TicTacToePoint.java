package tictactoe.game.board;

import java.util.Objects;

public class TicTacToePoint implements BoardPoint {
    private String value;
    private int xPos;
    private int yPos;

    public TicTacToePoint(int xPos, int yPos) {
        this.value = TicTacToePointValue.EMPTY.value();
        this.setPosition(xPos, yPos);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + this.xPos;
        result = 31 * result + this.yPos;
        result = 31 * result + this.value.hashCode();
        return 31 * result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o != null && o instanceof TicTacToePoint) {
            TicTacToePoint point = (TicTacToePoint) o;
            return this.xPos == point.getXPos() && this.yPos == point.getYPos();
        }

        return false;
    }

    @Override
    public void setPosition(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getXPos() {
        return this.xPos;
    }

    @Override
    public int getYPos() {
        return this.yPos;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
