package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test void SudokuField_SameObject_HashCodeEquals() {
        SudokuField fieldOne = new SudokuField(5);
        SudokuField fieldTwo = new SudokuField(5);
        assertEquals(fieldOne.hashCode(), fieldTwo.hashCode());
    }

    @Test void SudokuField_HashCode_EqualsTrue() {
        SudokuField fieldOne = new SudokuField(5);
        SudokuField fieldTwo = new SudokuField(5);
        if (fieldOne.equals(fieldTwo)) {
            assertTrue(fieldOne.hashCode() == fieldTwo.hashCode());
        }
    }

    @Test void SudokuField_CompareToNull_EqualsFalse() {
        SudokuField fieldOne = new SudokuField(5);
        assertEquals(false, fieldOne.equals(null));
    }

    @Test void SudokuField_SameReferance_EqualsTrue() {
        SudokuField fieldOne = new SudokuField(5);
        SudokuField fieldTwo = fieldOne;
        assertEquals(true, fieldOne.equals(fieldTwo));
    }

    @Test void SudokuField_SameObject_EqualsTrue() {
        SudokuField fieldOne = new SudokuField(5);
        SudokuField fieldTwo = new SudokuField(5);
        assertEquals(true, fieldOne.equals(fieldTwo));
    }

}
