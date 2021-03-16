import java.util.Scanner;

class Main {

    private static TTT game;

    private static void play(int caseNumber){
        int x = --caseNumber % 3;
        int y = (caseNumber - x)/3;
        if (game.play(x, y)){ 
            System.out.println(game);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    private static void humanPlay(){
        // TODO: Vérifier que le coup est bien valide
        int cell = scanner.nextInt();
        play(cell);
    }

    private static void IAPlay() {

    }

    public static void main(String[] args) {
        game = new TTT();
        play(0);
        play(0);
        play(1);
        play(2);
    }

}