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

    }

    public static void main(String[] args) {
        game = new TTT();
    }

}