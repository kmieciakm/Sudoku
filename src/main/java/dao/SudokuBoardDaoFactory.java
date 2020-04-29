package dao;

public class SudokuBoardDaoFactory {
    Dao getFileDao(String fileName){
        return new FileSudokuBoardDao(fileName);
    }
}
