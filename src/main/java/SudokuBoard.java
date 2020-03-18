import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    static final int boardDimension = 9;

    private int[][] board = new int[boardDimension][boardDimension];

    /**
     *  Returns Sudoku board.
     *
     * @return Board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     *  Sets Sudoku board.
     *
     * @param newBoard The board to be updated
     */
    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }

    /**
     *  Generate Sudoku board.
     *
     * @return Boolean that specifies if board is fully filled
     */
    public boolean fillBoard() {
        int emptyFiledX = -1;
        int emptyFiledY = -1;
        boolean isEmpty = true;

        outerloop: for (int x = 0; x < boardDimension; x++) {
            for (int y = 0; y < boardDimension; y++) {
                if (board[x][y] == 0) {
                    emptyFiledX = x;
                    emptyFiledY = y;
                    isEmpty = false;
                    break outerloop;
                }
            }
        }

        // no empty space left
        if (isEmpty) {
            return true;
        }

        List<Integer> numbers = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }};
        Collections.shuffle(numbers);

        for (int numberIndex = 0; numberIndex < boardDimension; numberIndex++) {
            board[emptyFiledX][emptyFiledY] = numbers.get(numberIndex);
            if (isValidRow(emptyFiledX)
                    && isValidColumn(emptyFiledY)
                    && isValidBox(emptyFiledX, emptyFiledY)) {
                if (fillBoard()) {
                    return true;
                }
            } else {
                board[emptyFiledX][emptyFiledY] = 0;
            }
        }

        return false;
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
     *  Check if specific column in Sudoku board is valid, ignore all 0's.
     *
     * @param columnId  The number of column in board to check (starts from 0)
     * @return Boolean that specifies correctness of column
     * @throws IndexOutOfBoundsException When param not in range
     */
    public boolean isValidColumn(int columnId) {
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
    public boolean isValidRow(int rowId) {
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
    public boolean isValidBox(int rowId, int columnId) {
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

}
