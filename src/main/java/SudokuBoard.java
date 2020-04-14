import java.lang.Math;
import java.util.Arrays;

public class SudokuBoard {

    public static final int sudokuDimension = 9;
    private SudokuField[][] board = new SudokuField[sudokuDimension][sudokuDimension];
    private SudokuSolver sudokuSolver;

    SudokuBoard(SudokuSolver solver) {
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
        SudokuField[] row = board[y].clone();
        return new SudokuStructure(row);
    }

    public SudokuStructure getColumn(int x) {
        SudokuField[] column = new SudokuField[sudokuDimension];
        for (int i = 0; i < sudokuDimension; i++) {
            column[i] = new SudokuField(this.get(i, x));
        }
        return new SudokuStructure(column);
    }

    public SudokuStructure getBox(int x, int y) {
        int squareSize = 3;
        int xboundary = (int) Math.floor(x / squareSize) * squareSize;
        int yboundary = (int) Math.floor(y / squareSize) * squareSize;
        SudokuField[] boxArray = new SudokuField[9];
        int i = 0;

        for (int xbox = xboundary; xbox < xboundary + squareSize; xbox++) {
            for (int ybox = yboundary; ybox < yboundary + squareSize; ybox++) {
                boxArray[i] = new SudokuField(this.get(xbox, ybox));
                i++;
            }
        }
        return new SudokuStructure(boxArray);
    }

    /**
     *  Check if Sudoku board follows the rules, is correctly solved.
     *
     * @return Boolean that specifies correctness of board
     */
    public boolean checkBoard() {
        int[] occurrenceCounter = new int [sudokuDimension + 1];

        // check row correctness
        for (int x = 0; x < sudokuDimension; x++) {
            Arrays.fill(occurrenceCounter, 0);
            for (int y = 0; y < sudokuDimension; y++) {
                occurrenceCounter[this.get(x, y)]++;
            }
            for (int i = 0; i < sudokuDimension; i++) {
                if (occurrenceCounter[i] > 1) {
                    return false;
                }
            }
        }

        // check columns correctness
        for (int y = 0; y < sudokuDimension; y++) {
            Arrays.fill(occurrenceCounter, 0);
            for (int x = 0; x < sudokuDimension; x++) {
                occurrenceCounter[this.get(x, y)]++;
            }
            for (int i = 0; i < sudokuDimension; i++) {
                if (occurrenceCounter[i] > 1) {
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
                        occurrenceCounter[board[xinternal][yinternal].getFieldValue()]++;
                    }
                }
                for (int i = 0; i < sudokuDimension; i++) {
                    if (occurrenceCounter[i] > 1) {
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
        if (getRow(rowId).verify()
                && getColumn(columnId).verify()
                && getBox(rowId, columnId).verify()) {
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
                if (this.get(rowId, columnId) != objBoard.get(rowId, columnId)) {
                    return false;
                }
            }
        }
        return true;
    }

}
