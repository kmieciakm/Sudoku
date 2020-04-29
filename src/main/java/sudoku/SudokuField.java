package sudoku;

import com.google.common.base.Objects;

public class SudokuField {
    private int value;

    public SudokuField() {
        value = 0;
    }

    public SudokuField(int initValue) {
        setFieldValue(initValue);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        value = newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuField)) return false;
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "SudokuField{" +
                "value=" + value +
                '}';
    }
}
