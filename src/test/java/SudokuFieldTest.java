import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuFieldTest {

    @Test
    public void SudokuField_Constructor_ValueCorrect() {
        SudokuField field = new SudokuField();
        assertEquals(0, field.getFieldValue());
    }

    @Test
    public void SudokuField_ConstructorWithParams_ValueCorrect() {
        SudokuField field = new SudokuField(5);
        assertEquals(5, field.getFieldValue());
    }

}
