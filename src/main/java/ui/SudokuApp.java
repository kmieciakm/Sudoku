package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle langBoundle = ResourceBundle.getBundle("Lang");
        Locale.setDefault(new Locale("en","EN"));

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Sudoku.fxml"), langBoundle);
        Scene scene = new Scene(root);

        stage.setTitle("SudokuGame");
        stage.setScene(scene);
        stage.show();
    }
}
