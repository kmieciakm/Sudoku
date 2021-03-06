package sudoku;

import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import solver.SudokuSolver;

public class SudokuBoard implements Serializable, Cloneable {

    public static final int sudokuDimension = 9;
    private SudokuField[][] board = new SudokuField[sudokuDimension][sudokuDimension];
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        this();
        sudokuSolver = solver;
    }

    SudokuBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public SudokuStructure getRow(int y) {
        List<SudokuField> row = Arrays.asList(board[y].clone());
        return new SudokuStructure(row);
    }

    public SudokuStructure getColumn(int x) {
        List<SudokuField> column = new ArrayList<>();
        for (int i = 0; i < sudokuDimension; i++) {
            column.add(new SudokuField(this.get(i, x)));
        }
        return new SudokuStructure(column);
    }

    public SudokuStructure getBox(int x, int y) {
        int squareSize = 3;
        int xboundary = (int) Math.floor(x / squareSize) * squareSize;
        int yboundary = (int) Math.floor(y / squareSize) * squareSize;
        List<SudokuField> boxList = new ArrayList<>();

        for (int xbox = xboundary; xbox < xboundary + squareSize; xbox++) {
            for (int ybox = yboundary; ybox < yboundary + squareSize; ybox++) {
                boxList.add(new SudokuField(this.get(xbox, ybox)));
            }
        }
        return new SudokuStructure(boxList);
    }

    public boolean checkBoard() {
        for (int i = 0; i < sudokuDimension; i++) {
            if (!getRow(i).check() || !getColumn(i).check()) {
                return false;
            }
        }
        return true;
    }

    public boolean isLayoutAllowed(int rowId, int columnId) {
        return getRow(rowId).verify()
                && getColumn(columnId).verify()
                && getBox(rowId, columnId).verify();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard objBoard = (SudokuBoard) o;
        for (int rowId = 0; rowId < board.length; rowId++) {
            for (int columnId = 0; columnId < board[rowId].length; columnId++) {
                if (this.get(rowId, columnId) != objBoard.get(rowId, columnId)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        return "SudokuBoard{"
                + "board="
                + Arrays.toString(board);
    }

    @Override
    protected SudokuBoard clone() {
        SudokuBoard newBoard = new SudokuBoard(this.sudokuSolver.getCopy());
        for (int rowId = 0; rowId < board.length; rowId++) {
            for (int columnId = 0; columnId < board[rowId].length; columnId++) {
                newBoard.set(rowId, columnId, this.get(rowId, columnId));
            }
        }
        return newBoard;
    }
}
