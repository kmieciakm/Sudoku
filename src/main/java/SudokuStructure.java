import java.util.Arrays;

public class SudokuStructure {
    protected SudokuField[] values;

    SudokuStructure(SudokuField[] values) {
        this.values = values;
    }

    public boolean verify() {
        int[] occurrenceCounter = new int[SudokuBoard.sudokuDimension + 1];

        Arrays.fill(occurrenceCounter, 0);
        for (int i = 0; i < SudokuBoard.sudokuDimension; i++) {
            occurrenceCounter[values[i].getFieldValue()]++;
        }

        for (int number = 1; number <= SudokuBoard.sudokuDimension; number++) {
            if (occurrenceCounter[number] > 1) {
                return false;
            }
        }

        return true;
    }
}
