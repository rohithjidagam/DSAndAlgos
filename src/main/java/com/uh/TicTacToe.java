package com.uh;

public class TicTacToe {

    // false -> computer, true -> player

    private static boolean turn = true;

    public static void main(String[] args) {

        int rows = 3;
        int cols = 3;
        char[][] board = new char[rows][cols];
        initialize(board);

        char res = placeMove(rows, cols, board);
        System.out.println(res);

        printBoard(board);

        System.out.println(checkValid(board));
    }

    private static boolean checkValid(char[][] board) {

        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'X')
                    xCount++;
                else if (board[i][j] == '0')
                    oCount++;
            }
        }
        
        if(xCount == oCount || xCount == oCount + 1){
            
            if(checkWinner(board, '0')){
                
                if(checkWinner(board, 'X'))
                    return false;
                
                return xCount == oCount;
            }
            
            if(checkWinner(board, 'X') && xCount != oCount+1)
                return false;
            
            return true;
            
        }
        
        return false;

    }

    private static char placeMove(int rows, int cols, char[][] board) {

        char ch = ' ';
        while (isEmpty(board)) {
            int row = (int) Math.floor(Math.random() * rows);
            int col = (int) Math.floor(Math.random() * cols);
            if (board[row][col] == '*') {
                if (turn) {
                    board[row][col] = 'X';
                    ch = 'X';
                    turn = false;
                } else if (!turn) {
                    board[row][col] = '0';
                    ch = '0';
                    turn = true;
                }
                boolean c = checkWinner(board, ch);
                if (c && !turn)
                    return 'X';
                else if (c && turn)
                    return '0';
            }
        }
        return '*';
    }

    private static boolean checkWinner(char[][] board, char ch) {
        return checkRow(board, ch) || checkCol(board, ch) || checkDiag(board, ch);
    }

    private static boolean checkRow(char[][] board, char ch) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] == ch)
                return true;
        }
        return false;
    }

    private static boolean checkCol(char[][] board, char ch) {
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[2][i] == ch)
                return true;
        }
        return false;
    }

    private static boolean checkDiag(char[][] board, char ch) {
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == ch)
            return true;
        else if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] == ch)
            return true;
        return false;
    }

    private static void initialize(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '*';
            }
        }
    }

    private static boolean isEmpty(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '*')
                    return true;
            }
        }
        return false;
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
