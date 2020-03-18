import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    @Test
    public void SudokuBoard_Initializer_AllValuesEqualsZero() {
        SudokuBoard board = new SudokuBoard();
        for (int[] row : board.getBoard()) {
            for (int valueInRow: row) {
                assertEquals(valueInRow, 0);
            }
        }
    }

    @Test
    public void SudokuBoard_FillBoardWithOneNumber_LayoutIsInCorrect() {
        SudokuBoard board = new SudokuBoard();
        board.setBoard(new int[][]{
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1}
        });
        assertEquals(false, board.isLayoutCorrect());
    }

    @Test
    public void SudokuBoard_FillBoardCorrect_LayoutCorrect() {
        SudokuBoard board = new SudokuBoard();
        board.setBoard(new int[][]{
                {4,5,2,3,9,1,8,7,6},
                {3,1,8,6,7,5,2,9,4},
                {6,7,9,4,2,8,3,1,5},
                {8,3,1,5,6,4,7,2,9},
                {2,4,5,9,8,7,1,6,3},
                {9,6,7,2,1,3,5,4,8},
                {7,9,6,8,5,2,4,3,1},
                {1,8,3,7,4,9,6,5,2},
                {5,2,4,1,3,6,9,8,7}
        });
        assertEquals(true, board.isLayoutCorrect());
    }

    @Test
    public void SudokuBoard_FillBoardFunction_LayoutIsCorrect() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();
        assertEquals(true, board.isLayoutCorrect());
    }

    @Test
    public void SudokuBoard_GenerateTwoBoards_BoardsDiffer() {
        SudokuBoard sudokuOne = new SudokuBoard();
        SudokuBoard sudokuTwo = new SudokuBoard();
        sudokuOne.fillBoard();
        sudokuTwo.fillBoard();
        assertEquals(false, Objects.equals(sudokuOne.getBoard(), sudokuTwo.getBoard()));
    }

    @Test
    public void SudokuBoard_ColumnIsValid_ReturnedTrue() {
        SudokuBoard board = new SudokuBoard();
        board.setBoard(new int[][]{
                {0,0,0,0,0,0,0,0,0},
                {1,1,8,6,7,5,2,9,3},
                {0,7,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0},
                {0,5,0,0,0,0,0,0,0},
                {0,6,0,0,0,0,0,0,0},
                {0,9,0,0,0,0,0,0,0},
                {0,8,0,0,0,0,0,0,0},
                {0,2,4,0,0,0,0,0,0}
        });
        assertEquals(true, board.isValidColumn(1));
    }

    @Test
    public void SudokuBoard_ColumnWrong_ReturnedFalse() {
        SudokuBoard board = new SudokuBoard();
        board.setBoard(new int[][]{
                {0,1,0,0,0,0,0,0,0},
                {4,1,8,6,7,5,2,9,3},
                {0,7,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0},
                {0,5,0,0,0,0,0,0,0},
                {0,6,0,0,0,0,0,0,0},
                {0,9,0,0,0,0,0,0,0},
                {0,8,0,0,0,0,0,0,0},
                {0,2,4,0,0,0,0,0,0}
        });
        assertEquals(false, board.isValidColumn(1));
    }

    @Test
    public void SudokuBoard_RowIsValid_ReturnedTrue() {
        SudokuBoard board = new SudokuBoard();
        board.setBoard(new int[][]{
                {0,3,0,0,0,0,0,0,0},
                {4,1,8,6,7,5,2,9,3},
                {0,3,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0},
                {0,3,4,0,0,0,0,0,0}
        });
        assertEquals(true, board.isValidRow(1));
    }

    @Test
    public void SudokuBoard_RowWrong_ReturnedFalse() {
        SudokuBoard board = new SudokuBoard();
        board.setBoard(new int[][]{
                {0,4,0,0,0,0,0,0,0},
                {1,1,8,6,7,5,2,9,3},
                {0,7,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0},
                {0,5,0,0,0,0,0,0,0},
                {0,6,0,0,0,0,0,0,0},
                {0,9,0,0,0,0,0,0,0},
                {0,8,0,0,0,0,0,0,0},
                {0,2,4,0,0,0,0,0,0}
        });
        assertEquals(false, board.isValidRow(1));
    }

    @Test
    public void SudokuBoard_BoxIsValid_ReturnedTrue() {
        SudokuBoard board = new SudokuBoard();
        board.setBoard(new int[][]{
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {1,2,3,0,0,0,0,0,0},
                {4,5,6,0,0,0,0,0,0},
                {7,8,9,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        });
        assertEquals(true, board.isValidBox(4,2));
    }

    @Test
    public void SudokuBoard_BoxIsZeros_ReturnedTrue() {
        SudokuBoard board = new SudokuBoard();
        board.setBoard(new int[][]{
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        });
        assertEquals(true, board.isValidBox(4,2));
    }

    @Test
    public void SudokuBoard_BoxWrong_ReturnedFalse() {
        SudokuBoard board = new SudokuBoard();
        board.setBoard(new int[][]{
                {0,0,0,0,1,0,0,0,0},
                {0,0,0,0,1,0,0,0,0},
                {0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        });
        assertEquals(false, board.isValidBox(2,4));
    }

}
