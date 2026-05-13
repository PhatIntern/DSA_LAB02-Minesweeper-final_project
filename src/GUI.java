import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private Board board;
    private JButton[][] buttons;
    private int rows, cols;

    public GUI(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;

        board = new Board(rows, cols, mines);

        buttons = new JButton[rows][cols];

        setTitle("Minesweeper");
        setSize(500, 500);
        setLayout(new GridLayout(rows, cols));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                JButton btn = new JButton("-");
                buttons[i][j] = btn;

                int r = i;
                int c = j;

                // Left click
                btn.addActionListener(e -> {
                    boolean safe = board.reveal(r, c);

                    updateBoard();

                    if (!safe) {

                        board.revealAllBombs();

                        updateBoard();

                        disableAllButtons();

                        JOptionPane.showMessageDialog(this, "Game Over!");
                    }

                    if (board.checkWin()) {
                        JOptionPane.showMessageDialog(this, "You Win!");
                    }
                });

                // Right click for flag
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {

                        if (SwingUtilities.isRightMouseButton(e)) {
                            board.toggleFlag(r, c);
                            updateBoard();
                        }
                    }
                });

                add(btn);
            }
        }
    }

    private void updateBoard() {

        char[][] visible = board.getVisible();
        boolean[][] flag = board.getFlag();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (flag[i][j]) {
                    buttons[i][j].setText("F");
                } else {
                    char cell = visible[i][j];

                    buttons[i][j].setText(String.valueOf(cell));

// coloring
                    switch (cell) {

                        case '1':
                            buttons[i][j].setForeground(Color.BLUE);
                            break;

                        case '2':
                            buttons[i][j].setForeground(Color.GREEN);
                            break;

                        case '3':
                            buttons[i][j].setForeground(Color.RED);
                            break;

                        case '4':
                            buttons[i][j].setForeground(Color.MAGENTA);
                            break;

                        case '*':
                            buttons[i][j].setForeground(Color.BLACK);
                            break;

                        default:
                            buttons[i][j].setForeground(Color.DARK_GRAY);
                    }
                }
                if (visible[i][j] != '-' && !flag[i][j]) {

                    buttons[i][j].setFocusable(false);

                    buttons[i][j].setBackground(Color.WHITE);

                }
            }
        }
    }
    private void disableAllButtons() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                buttons[i][j].setEnabled(false);
            }
        }
    }
}
