package sudoku;

import org.junit.jupiter.api.Test;
import solver.SudokuSolver;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuStructureTest {

    @Test
    public void SudokuStructure_Constructor_FilledCorrectly() {
        SudokuStructure row = new SudokuStructure();
        SudokuStructure rowExpected = new SudokuStructure(Arrays.asList(
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0))
        );
        assertEquals(true, row.equals(rowExpected));
    }

    @Test
    public void SudokuStructure_ConstructorOverloaded_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SudokuStructure(Arrays.asList(
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0))
            );
        });
    }

    @Test
    public void SudokuStructure_CompareStructures_EqualsTrue() {
        SudokuStructure row = new SudokuStructure();
        SudokuStructure rowExpected = new SudokuStructure();
        assertEquals(true, row.equals(rowExpected));
    }

    @Test
    public void SudokuStructure_CompareToNull_EqualsFalse() {
        SudokuStructure row = new SudokuStructure();
        assertEquals(false, row.equals(null));
    }

    @Test
    public void SudokuStructure_SameReference_HashCodeMatches() {
        SudokuStructure row = new SudokuStructure();
        SudokuStructure rowExpected = row;
        assertEquals(row.hashCode(), rowExpected.hashCode());
    }

    @Test
    public void SudokuStructure_Equals_HashCodeEquals() {
        SudokuStructure row = new SudokuStructure();
        SudokuStructure rowExpected = new SudokuStructure();
        if (row.equals(rowExpected)) {
            assertTrue(row.hashCode() == rowExpected.hashCode());
        }
    }

    @Test
    public void SudokuStructure_CompareSameStructures_EqualsTrue() {
        SudokuStructure row = new SudokuStructure(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))
        );
        SudokuStructure rowExpected = new SudokuStructure(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))
        );
        assertEquals(true, row.equals(rowExpected));
    }

    @Test
    public void SudokuStructure_CompareStructures_EqualsFalse() {
        SudokuStructure row = new SudokuStructure();
        SudokuStructure rowExpected = new SudokuStructure(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))
        );
        assertEquals(false, row.equals(rowExpected));
    }

    @Test
    public void SudokuStructure_IncorrectStructure_VerifyFalse() {
        SudokuField[] columnArray = new SudokuField[SudokuBoard.sudokuDimension];
        Arrays.fill(columnArray, new SudokuField(1));
        SudokuStructure wrongColumn = new SudokuStructure(Arrays.asList(columnArray));

        assertEquals(false, wrongColumn.verify());
    }

    @Test
    public void SudokuStructure_CorrectStructure_VerifyTrue() {
        SudokuStructure column = new SudokuStructure(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))
        );

        assertEquals(true, column.verify());
    }

    @Test
    public void SudokuFiled_Clone_Correct() {
        SudokuStructure column = new SudokuStructure(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))
        );

        SudokuStructure clone = column.clone();
        assertNotSame(column, clone);
        assertEquals(column, clone);
    }

}
