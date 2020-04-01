import java.util.Arrays;

public class SudokuBox implements SudokuStructure {

    private SudokuField[] boxArray;

    SudokuBox(SudokuField[] newBoxArray) {
        boxArray = newBoxArray.clone();
    }

    public boolean verify() {
        int[] occurrenceCounter = new int[SudokuBoard.sudokuDimension + 1];

        Arrays.fill(occurrenceCounter, 0);

        for (int i = 0; i < SudokuBoard.sudokuDimension; i++) {
            occurrenceCounter[boxArray[i].getFieldValue()]++;
        }

        for (int number = 1; number <= SudokuBoard.sudokuDimension; number++) {
            if (occurrenceCounter[number] > 1) {
                return false;
            }
        }

        return true;
    }
}
