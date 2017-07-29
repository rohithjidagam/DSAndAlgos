package com.uh;

public class LeetCode {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                int live_count = checkAllAdjacent(i, j, m, n, board);
                
                if (board[i][j] == 1) {

                    if (live_count < 2) {
                        board[i][j] = 0;
                    }

                    else if (live_count == 2 || live_count == 3) {
                        board[i][j] = 1;
                    }

                    else if (live_count > 3) {
                        board[i][j] = 0;
                    } 
                } else {
                    if (live_count == 3) {
                        board[i][j] = 1;
                    }
                }

            }

        }

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int checkAllAdjacent(int i, int j, int m, int n, int[][] b) {
        int live_count = 0;
        for (int l = i - 1; l <= i + 1; l++) {
            for (int k = j - 1; k <= j + 1; k++) {
                if (l == i && k == j)
                    continue;

                if (isValid(l, k, m, n))
                    if (b[l][k] == 1) {
                        live_count++;
                    }
            }
        }

        return live_count;

    }

    private boolean isValid(int l, int k, int m, int n) {
        return l >= 0 && k >= 0 && l < m && k < n;
    }

    public static void main(String[] args) {

        int[][] board = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 0 }, { 0, 0, 1, 1 } };
        LeetCode lc = new LeetCode();
        lc.gameOfLife(board);
    }
}