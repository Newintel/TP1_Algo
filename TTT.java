public class TTT {

    public enum Pion {
        EMPTY('-'), CIRCLE('O'), CROSS('X');

        public final char c;

        Pion(char c) {
            this.c = c;
        }
    }

    private final Pion[][] grid = new Pion[3][3];

    public TTT() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grid[x][y] = Pion.EMPTY;
            }
        }
    }

    public TTT(TTT ttt) {
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                grid[x][y] = ttt.grid[x][y];
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

    public void play(int x, int y, Pion pion){
        
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
