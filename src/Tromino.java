import java.util.*;

public class Tromino {

    public static int boardSize;
    public static int storeBoard;
    public static int missingX,  missingY;
    public static int[][] board = new int[128][128];
    public static boolean isPowerOfTwo(int n)
    {
        return (int)(Math.ceil((Math.log(n) / Math.log(2))))
                == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }

    public static int tromino(int boardS, int x, int y) {

        if(!isPowerOfTwo(boardS)){
            System.out.println("Wrong Input, n should be a power of 2");
            return 0;
        }


        int a = 0, b = 0;


        if (boardS == 2) {
            for (int j = boardS - 1; j >= 0; j--) {
                for (int i = 0; i < boardS; i++) {

                    if(board[i][j] == 0 && (a >= x + boardS / 2 && b < y + boardS / 2)){
                        board[i][j] = 1;
                    }
                    if(board[i][j] == 0 && (a >= x + boardS / 2 && b >= y + boardS / 2)){
                        board[i][j] = 2;
                    }
                    if(board[i][j] == 0 && (a < x + boardS / 2 && b < y + boardS / 2)){
                        board[i][j] = 3;
                    }
                    if(board[i][j] == 0 && (a < x + boardS / 2 && b >= y + boardS / 2)){
                        board[i][j] = 4;
                    }
                }
            }

            return 0;
        }


                // Locate Hole
                for (int i = x; i < x + boardS; i++) {
                    for (int j = y; j < y + boardS; j++) {
                        if (board[i][j] != 0) {
                            a = i;
                            b = j;
                        }

                    }
                }

                // Quadrant 1
                if (a >= x + boardS / 2 && b < y + boardS / 2) {
                    board[x + (boardS / 2) - 1 ][y + boardS / 2] = 1;
                    board[x + (boardS / 2)][y + (boardS / 2) - 1] = 1;
                    board[x + (boardS / 2) - 1][y + (boardS / 2) - 1] = 1;
//                    x + (n / 2) - 1, y + (n / 2), x + (n / 2),
//                            y + (n / 2) - 1, x + (n / 2) - 1,
//                            y + (n / 2) - 1

                }
                // Quadrant 2
                else if (a >= x + boardS / 2 && b >= y + boardS / 2) {
                    board[x + (boardS / 2) - 1][y + (boardS / 2)] = 1;
                    board[x + (boardS / 2)][y + (boardS / 2) - 1] = 1;
                    board[x + (boardS / 2) - 1][y + (boardS / 2) - 1] = 1;

                }
                // Quadrant 3
                else if (a < x + boardS / 2 && b < y + boardS / 2) {
                    board[x + boardS / 2][y + (boardS / 2) - 1] = 3;
                    board[x + boardS / 2][y + boardS / 2] = 3;
                    board[x + boardS / 2 - 1][y + boardS / 2] = 3;

                }
                // Quadrant 4
                else if (a < x + boardS / 2 && b >= y + boardS / 2) {
                    board[x + boardS / 2][y + (boardS / 2) - 1] = 3;
                    board[x + boardS / 2][y + boardS / 2] = 3;
                    board[x + boardS / 2 - 1][y + boardS / 2 - 1] = 3;
                }

                tromino(boardS / 2, x, y + boardS / 2);
                tromino(boardS / 2, x, y);
                tromino(boardS / 2, x + boardS / 2, y);
                tromino(boardS / 2, x + boardS / 2, y + boardS / 2);

                return 0;
            }





    public static int printBoard(int[][] board, int size) {
        if(!isPowerOfTwo(size)){

            return 0;
        }else {
            String missingSquare = "MS";
            String LR = "LR";
            String LL = "LL";
            String UL = "UL";
            String UR = "UR";


            for (int j = size - 1; j >= 0; j--) {
                for (int i = 0; i < size; i++) {
                    if (board[i][j] == -1) {
                        System.out.print(missingSquare + " ");
                    }
                    if (board[i][j] == 1) {
                        System.out.print(LL + " ");
                    }
                    if (board[i][j] == 2) {
                        System.out.print(UL + " ");
                    }
                    if (board[i][j] == 3) {
                        System.out.print(LR + " ");
                    }
                    if (board[i][j] == 4) {
                        System.out.print(UR + " ");
                    }
                    if(board[i][j] == 0){
                        System.out.print("HM ");
                    }

                }
                System.out.println();
            }
        }
        return 0;
            }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter board size (needs to be 2^n and 0 to quit) : ");
            boardSize = scanner.nextInt();
            if (boardSize == 0) {
                System.exit(0);
            }

            System.out.print("Enter missing co-ordinate (seperated by a space): ");
            missingX = scanner.nextInt();
            missingY = scanner.nextInt();
            storeBoard = boardSize;
            board[missingX][missingY] = -1;
            tromino(boardSize, 0 , 0);
            printBoard(board, storeBoard);
            board[missingX][missingY] = 0;
        }

    }

}