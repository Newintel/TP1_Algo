public class TTT {

    public enum Pion {
        EMPTY('-'), CIRCLE('O'), CROSS('X');

        public final char c;

        Pion(char c) {
            this.c = c;
        }
    }

    private final Pion[][] grid = new Pion[3][3];
    private int turn = 0;
    private Pion player1, player2;

    public TTT(Pion p1, Pion p2) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grid[x][y] = Pion.EMPTY;
            }
        }

        player1 = p1;
        player2 = p2;
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

    public void play(int x, int y){
        if (turn < 9){
            grid[y][x] = turn % 2 == 0 ? player1 : player2;

            print();

            if (check()){
                // Code if won
                System.out.println("The " + grid[y][x] + " won!");
            }
            else if (turn == 8){
                // Code if draw
                System.out.println("It is a draw!");
            }
            else {turn++;}
        }
    }

    private boolean check(){
        // Check diagonals
        if (grid[0][0] != Pion.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] || grid[0][2] != Pion.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]){
            return true;
        }

        for (int i = 0; i < 3; i++) {
            // Check lines and columns
            if (grid[i][0] != Pion.EMPTY && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] || grid[0][i] != Pion.EMPTY && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]){
                return true;
            }

        }

        return false;
    }
}
