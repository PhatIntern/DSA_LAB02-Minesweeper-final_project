import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose difficulty:");
        System.out.println("1. Easy (5x5, 5 bombs)");
        System.out.println("2. Medium (8x8, 10 bombs)");
        System.out.println("3. Hard (10x10, 20 bombs)");

        int choice = sc.nextInt();

        Game game;

        if (choice == 1) {
            game = new Game(5, 5, 5);
        } else if (choice == 2) {
            game = new Game(8, 8, 10);
        } else if(choice == 3){
            game = new Game(10, 10, 20);
        }
        else {
            System.out.println("Invalid Difficulty");
            return;
        }


        game.start();
    }
}