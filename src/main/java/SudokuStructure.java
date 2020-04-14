import java.util.Arrays;

public class SudokuStructure {
    protected SudokuField[] values;

    SudokuStructure(SudokuField[] values) {
        this.values = values.clone();
    }

    SudokuStructure() {
        values = new SudokuField[SudokuBoard.sudokuDimension];
        for (int i = 0; i < values.length; i++) {
            values[i] = new SudokuField();
        }
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        SudokuStructure objStructure = (SudokuStructure) obj;

        for (int i = 0; i < values.length; i++) {
            if (objStructure.values[i].getFieldValue() != this.values[i].getFieldValue()) {
                return false;
            }
        }

        return true;
    }

}
