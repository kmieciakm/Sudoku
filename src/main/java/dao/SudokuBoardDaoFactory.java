package dao;

import sudoku.SudokuBoard;

public class SudokuBoardDaoFactory {
    private SudokuBoardDaoFactory() {}

    public static Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static Dao<SudokuBoard> getJsonDao(String fileName) {
        return new JsonSudokuBoardDao(fileName);
    }
}
