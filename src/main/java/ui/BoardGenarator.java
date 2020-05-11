package ui;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import sudoku.SudokuBoard;

public class BoardGenarator {

    private BoardGenarator() {}

    public static void generateBoard(SudokuBoard board, DifficultyLevels level) {
        switch (level) {
            case HARD: {
                erase(board, 65);
                break;
            }
            case MEDIUM: {
                erase(board, 55);
                break;
            }
            default: {
                erase(board, 40);
                break;
            }
        }
    }

    private static void erase(SudokuBoard board, double eraseAmount) {
        int max = 80;
        Random rng = new Random();
        Set<Integer> generated = new LinkedHashSet<>();
        while (generated.size() < eraseAmount) {
            Integer next = rng.nextInt(max);
            generated.add(next);
        }
        for (int number : generated) {
            board.set(number / 9, (number % 9), 0);
        }
    }
}
