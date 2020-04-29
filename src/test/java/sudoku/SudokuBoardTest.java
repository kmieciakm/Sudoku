package sudoku;

import solver.BacktrackingSudokuSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    @Test
    public void SudokuBoard_FillBoardFunction_LayoutIsCorrect() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        assertEquals(true, board.checkBoard());
    }

    @Test
    public void SudokBoard_IsLayoutAllowed_True() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.set(3,5,2);
        board.set(0,0,1);
        assertEquals(true, board.isLayoutAllowed(0,0));
    }

    @Test
    public void SudokuBoard_GenerateTwoBoards_BoardsDiffer() {
        SudokuBoard sudokuOne = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuTwo = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuOne.solveGame();
        sudokuTwo.solveGame();
        assertEquals(false, sudokuOne.equals(sudokuTwo));
    }

    @Test
    public void SudokuBoard_PassTwoEqualsBoards_BoardsComparisonCorrect() {
        SudokuBoard sudokuOne = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuTwo = new SudokuBoard(new BacktrackingSudokuSolver());
        assertEquals(true, sudokuOne.equals(sudokuTwo));
    }

    @Test
    public void SudokuBoard_SetValue_ValueSaved() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        int value = 5;
        sudokuBoard.set(1,1, value);
        assertEquals(value, sudokuBoard.get(1,1));
        assertNotEquals(value, sudokuBoard.get(1,2));
        assertNotEquals(value, sudokuBoard.get(2,1));
    }

}
