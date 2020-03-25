import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    public static final int boardDimension = 9;

    private int[][] board = new int[boardDimension][boardDimension];

    /**
     *  Returns Sudoku board copy.
     *
     * @return Copy of the board
     */
    public int[][] getBoard() {
        int[][] boardCopy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            boardCopy[i] = board[i].clone();
        }
        return boardCopy;
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public int solveGame() {
        return 0;
    }


    /**
     *  Check if Sudoku board follows the rules, is correctly solved.
     *
     * @return Boolean that specifies correctness of board
     */
    public boolean isLayoutCorrect() {
        int[] occurrenceCounter = new int [boardDimension];

        // check row correctness
        for (int x = 0; x < boardDimension; x++) {
            Arrays.fill(occurrenceCounter, 0);
            for (int y = 0; y < boardDimension; y++) {
                occurrenceCounter[board[x][y] - 1]++;
            }
            for (int value : occurrenceCounter) {
                if (value > 1) {
                    return false;
                }
            }
        }

        // check columns correctness
        for (int y = 0; y < boardDimension; y++) {
            Arrays.fill(occurrenceCounter, 0);
            for (int x = 0; x < boardDimension; x++) {
                occurrenceCounter[board[x][y] - 1]++;
            }
            for (int value : occurrenceCounter) {
                if (value > 1) {
                    return false;
                }
            }
        }

        // check squares correctness
        int squareSize = 3;
        for (int x = 0; x < boardDimension; x += squareSize) {
            for (int y = 0; y < boardDimension; y += squareSize) {
                Arrays.fill(occurrenceCounter, 0);
                for (int xinternal = x; xinternal < squareSize; xinternal++) {
                    for (int yinternal = y; yinternal < squareSize; yinternal++) {
                        occurrenceCounter[board[xinternal][yinternal] - 1]++;
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
        if (isValidRow(rowId) && isValidColumn(columnId) && isValidBox(rowId, columnId)) {
            return true;
        }
        return false;
    }

    /**
     *  Check if specific column in Sudoku board is valid, ignore all 0's.
     *
     * @param columnId  The number of column in board to check (starts from 0)
     * @return Boolean that specifies correctness of column
     * @throws IndexOutOfBoundsException When param not in range
     */
    private boolean isValidColumn(int columnId) {
        if (columnId >= boardDimension || columnId < 0) {
            throw new IndexOutOfBoundsException("ColumnId: " + columnId + " not allowed");
        }

        int[] occurrenceCounter = new int[boardDimension + 1];

        Arrays.fill(occurrenceCounter, 0);
        for (int x = 0; x < boardDimension; x++) {
            occurrenceCounter[board[x][columnId]]++;
        }

        for (int number = 1; number <= boardDimension; number++) {
            if (occurrenceCounter[number] > 1) {
                return false;
            }
        }

        return true;
    }

    /**
     *  Check if specific row in Sudoku board is valid, ignore all 0's.
     *
     * @param rowId The number of row in board to check (starts from 0)
     * @return Boolean that specifies correctness of row
     * @throws IndexOutOfBoundsException When param not in range
     */
    private boolean isValidRow(int rowId) {
        if (rowId >= boardDimension || rowId < 0) {
            throw new IndexOutOfBoundsException("ColumnId: " + rowId + " not allowed");
        }

        int[] occurrenceCounter = new int[boardDimension + 1];

        Arrays.fill(occurrenceCounter, 0);
        for (int y = 0; y < boardDimension; y++) {
            occurrenceCounter[board[rowId][y]]++;
        }

        for (int number = 1; number <= boardDimension; number++) {
            if (occurrenceCounter[number] > 1) {
                return false;
            }
        }

        return true;
    }

    /**
     *  Check if specific square box in Sudoku board is valid, ignore all 0's.
     *
     * @param rowId The row number, used to find corresponding square box
     * @param columnId The column number, used to find corresponding square box
     * @return Boolean that specifies correctness of box
     * @throws IndexOutOfBoundsException When param not in range
     */
    private boolean isValidBox(int rowId, int columnId) {
        if (rowId >= boardDimension || rowId < 0) {
            throw new IndexOutOfBoundsException("RowId: " + rowId + " not allowed");
        }
        if (columnId >= boardDimension || columnId < 0) {
            throw new IndexOutOfBoundsException("ColumnId: " + columnId + " not allowed");
        }

        int squareSize = 3;
        int xboundary = (int) Math.floor(rowId / squareSize) * squareSize;
        int yboundary = (int) Math.floor(columnId / squareSize) * squareSize;
        int[] occurrenceCounter = new int[boardDimension + 1];

        Arrays.fill(occurrenceCounter, 0);
        for (int x = xboundary; x < xboundary + squareSize; x++) {
            for (int y = yboundary; y < yboundary + squareSize; y++) {
                occurrenceCounter[board[x][y]]++;
            }
        }

        for (int number = 1; number <= boardDimension; number++) {
            if (occurrenceCounter[number] > 1) {
                return false;
            }
        }

        return true;
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
