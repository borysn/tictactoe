package tictactoe.game.board;

import tictactoe.game.engine.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class TicTacToeBoard implements Board {

    private List<List<TicTacToePoint>> gameBoard;
    private int boardSizeX;
    private int boardSizeY;

    public TicTacToeBoard() {
        this.gameBoard = new ArrayList<>();
        this.initBoard(3, 3);
    }

    @Override
    public void initBoard(int x, int y) {
        // set board size
        this.boardSizeX = x;
        this.boardSizeY = y;

        for (int i = 0; i < x; i++) {
            // new game board row
            List<TicTacToePoint> row = new ArrayList<>();
            for (int j = 0; j < y; j++) {
                TicTacToePoint point = new TicTacToePoint(j, i);
                row.add(point);
            }
            gameBoard.add(row);
        }
    }

    @Override
    public void resetBoard() {
        for (List<TicTacToePoint> row : this.gameBoard) {
            for (TicTacToePoint point : row) {
                point.setValue(TicTacToePointValue.EMPTY.value());
            }
        }
    }

    @Override
    public void setPointValue(PointValue value, Move move) {
        this.gameBoard.get(move.getY()-1).get(move.getX()-1).setValue(value.value());
    }

    @Override
    public void printBoard() {
        // print separator
        System.out.println("--------------------------");

        // print user y pos display
        System.out.println("   1  2  3");

        // print rest of game board
        for (int i = 0; i < gameBoard.size(); i++) {
            List<TicTacToePoint> row = gameBoard.get(i);

            // print x pos display
            System.out.print(i+1 + " ");

            for (int j = 0; j < row.size(); j++) {
                System.out.print("[" + row.get(j).getValue() + "]");
            }
            System.out.println();
        }

        // print separator
        System.out.println("--------------------------");
        System.out.println();
    }

    @Override
    public boolean hasWinningMove() {
        // first check rows/columns/diagonals for win
        return (this.checkRows() || this.checkColumns() || this.checkDiagonals()) == true ? true : false;
    }

    protected boolean checkRows() {
        for (List<TicTacToePoint> row : gameBoard) {
            if (this.hasWin(row)) {
                return true;
            }
        }

        return false;
    }

    protected boolean checkColumns() {
        for (int i = 0; i < this.getXSize(); i++) {
            // init column array
            List<TicTacToePoint> column = new ArrayList<>();
            for (int j = 0; j < this.getYSize(); j++) {
                // get point from pos
                TicTacToePoint point = this.gameBoard.get(j).get(i);
                column.add(point);
            }
            // check for win
            if (this.hasWin(column)) {
                return true;
            }
        }

        return false;
    }

    protected boolean checkDiagonals() {
        // init list
        List<TicTacToePoint> diagonal1 = new ArrayList<>();
        List<TicTacToePoint> diagonal2 = new ArrayList<>();

        // diagonal 1,1 to 3,3 && 3,1 to 1,3
        for (int i = 0; i < this.getXSize(); i++) {
            TicTacToePoint point = this.gameBoard.get(i).get(i);
            TicTacToePoint point2 = this.gameBoard.get(((this.getYSize()-1)-i)).get(i);
            diagonal1.add(point);
            diagonal2.add(point2);
        }
        // check for win
        if (this.hasWin(diagonal1) || this.hasWin(diagonal2)) {
            return true;
        }

        return false;
    }

    protected boolean hasWin(List<TicTacToePoint> list) {
        Predicate<TicTacToePoint> pX = p -> p.getValue().equals(TicTacToePointValue.X.value());
        Predicate<TicTacToePoint> pO = p -> p.getValue().equals(TicTacToePointValue.O.value());

        if (list.stream().allMatch(pX) || list.stream().allMatch(pO)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean hasDraw() {
        // check if all positions have been filled
        for (int i = 0; i < this.getXSize(); i++) {
            for (int j = 0; j < this.getYSize(); j++) {
                // get pos value
                String posValue = this.gameBoard.get(i).get(j).getValue();
                // if this position has an empty value, we know there is no draw yet
                if (posValue.equals(TicTacToePointValue.EMPTY.value())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<TicTacToePoint> getBoardPointList() {
        // init return
        List<TicTacToePoint> boardPointList = new ArrayList<>();
        // add points to list
        this.gameBoard.stream().forEach(list -> boardPointList.addAll(list));

        return boardPointList;
    }

    @Override
    public int getXSize() {
        return this.boardSizeX;
    }

    @Override
    public int getYSize() {
        return this.boardSizeY;
    }

    @Override
    public boolean isValidMove(Move move, Set<Move> movesMade) {
        // check if move is already made
        if (movesMade.contains(move)) {
            System.out.println("\n[Error] This position has been filled\n");
            return false;
        }

        // x value must be between 1 and game board x size (inclusive)
        if (move.getX()-1 >= 0 && move.getX()-1 < this.boardSizeX) {
            // y value must be between 1 and game board y size (inclusive)
            if (move.getY()-1 >= 0 && move.getY()-1 < this.boardSizeY) {
                // this move is valid
                return true;
            } else {
                System.out.println("\n[Error] Invalid y position\n");
            }
        } else {
            System.out.println("\n[Error] Invalid x position\n");
        }

        return false;
    }
}
