public class TTT {

    public enum Pion {
        EMPTY('-'), CIRCLE('O'), CROSS('X');

        public final char c;

        Pion(char c) {
            this.c = c;
        }
    }

    public static final Pion PLAYER1 = Pion.CROSS;
    public static final Pion PLAYER2 = Pion.CIRCLE;

    private final Pion[][] grid = new Pion[3][3];
    private int turn = 0;

    public TTT() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grid[x][y] = Pion.EMPTY;
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

    public void play(int x, int y) {
        if (turn < 9 && grid[x][y] == Pion.EMPTY) {
            grid[y][x] = getPlayer();

            // C'est mieux d'afficher dans la boucle principale non ?
//            print();

            if (check()) {
                // Code if won
                System.out.println("The " + grid[y][x] + " won!");
            } else if (turn == 8) {
                // Code if draw
                System.out.println("It is a draw!");
            }
            turn++;
        }
    }

    /**
     * @return The player whose turn it is to play.
     */
    public Pion getPlayer() {
        return turn % 2 == 0 ? PLAYER1 : PLAYER2;
    }

    /**
     * @return true if there is a winner, false otherwise.
     */
    private boolean check() {
        // Check diagonals
        if (grid[0][0] != Pion.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] || grid[0][2] != Pion.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            // Check lines and columns
            if (grid[i][0] != Pion.EMPTY && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] || grid[0][i] != Pion.EMPTY && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                return true;
            }

        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("╔═══╤═══╤═══╗\n");

        for (int i = 0; i < 3; i++) {
            builder.append("║");
            for (int j = 0; j < 3; j++) {
                builder.append(" " + grid[i][j].c + " ");
                if (j < 2)
                    builder.append("│");
            }
            builder.append("║\n");
            if (i < 2)
                builder.append("╟───┼───┼───╢\n");
        }

        builder.append("╚═══╧═══╧═══╝\n");

        return builder.toString();
    }
}
