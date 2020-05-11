package ui;

import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

public class SudokuController {

    SudokuBoard board;

    @FXML
    private GridPane grid;

    @FXML
    private ChoiceBox<DifficultyLevels> difficultyBox;

    private List<Label> labels;

    @FXML
    public void initialize() {
        // fill level difficulty ChoiceBox
        difficultyBox.getItems().setAll(DifficultyLevels.values());
        difficultyBox.getSelectionModel().selectFirst();
        bindGrid();
    }

    @FXML
    public void startGame() {
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();

        DifficultyLevels level = difficultyBox.getSelectionModel().getSelectedItem();
        BoardGenarator.generateBoard(board, level);
        drawSudoku();
    }

    private void bindGrid() {
        grid.getChildren().clear();
        for (int rowId = 0; rowId < grid.getRowCount(); rowId++) {
            for (int columnId = 0; columnId < grid.getColumnCount(); columnId++) {
                // add label
                Label label = new Label("");
                // label style
                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                label.setAlignment(Pos.CENTER);
                label.setBorder(new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                null, new BorderWidths(1))));
                if ((rowId / 3) % 2 == 0 && ((columnId / 3) % 2 == 0)
                        || (rowId / 3) % 2 != 0 && ((columnId / 3) % 2 != 0)) {
                    label.setStyle("-fx-background-color: LIGHTCYAN");
                }
                // add to grid
                grid.add(label, columnId, rowId);
            }
        }
    }

    private void drawSudoku() {
        for (int rowId = 0; rowId < grid.getRowCount(); rowId++) {
            for (int columnId = 0; columnId < grid.getColumnCount(); columnId++) {
                String value = "";
                if (board.get(rowId, columnId) != 0) {
                    value = String.valueOf(board.get(rowId, columnId));
                }
                Node node = getNodeFromGridPane(grid, columnId, rowId);
                if (node instanceof Label) {
                    ((Label) node).setText(value);
                }
            }
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }


}
