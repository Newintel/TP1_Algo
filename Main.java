import java.util.Scanner;

class Main {

    private static TTT game;
    private static final Scanner scanner = new Scanner(System.in);

    private static void humanPlay() {
        int cell;
        do {
            System.out.print("Enter the cell number (1-9): ");
            try {
                cell = scanner.nextInt();
            } catch (Exception e) {
                cell = -1;
            } finally {
                scanner.nextLine();
            }
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
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        int choice = -1;

        do {
            System.out.println("How do you want to play?");
            System.out.println("\t0: Player vs. Player");
            System.out.println("\t1: Player vs. AI");
            System.out.println("\t2: AI vs. Player");
            System.out.println("\t3: AI vs. AI");
            do {
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();
                } catch (Exception ignored) {
                } finally {
                    scanner.nextLine();
                    if (choice < 0 || choice > 3) {
                        choice = -1;
                    }
                }
            } while (choice == -1);

            p1_human = (choice & 2) == 0;
            p2_human = (choice & 1) == 0;

            game = new TTT();
            System.out.println(game);

            while (!game.isGameOver()) {
                play();
            }

            if (game.getWinner() == TTT.Mark.EMPTY) {
                System.out.println("It's a draw!");
            } else {
                System.out.println(game.getWinner() + " won!");
            }

            System.out.println();
            System.out.print("Do you want to play again ? [Y|n]: ");
            exit = !scanner.nextLine().matches("^$|^[yY]");
            System.out.println("\n════════════════════\n");
        } while (!exit);
    }

}