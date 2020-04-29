package dao;

import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoTests {

    @Test void SudokuBoardDaoFactory_TryCreateDao_Created() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertTrue(factory.getFileDao("C://Temp/sudokuTest.json").getClass() == FileSudokuBoardDao.class);
    }

    @Test
    public void SudokuBoardDao_WriteToFile_FileCreated() {
        String filePath = "C://Temp/sudokuTest.json";
        FileSudokuBoardDao dao = new FileSudokuBoardDao(filePath);
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        dao.write(board);
        assertEquals(true, new File(filePath).isFile());
    }

    @Test
    public void SudokuBoardDao_ReadFromFile_SudokuBoardCreated() {
        String filePath = "C://Temp/sudokuCreateTest.json";
        FileSudokuBoardDao dao = new FileSudokuBoardDao(filePath);
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

        board.solveGame();
        dao.write(board);

        SudokuBoard boardFromFile = dao.read();

        assertEquals(true, board.equals(boardFromFile));
    }

}
