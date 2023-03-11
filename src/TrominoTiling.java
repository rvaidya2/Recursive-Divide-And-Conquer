import java.util.Scanner;

public class TrominoTiling {

    private static int TILE_COUNT = 0;
    private static int[][] BOARD = new int[1024][1024];

    public static void tromino(int x_board, int y_board, int x_missing, int y_missing, int board_size) {
        if (board_size == 1) {
            return;
        }
        int half_size = board_size / 2;
        int x_center = x_board + half_size;
        int y_center = y_board + half_size;
        String missingSquare = "MS";
        String LR = "LR";
        String LL = "LL";
        String UL = "UL";
        String UR = "UR";

        // Tile the upper left subboard
        if (x_missing < x_center && y_missing >= y_center) {
            tromino(x_board, y_center, x_missing, y_missing, half_size);
        } else {
            TILE_COUNT++;
            BOARD[x_center - 1][y_center] = TILE_COUNT;
            BOARD[x_center][y_center] = TILE_COUNT;
            BOARD[x_center][y_center - 1] = TILE_COUNT;
            tromino(x_board, y_center, x_center - 1, y_center, half_size);

        }

        // Tile the upper right subboard
        if (x_missing >= x_center && y_missing >= y_center) {
            tromino(x_center, y_center, x_missing, y_missing, half_size);
        } else {
            TILE_COUNT++;
            BOARD[x_center - 1][y_center - 1] = TILE_COUNT;
            BOARD[x_center][y_center] = TILE_COUNT;
            BOARD[x_center][y_center - 1] = TILE_COUNT;
            tromino(x_center, y_center, x_center, y_center - 1, half_size);

        }

        // Tile the lower left subboard
        if (x_missing < x_center && y_missing < y_center) {
            tromino(x_board, y_board, x_missing, y_missing, half_size);
        } else {
            TILE_COUNT++;
            BOARD[x_center - 1][y_center - 1] = TILE_COUNT;
            BOARD[x_center - 1][y_center] = TILE_COUNT;
            BOARD[x_center][y_center - 1] = TILE_COUNT;
            tromino(x_board, y_board, x_center - 1, y_center - 1, half_size);

        }

        // Tile the lower right subboard
        if (x_missing >= x_center && y_missing < y_center) {
            tromino(x_center, y_board, x_missing, y_missing, half_size);
        } else {
            TILE_COUNT++;
            BOARD[x_center - 1][y_center - 1] = TILE_COUNT;
            BOARD[x_center - 1][y_center] = TILE_COUNT;
            BOARD[x_center][y_center] = TILE_COUNT;
            tromino(x_center, y_board, x_center, y_center, half_size);

        }
    }

    public static void printBoard(int[][] board, int size, int x_missing, int y_missing) {
          /*   What you need to do in the base case is
        to decide which L shape to be put in the three squares. Please print “LR” (Lower Right) in all
        three squares for the first L shape (see the figure above), print “LL” (Lower Left) for the second
        L shape (see the figure above), “UL” (Upper Left) for the third L shape (see the figure above),
        “UR” (Upper Right) for the forth L shape (see the figure above).    */

        String missingSquare = "MS";
        String LR = "LR";
        String LL = "LL";
        String UL = "UL";
        String UR = "UR";


        for (int j = size - 1; j >= 0; j--) {
            for (int i = 0; i < size; i++) {
                if (i == x_missing && j == y_missing) {
                    System.out.print(missingSquare + " ");
                }else if(x_missing == 1 && y_missing == 1){
                    System.out.print(LL + " ");
                }else if(x_missing == 0 && y_missing == 0){
                    System.out.print(UR + " ");
                }else if(x_missing ==  1 && y_missing ==  0){
                    System.out.print(UL + " ");
                }else if(x_missing == 0 && y_missing == 1){
                    System.out.print(LR + " ");
                }
                else {
                    String tile = String.valueOf(board[i][j]);
                    System.out.print(tile + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Enter board size (needs to be 2^n and 0 to quit) : ");
            int boardsize = scanner.nextInt();
            if(boardsize == 0){
                System.exit(0);
            }
            System.out.print("Enter missing co-ordinates (seperated by a space): ");
            int missingX = scanner.nextInt();
            int missingY = scanner.nextInt();
            tromino(0, 0, missingX, missingY, boardsize);
            printBoard(BOARD, boardsize, missingX, missingY);
        }

    }
}