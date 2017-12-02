package com.uh;

public class MatrixExamples {

    public static void main(String[] args) {

        int[][] arr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

        spriralForm(arr, arr.length, arr[0].length);
        System.out.println();

        diagonal(arr, arr.length, arr[0].length);
    }

    private static void diagonal(int[][] arr, int R, int C) {

        for (int k = 0; k < arr.length; k++) {

            System.out.print(arr[k][0] + " ");

            int i = k - 1;
            int j = 1;

            while (isValid(i, j, arr)) {

                System.out.print(arr[i][j] + " ");
                i--;
                j++;
            }
        }

        for (int k = 1; k < arr[0].length; k++) {

            System.out.print(arr[R-1][k] + " ");

            int i = R - 2;
            int j = k + 1;

            while (isValid(i, j, arr)) {

                System.out.print(arr[i][j] + " ");
                i--;
                j++;
            }
        }

    }

    private static boolean isValid(int i, int j, int[][] arr) {
        return !(i < 0 || j < 0 || i >= arr.length || j >= arr[0].length);
    }

    private static void spriralForm(int[][] arr, int m, int n) {

        int k = 0; // row
        int l = 0; // col
        int i;

        while (k < m && l < n) {

            for (i = l; i < n; i++) {
                System.out.print(arr[k][i] + " ");
            }
            k++;
            for (i = k; i < m; i++) {
                System.out.print(arr[i][n - 1] + " ");
            }
            n--;

            if (k < m) {
                for (i = n - 1; i >= l; i--) {
                    System.out.print(arr[m - 1][i] + " ");
                }
                m--;
            }

            if (l < n) {
                for (i = m - 1; i >= k; i--) {
                    System.out.print(arr[i][l] + " ");
                }
                l++;
            }

        }

    }

}
