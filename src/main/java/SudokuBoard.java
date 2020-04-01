import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard {

    public static final int sudokuDimension = 9;
    private SudokuField[][] board = new SudokuField[sudokuDimension][sudokuDimension];
    private SudokuSolver sudokuSolver;

    SudokuBoard(){
        Arrays.fill(board, new SudokuField());
    };
    /**
     *  Returns Sudoku board copy.
     *
     * @return Copy of the board
     */
    public SudokuField[][] getBoard() {
        SudokuField[][] boardCopy = new SudokuField[board.length][];
        for (int i = 0; i < board.length; i++) {
            boardCopy[i] = board[i].clone();
        }
        return boardCopy;
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

    SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
    }

    SudokuRow getRow(int y) {
        SudokuField[] row = board[y].clone();
        return new SudokuRow(row);
    }

    SudokuColumn getColumn(int x) {
        SudokuField[] column = new SudokuField[sudokuDimension];
        for(int i = 0; i < sudokuDimension; i++){
            column[i] = board[i][x];
        }
        return new SudokuColumn(column);
    }

    SudokuBox getBox(int x, int y) {
        int squareSize = 3;
        int xboundary = (int) Math.floor(x / squareSize) * squareSize;
        int yboundary = (int) Math.floor(y / squareSize) * squareSize;
        List<SudokuField> box = new ArrayList<SudokuField>();


        for (int xBox = xboundary; xBox < xboundary + squareSize; xBox++) {
            for (int yBox = yboundary; yBox < yboundary + squareSize; yBox++) {
                box.add(board[x][y]);
            }
        }
        SudokuField[] boxArray = new SudokuField[box.size()];
        box.toArray(boxArray);
        return new SudokuBox(boxArray);
    }

    /**
     *  Check if Sudoku board follows the rules, is correctly solved.
     *
     * @return Boolean that specifies correctness of board
     */
    public boolean checkBoard() {
        int[] occurrenceCounter = new int [sudokuDimension];

        // check row correctness
        for (int x = 0; x < sudokuDimension; x++) {
            Arrays.fill(occurrenceCounter, 0);
            for (int y = 0; y < sudokuDimension; y++) {
                occurrenceCounter[board[x][y].getFieldValue() - 1]++;
            }
            for (int value : occurrenceCounter) {
                if (value > 1) {
                    return false;
                }
            }
        }

        // check columns correctness
        for (int y = 0; y < sudokuDimension; y++) {
            Arrays.fill(occurrenceCounter, 0);
            for (int x = 0; x < sudokuDimension; x++) {
                occurrenceCounter[board[x][y].getFieldValue() - 1]++;
            }
            for (int value : occurrenceCounter) {
                if (value > 1) {
                    return false;
                }
            }
        }

        // check squares correctness
        int squareSize = 3;
        for (int x = 0; x < sudokuDimension; x += squareSize) {
            for (int y = 0; y < sudokuDimension; y += squareSize) {
                Arrays.fill(occurrenceCounter, 0);
                for (int xinternal = x; xinternal < squareSize; xinternal++) {
                    for (int yinternal = y; yinternal < squareSize; yinternal++) {
                        occurrenceCounter[board[xinternal][yinternal].getFieldValue() - 1]++;
                    }
                }
                for (int value : occurrenceCounter) {
                    if (value > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     *  Check if current inserted numbers to board follows the rules.
     *
     * @param rowId index of a row
     * @param columnId index of a column
     * @return Boolean that specifies correctness of board
     */
    public boolean isLayoutAllowed(int rowId, int columnId) {
        if (getRow(rowId).verify() && getColumn(columnId).verify() && getBox(rowId, columnId).verify()) {
            return true;
        }
        return false;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        SudokuBoard objBoard = (SudokuBoard) obj;

        for (int rowId = 0; rowId < board.length; rowId++) {
            for (int columnId = 0; columnId < board[rowId].length; columnId++) {
                if (board[rowId][columnId] != objBoard.getBoard()[rowId][columnId]) {
                    return false;
                }
            }
        }
        return true;
    }
}
