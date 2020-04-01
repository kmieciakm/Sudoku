import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public void solve(SudokuBoard board) {
        fillBoard(board);
    }

    private boolean fillBoard(SudokuBoard board) {
        int emptyFiledX = -1;
        int emptyFiledY = -1;
        boolean isEmpty = true;

        outerloop:
        for (int x = 0; x < SudokuBoard.boardDimension; x++) {
            for (int y = 0; y < SudokuBoard.boardDimension; y++) {
                if (board.get(x, y) == 0) {
                    emptyFiledX = x;
                    emptyFiledY = y;
                    isEmpty = false;
                    break outerloop;
                }
            }
        }

        // no empty space left
        if (isEmpty) {
            return true;
        }

        List<Integer> numbers = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }};
        Collections.shuffle(numbers);

        for (int numberIndex = 0; numberIndex < SudokuBoard.boardDimension; numberIndex++) {
            board.set(emptyFiledX, emptyFiledY, numbers.get(numberIndex));
            if (board.isLayoutAllowed(emptyFiledX, emptyFiledY)) {
                if (fillBoard(board)) {
                    return true;
                }
            } else {
                board.set(emptyFiledX, emptyFiledY, 0);
            }
        }

        return false;

    }
}

