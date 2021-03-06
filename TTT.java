import java.util.ArrayList;

public class TTT {

    public enum Mark {
        EMPTY('-'), CIRCLE('O'), CROSS('X');

        public final char c;

        Mark(char c) {
            this.c = c;
        }
    }

    public static final Mark PLAYER1 = Mark.CROSS;
    public static final Mark PLAYER2 = Mark.CIRCLE;

    private final Mark[][] grid = new Mark[3][3];
    private int turn = 0;
    private Mark winnerCache = null;

    public TTT() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grid[x][y] = Mark.EMPTY;
            }
        }
    }

    public TTT(TTT ttt) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grid[x][y] = ttt.grid[x][y];
            }
        }
        this.turn = ttt.turn;
        this.winnerCache = ttt.winnerCache;
    }

    public void print() {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------");
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j].c + " | ");
            }
            System.out.println("");
        }
        System.out.println("-------------");
    }

    public synchronized boolean play(int x, int y) {
        if (x >= 0 && y >= 0 && x < 3 && y < 3 && grid[y][x] == Mark.EMPTY) {
            grid[y][x] = getPlayer();
            turn++;
            winnerCache = null;
            return true;
        }
        return false;
    }

    public synchronized boolean play(int cellNumber) {
        int x = --cellNumber % 3;
        int y = cellNumber / 3;
        return this.play(x, y);
    }

    /**
     * @return The player whose turn it is to play.
     */
    public Mark getPlayer() {
        return turn % 2 == 0 ? PLAYER1 : PLAYER2;
    }

    /**
     * @return true if there is a winner, false otherwise.
     */
    private boolean check() {
        // Check diagonals
        if (grid[0][0] != Mark.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] || grid[0][2] != Mark.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            // Check lines and columns
            if (grid[i][0] != Mark.EMPTY && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] || grid[0][i] != Mark.EMPTY && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                return true;
            }

        }

        return false;
    }

    private synchronized Mark __getWinner() {
        Mark m;

        // Diagonals
        m = grid[1][1];
        if (m != Mark.EMPTY) {
            if (m == grid[0][0] && m == grid[2][2] || m == grid[0][2] && m == grid[2][0]) {
                return m;
            }
        }

        // Lines and columns
        for (int i = 0; i < 3; i++) {
            // Columns
            m = grid[0][i];
            if (m != Mark.EMPTY) {
                if (m == grid[1][i] && m == grid[2][i]) {
                    return m;
                }
            }

            // Lines
            m = grid[i][0];
            if (m != Mark.EMPTY) {
                if (m == grid[i][1] && m == grid[i][2]) {
                    return m;
                }
            }
        }

        return Mark.EMPTY;
    }

    public synchronized Mark getWinner() {
        if (winnerCache == null) {
            winnerCache = __getWinner();
        }
        return winnerCache;
    }

    public synchronized boolean isGameOver() {
        return turn == 9 || getWinner() != Mark.EMPTY;
    }

    public ArrayList<Integer> getEmptyCells() {
        if (turn == 9) {
            return null;
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[y][x] == Mark.EMPTY) {
                    res.add(3 * y + x + 1);
                }
            }
        }
        return res;
    }

    public int getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("???????????????????????????????????????\n");

        for (int i = 0; i < 3; i++) {
            builder.append("???");
            for (int j = 0; j < 3; j++) {
                builder.append(" " + grid[i][j].c + " ");
                if (j < 2)
                    builder.append("???");
            }
            builder.append("???\n");
            if (i < 2)
                builder.append("???????????????????????????????????????\n");
        }

        builder.append("???????????????????????????????????????\n");

        return builder.toString();
    }
}
