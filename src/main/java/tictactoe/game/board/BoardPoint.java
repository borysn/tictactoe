package tictactoe.game.board;

public interface BoardPoint {
    void setPosition(int x, int y);
    void setValue(String value);
    int getXPos();
    int getYPos();
    String getValue();
}
