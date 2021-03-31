import java.util.Scanner;

class Main {

    private static TTT game;
    private static final Scanner scanner = new Scanner(System.in);

    private static void humanPlay() {
        int cell;
        do {
            System.out.print("Enter the cell number (1-9): ");
            cell = scanner.nextInt();
        } while (!game.play(cell));
        System.out.println(game);
    }

    private static void IAPlay() {
        int cell = IA.minimax(game);
        if (game.play(cell)) {
            System.out.println(game);
        } else {
            throw new RuntimeException("IA wanted to play cell nb " + cell + " but it wasn't a valid move ¯\\_(ツ)_/¯");
        }
    }

    private static boolean p1_human = false;
    private static boolean p2_human = true;

    private static void play() {
        if (game.getTurn() % 2 == 0) {
            if (p1_human) {
                humanPlay();
            } else {
                IAPlay();
            }
        } else {
            if (!p2_human) {
                IAPlay();
            } else {
                humanPlay();
            }
        }
    }

    public static void main(String[] args) {
        game = new TTT();
        System.out.println(game);

        while (!game.isGameOver()) {
            play();

            if (!game.isGameOver()) {
                play();
            }
        }

        if (game.getWinner() == TTT.Mark.EMPTY) {
            System.out.println("It's a draw!");
        } else {
            System.out.println(game.getWinner() + " won!");
        }
    }

}