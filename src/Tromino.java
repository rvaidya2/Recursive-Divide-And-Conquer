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
        int a = 0, b =0;

        if(boardS == 1)
        {
            return 0;
        }
        if (!isPowerOfTwo(boardS)) {
            System.out.println("Wrong Input, n should be a power of 2");
            return 0;
        }


        if(boardS == 2) {
            int  r = 0, c = 0;


            for (int i = 0; i < x + boardS; i++) {
                for (int j = 0; j < y + boardS; j++) {
                    if (board[x + i][y + j] != 0) {

                        r = i;
                        c = j;

                    }
                }
            }

            for (int i = 0; i <  boardS; i++) {
                for (int j = 0; j < boardS; j++) {
                    if (board[x + i][y + j] == 0) {
                        if (r < x + boardS / 2 && c < y + boardS / 2) {
                            board[x + i][y + j] = 1;
                        } else if (r >= x + boardS / 2 && c < y + boardS / 2) {
                            board[x + i][y + j] = 2;
                        } else if (r < x + boardS / 2 && c >= y + boardS / 2) {
                            board[x + i][y + j] = 3;
                        } else if (r >= x + boardS / 2 && c >= y + boardS / 2) {
                            board[x + i][y + j] = 4;
                        }
                    }

                }
            }
            return 0;
        }


        for (int i = x; i < x + boardS; i++)
        {
            for (int j = y; j < y + boardS; j++)
            {
                if (board[i][j] != 0)
                {
                    a = i;
                    b = j;
                }

            }
        }

        if (a < x + (boardS / 2) && b < y + (boardS / 2)) {

                board[x + boardS / 2][y + (boardS / 2) - 1] = 1;
                board[x + boardS / 2][ y + boardS / 2] = 1;
                board[x + boardS / 2 - 1][y + boardS / 2] = 1;


        }


        else if (a >= x + (boardS / 2) && b < y + (boardS / 2)){

                board[x + (boardS / 2) - 1][y + (boardS / 2)] = 2;
                board[x + (boardS / 2)][y + boardS / 2] = 2;
                board[x + (boardS/ 2) - 1][y + (boardS / 2) - 1] = 2;


        }



        else if (a < x + (boardS / 2) && b >= y + (boardS / 2)){
            board[x + boardS / 2][y + (boardS / 2) - 1] = 3;
            board[x + boardS / 2][y + boardS / 2] = 3;
            board[x + boardS / 2 - 1][y + boardS / 2 - 1] =3;
        }



        else if (a >= x + (boardS / 2) && b >= y + (boardS / 2)){
            board[x + (boardS / 2) - 1][y + (boardS / 2)] = 4;
            board[x + (boardS / 2)][y + (boardS / 2) - 1] = 4;
            board[x + (boardS / 2) - 1][y + (boardS / 2) - 1] = 4;
        }



        // dividing it again in 4 quadrants
        tromino(boardS / 2, x, y + (boardS / 2));
        tromino(boardS / 2, x, y);
        tromino(boardS / 2, x + (boardS/ 2), y);
        tromino(boardS/ 2, x + (boardS / 2), y + (boardS/ 2));



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
                    //Quadrant 1 UR
                    if (board[i][j] == 1) {
                        System.out.print(UR + " ");
                    }
                    // Quadrant 2 UL
                    if (board[i][j] == 2) {
                        System.out.print(UL + " ");
                    }
                    //Quadrant 3 LR
                    if (board[i][j] == 3) {
                        System.out.print(LR + " ");
                    }
                    // Quadrant 4 LL
                    if (board[i][j] == 4) {
                        System.out.print(LL + " ");
                    }
                    if(board[i][j] == 0){
                        System.out.print("00 ");
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
            printBoard(board, boardSize);

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = 0;
                }
            }
        }

    }

}