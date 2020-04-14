public class SudokuField {
    private int value;

    SudokuField() {
        value = 0;
    }

    SudokuField(int initValue) {
        setFieldValue(initValue);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        value = newValue;
    }
}
