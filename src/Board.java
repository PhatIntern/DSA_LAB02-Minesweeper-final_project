import java.util.*;
public class Board {
    //attributes
    private int rows, cols, mines;
    private char[][] visible;
    private char[][] hidden;
    private boolean[][] revealed;
    private boolean[][] flag; // added feature

    //loop 8 directions
    private final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    //Constructor
    public Board(int rows, int cols, int mines){
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        visible = new char[rows][cols];
        hidden = new char[rows][cols];
        revealed = new boolean[rows][cols];
        flag = new boolean[rows][cols];
        initBoard();
        placeBomb();
        calculateNumbers();
    }
    //getter
    public int getRows() { return rows; }
    public int getCols() { return cols; }

    private void initBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                hidden[i][j] = ' ';
                visible[i][j] = '-';
                revealed[i][j] = false;
                flag[i][j] = false;
            }
        }
    }

    private void placeBomb() {
        Random rand = new Random();
        int count = 0;
        while(count < mines) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if(hidden[r][c] != '*') {
                hidden[r][c] = '*';
                count++;
            }
        }
    }
    private void calculateNumbers() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(hidden[i][j] == '*') continue;
                int count = 0;
                for(int k = 0; k < 8; k++) {
                    int numberRows = i + dx[k];
                    int numberCols = j + dy[k];
                    if(inBounds(numberRows, numberCols) && hidden[numberRows][numberCols] == '*') {
                        count++;
                    }
                }
                hidden[i][j] = (char) (count + '0');
            }
        }
    }
    private boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
    //DFS method to show if not mine
    public boolean reveal(int r, int c) {

        if (!inBounds(r, c)) return true;


        if (revealed[r][c]) return true;


        if (flag[r][c]) {
            System.out.println("Cell is flagged!");
            return true;
        }

        revealed[r][c] = true;


        if (hidden[r][c] == '*') {
            visible[r][c] = '*';
            return false;
        }

        visible[r][c] = hidden[r][c];

        // DFS mở lan
        if (hidden[r][c] == '0') {
            for (int i = 0; i < 8; i++) {
                reveal(r + dx[i], c + dy[i]);
            }
        }

        return true;
    }
    public void printBoard() {
        System.out.print("  ");
        for (int j = 0; j < cols; j++) System.out.print(j + " ");
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < cols; j++) {
                if (flag[i][j]) {
                    System.out.print("F ");
                }
                else {
                    System.out.print(visible[i][j] + " ");
                }
        }
            System.out.println();
        }
    }
    public void toggleFlag(int r, int c) {
        if (!inBounds(r, c) || revealed[r][c]) return;

        flag[r][c] = !flag[r][c];
    }
    public boolean checkWin() {
        int safeCells = rows * cols - mines;
        int revealedCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j] && hidden[i][j] != '*') {
                    revealedCount++;
                }
            }
        }
        return revealedCount == safeCells;
    }
}// end class

