import org.junit.jupiter.api.Test;
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
        assertEquals(false, sudokuOne.equals(sudokuTwo));
    }

    @Test
    public void SudokuBoard_PassTwoEqualsBoards_BoardsComparisonCorrect() {
        SudokuBoard sudokuOne = new SudokuBoard();
        SudokuBoard sudokuTwo = sudokuOne;
        sudokuOne.fillBoard();
        assertEquals(true, sudokuOne.equals(sudokuTwo));
    }

}
