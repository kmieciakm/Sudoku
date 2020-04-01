public class SudokuField {
    private int value;

    SudokuField() {
        value = 0;
    }

    SudokuField(int initValue) {
        setFieldValue(initValue);
    }

    int getFieldValue() {
        return value;
    }

    void setFieldValue(int newValue) {
        value = newValue;
    }
}
