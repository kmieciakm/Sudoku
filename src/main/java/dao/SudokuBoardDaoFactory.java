package dao;

import sudoku.SudokuBoard;

public class SudokuBoardDaoFactory {
    public static Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
    private SudokuBoardDaoFactory() {};
}
