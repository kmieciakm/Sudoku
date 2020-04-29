package sudoku;

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
}
