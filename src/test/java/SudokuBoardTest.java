import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    @Test
    public void SudokuBoard_FillBoardFunction_LayoutIsCorrect() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        assertEquals(true, board.checkBoard());
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
        SudokuBoard sudokuTwo = sudokuOne;
        sudokuOne.solveGame();
        assertEquals(true, sudokuOne.equals(sudokuTwo));
    }

    @Test
    public void SudokuColumn_AddedWrongColumn_VerifyFalse(){
        SudokuField[] columnArray = new SudokuField[SudokuBoard.sudokuDimension];
        Arrays.fill(columnArray, new SudokuField(1));
        SudokuColumn wrongColumn = new SudokuColumn(columnArray);

        assertEquals(false, wrongColumn.verify());
    }

    @Test
    public void SudokuColumn_CorrectColumn_VerifyTrue(){
        SudokuColumn wrongColumn = new SudokuColumn( new SudokuField[] {
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)
        });

        assertEquals(true, wrongColumn.verify());
    }

    @Test
    public void SudokuRow_AddedWrongRow_VerifyFalse(){
        SudokuField[] rowArray = new SudokuField[SudokuBoard.sudokuDimension];
        Arrays.fill(rowArray, new SudokuField(1));
        SudokuColumn wrongRow = new SudokuColumn(rowArray);

        assertEquals(false, wrongRow.verify());
    }


}
