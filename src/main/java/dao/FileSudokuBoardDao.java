package dao;

import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

public class FileSudokuBoardDao implements Dao, AutoCloseable {

    public String fileName;

    public FileSudokuBoardDao(String newFilename) {
        fileName = newFilename;
    }

    @Override
    public SudokuBoard read() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(SudokuBoard.class, new SudokuBoardDeserializer())
                .create();
        SudokuBoard board = null;
        try (Reader reader = new FileReader(this.fileName)) {
            board = gson.fromJson(reader, SudokuBoard.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return board;
    }

    @Override
    public void write(Object obj) {
        SudokuBoard board = (SudokuBoard) obj;
        try (FileWriter writer = new FileWriter(this.fileName)) {
            new Gson().toJson(board, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SudokuBoardDeserializer implements JsonDeserializer<SudokuBoard> {
        @Override
        public SudokuBoard deserialize(
                JsonElement json,
                Type typeOfT,
                JsonDeserializationContext ctx)
                throws JsonParseException {
            SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
            JsonObject obj = json.getAsJsonObject();
            for (int i = 0; i < SudokuBoard.sudokuDimension; i++) {
                for (int j = 0; j < SudokuBoard.sudokuDimension; j++) {
                    var row = obj.get("board").getAsJsonArray().get(i);
                    board.set(i, j, row.getAsJsonArray().get(j)
                            .getAsJsonObject().get("value")
                            .getAsJsonPrimitive().getAsInt());
                }
            }
            return board;
        }
    }

    @Override
    public void close() throws Exception {

    }
}
