package solver;

import sudoku.SudokuBoard;

public interface SudokuSolver extends Cloneable {
    void solve(SudokuBoard board);

    SudokuSolver clone();
}
