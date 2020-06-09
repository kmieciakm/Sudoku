package ui;

import dao.FileSudokuBoardDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

public class SudokuController {

    SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    String savingPath = System.getProperty("user.dir") + "/sudoku.bin";
    ResourceBundle langBundle = ResourceBundle.getBundle("Lang");
    ResourceBundle authorsBundle = ResourceBundle.getBundle("Authors");

    @FXML private AnchorPane rootPane;

    @FXML private Label message;
    @FXML private Label authors;
    @FXML private Label difficultyLabel;

    @FXML private GridPane grid;

    @FXML private ChoiceBox<DifficultyLevels> difficultyBox;
    @FXML private ChoiceBox<Languages> languageBox;
    private Languages currentLang;

    @FXML private Button checkBtn;
    @FXML private Button solveBtn;
    @FXML private Button loadBtn;
    @FXML private Button saveBtn;
    @FXML private Button startBtn;

    @FXML
    public void initialize() {
        // fill level difficulty ChoiceBox
        difficultyBox.getItems().setAll(DifficultyLevels.values());
        difficultyBox.getSelectionModel().selectFirst();

        // add language choice box
        languageBox.getItems().setAll(Languages.values());

        languageBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                                Number number, Number number2) {
                currentLang = languageBox.getItems().get((Integer) number2);
                switchLanguage(currentLang);
            }
        });
        // populateTextFields();
        message.setText("");
        authors.setText(authorsBundle.getString("names"));

        bindToGrid(board);
    }

    private void switchLanguage(Languages lang) {
        switch (lang) {
            case PL: {
                langBundle = ResourceBundle.getBundle("Lang", new Locale("pl"));
                break;
            }
            default: {
                langBundle = ResourceBundle.getBundle("Lang", new Locale("en"));
            }
        }

        try {
            reload();
        } catch (IOException e) {
            message.setText(langBundle.getString("message_error"));
        }
        // populateTextFields();
    }

    private void populateTextFields() {
        checkBtn.setText(langBundle.getString("checkBtn"));
        solveBtn.setText(langBundle.getString("solveBtn"));
        saveBtn.setText(langBundle.getString("saveBtn"));
        loadBtn.setText(langBundle.getString("loadBtn"));
        startBtn.setText(langBundle.getString("startBtn"));
        difficultyLabel.setText(langBundle.getString("level"));
    }

    @FXML
    public void checkCorrectness() {
        if (board.checkBoard()) {
            message.setText(langBundle.getString("message_correct"));
            message.setTextFill(Color.GREEN);
        } else {
            message.setText(langBundle.getString("message_wrong"));
            message.setTextFill(Color.RED);
        }
    }

    private void reload() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Sudoku.fxml"), langBundle);
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void solveGame() {
        board.solveGame();
        drawSudoku();
    }

    @FXML
    public void startGame() {
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

    @FXML
    public void saveBoard() {
        try {
            FileSudokuBoardDao dao = new FileSudokuBoardDao(savingPath);
            dao.write(board);
            message.setText(langBundle.getString("message_save"));
            message.setTextFill(Color.GREEN);
        } catch (Exception e) {
            message.setText(langBundle.getString("message_error"));
            message.setTextFill(Color.RED);
        }
    }

    @FXML
    public void loadBoard() {
        try {
            FileSudokuBoardDao dao = new FileSudokuBoardDao(savingPath);
            board = dao.read();
            bindToGrid(board);
            drawSudoku();
            message.setText(langBundle.getString("message_load"));
            message.setTextFill(Color.GREEN);
        } catch (FileNotFoundException e) {
            message.setText(langBundle.getString("message_emptySave"));
            message.setTextFill(Color.RED);
        } catch (Exception e) {
            message.setText(langBundle.getString("message_error"));
            message.setTextFill(Color.RED);
        }
    }

}
