package solver;

import sudoku.SudokuBoard;

public interface SudokuSolver {
    void solve(SudokuBoard board);

    SudokuSolver getCopy();
}
