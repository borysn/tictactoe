package tictactoe.game.board;

public class TicTacToePoint implements BoardPoint {
    private String value;
    private int xPos;
    private int yPos;

    public TicTacToePoint(int xPos, int yPos) {
        this.value = TicTacToePointValue.EMPTY.value();
        this.setPosition(xPos, yPos);
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
