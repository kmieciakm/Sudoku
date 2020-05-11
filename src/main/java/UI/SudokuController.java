package UI;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

public class SudokuController {

    SudokuBoard board;

    @FXML
    private GridPane grid;

    @FXML
    public void startGame() {
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();

        drawSudoku();
    }

    private void drawSudoku() {
        clearGrid();
        for (int rowId = 0; rowId < grid.getRowCount(); rowId++) {
            for (int columnId = 0; columnId < grid.getColumnCount(); columnId++) {
                // bind label
                Label label = new Label(String.valueOf(board.get(rowId, columnId)));
                // label style
                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                label.setAlignment(Pos.CENTER);
                label.setBorder(new Border(
                        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                if((rowId / 3) % 2 == 0 && ((columnId / 3) % 2 == 0) || (rowId / 3) % 2 != 0 && ((columnId / 3) % 2 != 0)) {
                    label.setStyle("-fx-background-color: LIGHTCYAN");
                }
                // add to grid
                grid.add(label, columnId, rowId);
            }
        }
    }

    private void clearGrid() {
        grid.getChildren().clear();
    }

}
