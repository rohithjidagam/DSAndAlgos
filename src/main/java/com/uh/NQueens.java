package com.uh;

public class NQueens {

    public static void main(String[] args) {

        int n = 4;
        int[][] mat = new int[n][n];

        boolean b = placeQueens(mat, 0);
        System.out.println(b);
        print(mat);
    }

    private static void print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean placeQueens(int[][] mat, int j) {

        if (j >= mat.length)
            return true;

        for (int i = 0; i < mat.length; i++) {

            if (isSafe(mat, i, j)) {

                mat[i][j] = 1;

                if (placeQueens(mat, j + 1))
                    return true;

                mat[i][j] = 0;
            }
        }

        return false;

    }

    private static boolean isSafe(int[][] mat, int row, int col) {

        int i, j;
        for (i = 0; i < col; i++)
            if (mat[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (mat[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < mat.length; i++, j--)
            if (mat[i][j] == 1)
                return false;

        return true;
    }

}
