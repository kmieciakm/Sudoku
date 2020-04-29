package sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuStructure {
    private List<SudokuField> values = Arrays.asList(
        new SudokuField[SudokuBoard.sudokuDimension]
    );

    public SudokuStructure(List<SudokuField> values) {
        if (this.values.size() != values.size()) {
            throw new IllegalArgumentException(
                "Sudoku structure must contain exactly " + values.size() + "elements"
            );
        }
        Collections.copy(this.values, values);
    }

    public SudokuStructure() {
        for (int i = 0; i < values.size(); i++) {
            values.set(i, new SudokuField());
        }
    }

    public boolean verify() {
        int[] occurrenceCounter = new int[SudokuBoard.sudokuDimension + 1];

        Arrays.fill(occurrenceCounter, 0);
        for (int i = 0; i < SudokuBoard.sudokuDimension; i++) {
            occurrenceCounter[values.get(i).getFieldValue()]++;
        }

        for (int number = 1; number <= SudokuBoard.sudokuDimension; number++) {
            if (occurrenceCounter[number] > 1) {
                return false;
            }
        }

        return true;
    }

    public boolean check() {
        int[] occurrenceCounter = new int[SudokuBoard.sudokuDimension + 1];

        Arrays.fill(occurrenceCounter, 0);
        for (int i = 0; i < SudokuBoard.sudokuDimension; i++) {
            if (values.get(i).getFieldValue() == 0) {
               return false;
            }
            occurrenceCounter[values.get(i).getFieldValue()]++;
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
        for (int i = 0; i < values.size(); i++) {
            if (objStructure.values.get(i).getFieldValue() != this.values.get(i).getFieldValue()) {
                return false;
            }
        }

        return true;
    }

}
