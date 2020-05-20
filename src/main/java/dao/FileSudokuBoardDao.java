package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sudoku.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    public String fileName;

    public FileSudokuBoardDao(String newFilename) {
        fileName = newFilename;
    }

    @Override
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            SudokuBoard board = (SudokuBoard) inputStream.readObject();
            System.out.println(board);
            return board;
        }
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
        try (ObjectOutputStream outputStream =
                new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(obj);
        }
    }

    @Override
    public void close() throws Exception {

    }
}
