package dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoTests {

    static String tempDir = "Temp";

    @BeforeAll
    static void Initialize() {
        boolean file = new File(tempDir).mkdirs();;
    }

    @Test
    void SudokuBoardDaoFactory_TryCreateDao_Created() {
        assertTrue(SudokuBoardDaoFactory.getFileDao(tempDir + "/sudokuTest.json").getClass()
                == FileSudokuBoardDao.class);
    }

    @Test
    public void SudokuBoardDao_WriteToFile_FileCreated() {
        String filePath = tempDir + "/sudokuTest.json";
        FileSudokuBoardDao dao = new FileSudokuBoardDao(filePath);
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        dao.write(board);
        assertEquals(true, new File(filePath).isFile());
    }

    @Test
    public void SudokuBoardDao_ReadFromFile_SudokuBoardCreated() {
        String filePath = tempDir + "/sudokuCreateTest.json";
        FileSudokuBoardDao dao = new FileSudokuBoardDao(filePath);
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

        board.solveGame();
        dao.write(board);

        SudokuBoard boardFromFile = dao.read();

        assertEquals(true, board.equals(boardFromFile));
    }

    @AfterAll
    static void Finalize() {
        deleteDirectory(new File(tempDir));
    }

    static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
