import java.util.*;

public class Game {
    private Board board;
    private Scanner sc;
    //constructor
    public Game(int rows, int cols, int bombs) {
        board = new Board(rows, cols, bombs);
        sc = new Scanner(System.in);
    }
    public void start() {
        board.printBoard();
        while (true) {
            System.out.print("Enter command: ");

            String cmd = sc.next();
            int r, c;

// For flagging cells
            if (cmd.equals("f")) {
                if (!sc.hasNextInt()) { System.out.println("Invalid!"); sc.next(); continue; }
                r = sc.nextInt();

                if (!sc.hasNextInt()) { System.out.println("Invalid!"); sc.next(); continue; }
                c = sc.nextInt();

                if (r < 0 || r >= board.getRows() || c < 0 || c >= board.getCols()) {
                    System.out.println("Out of range!");
                    continue;
                }

                board.toggleFlag(r, c);
                continue;
            }

// try - catch block for opening cells
            try {
                r = Integer.parseInt(cmd);
            } catch (Exception e) {
                System.out.println("Invalid!");
                continue;
            }

            if (!sc.hasNextInt()) {
                System.out.println("Invalid!");
                sc.next();
                continue;
            }
            c = sc.nextInt();


// end command
            // this iss logic
            System.out.println("You entered: " + r + " " + c);

            if (r < 0 || r >= board.getRows() || c < 0 || c >= board.getCols()) {
                System.out.println("Out of range!");
                continue;
            }

            boolean safe = board.reveal(r, c);
            board.printBoard();

            if (!safe) {
                System.out.println("Game Over!");
                break;
            }

            if (board.checkWin()) {
                System.out.println("You Win!");
                break;
            }
        }
    }

    }

