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
    void SudokuBoardDaoFactory_TryCreateJsonDao_Created() {
        assertTrue(SudokuBoardDaoFactory.getJsonDao(tempDir + "/sudokuTest.json").getClass()
                == JsonSudokuBoardDao.class);
    }

    @Test
    void SudokuBoardDaoFactory_TryCreateFileDao_Created() {
        assertTrue(SudokuBoardDaoFactory.getFileDao(tempDir + "/sudokuTest.bin").getClass()
                == FileSudokuBoardDao.class);
    }

    @Test
    public void SudokuBoardDao_WriteToJson_FileCreated() {
        String filePath = tempDir + "/sudokuTest.json";
        JsonSudokuBoardDao dao = new JsonSudokuBoardDao(filePath);
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        dao.write(board);
        assertEquals(true, new File(filePath).isFile());
    }

    @Test
    public void SudokuBoardDao_ReadFromJson_SudokuBoardCreated() {
        String filePath = tempDir + "/sudokuCreateTest.json";
        JsonSudokuBoardDao dao = new JsonSudokuBoardDao(filePath);
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

        board.solveGame();
        dao.write(board);

        SudokuBoard boardFromFile = dao.read();

        assertEquals(true, board.equals(boardFromFile));
    }

    @Test
    public void SudokuBoardDao_WriteToFile_FileCreated() {
        String filePath = tempDir + "/sudokuTest.bin";
        FileSudokuBoardDao dao = new FileSudokuBoardDao(filePath);
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        dao.write(board);
        assertEquals(true, new File(filePath).isFile());
    }

    @Test
    public void SudokuBoardDao_ReadFromFile_SudokuBoardCreated() {
        String filePath = tempDir + "/sudokuCreateTest.bin";
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
