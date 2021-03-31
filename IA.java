import java.util.Arrays;
import java.util.List;

public class IA {

    public static int minimax(TTT game) {
        List<Integer> emptyCells = game.getEmptyCells();
        int emptyCellsCount = emptyCells.size();
        int scores[] = new int[emptyCellsCount];
        TTT.Mark player = game.getPlayer();
        for (int i = 0; i < emptyCellsCount; i++) {
            TTT copy = new TTT(game);
            copy.play(emptyCells.get(i));
            scores[i] = minimax(copy, player);
        }

        int maxIndex = 0;
        for (int i = 1; i < emptyCellsCount; i++) {
            if (scores[i] > scores[maxIndex]) maxIndex = i;
        }

        return emptyCells.get(maxIndex);
    }

    private static int minimax(TTT game, TTT.Mark player) {
        if (game.isGameOver()) {
            if (game.getWinner() == player) {
                return 10;
            } else if (game.getWinner() == TTT.Mark.EMPTY) {
                return 0;
            } else {
                return -10;
            }
        }

        List<Integer> emptyCells = game.getEmptyCells();
        int emptyCellsCount = emptyCells.size();
        int[] scores = new int[emptyCellsCount];

        for (int i = 0; i < emptyCellsCount; i++) {
            TTT copy = new TTT(game);
            copy.play(emptyCells.get(i));
            scores[i] = minimax(copy, player);
        }

        if (game.getPlayer() == player) {
            return Arrays.stream(scores).max().getAsInt();
        } else {
            return Arrays.stream(scores).min().getAsInt();
        }
    }

}
