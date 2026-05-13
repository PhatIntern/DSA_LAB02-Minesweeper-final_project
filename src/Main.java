import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        String[] options = {"Easy", "Medium", "Hard"};

        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose difficulty",
                "Minesweeper",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            new GUI(5, 5, 5);
        }
        else if (choice == 1) {
            new GUI(8, 8, 10);
        }
        else if (choice == 2) {
            new GUI(10, 10, 20);
        }
        else if (choice == 3) {
            System.out.println("Invalid difficulty");
            return;
        }
    }
}