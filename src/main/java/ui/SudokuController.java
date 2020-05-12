package ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

public class SudokuController {

    SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

    @FXML
    private Label massage;

    @FXML
    private GridPane grid;

    @FXML
    private ChoiceBox<DifficultyLevels> difficultyBox;

    @FXML
    public void initialize() {
        // fill level difficulty ChoiceBox
        difficultyBox.getItems().setAll(DifficultyLevels.values());
        difficultyBox.getSelectionModel().selectFirst();

        massage.setText("");

        bindToGrid(board);
    }

    @FXML
    public void checkCorrectness() {
        if (board.checkBoard()) {
            massage.setText("Correct");
            massage.setTextFill(Color.GREEN);
        } else {
            massage.setText("Wrong");
            massage.setTextFill(Color.RED);
        }
    }

    @FXML
    public void solveGame() {
        board.solveGame();
        drawSudoku();
    }

    @FXML
    public void startGame() {
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        DifficultyLevels level = difficultyBox.getSelectionModel().getSelectedItem();
        BoardGenarator.generateBoard(board, level);

        drawSudoku();
    }

    private void bindToGrid(SudokuBoard board) {
        grid.getChildren().clear();
        for (int rowId = 0; rowId < grid.getRowCount(); rowId++) {
            for (int columnId = 0; columnId < grid.getColumnCount(); columnId++) {
                FieldView fieldView = new FieldView(rowId, columnId, board);
                grid.add(fieldView.getTextFiled(), columnId, rowId);
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
                if (node instanceof TextField) {
                    ((TextField) node).setText(value);
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
