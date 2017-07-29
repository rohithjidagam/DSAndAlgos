package com.uh;

public class DPExamples {

    public static void main(String[] args) {

        DPExamples dp = new DPExamples();
        /**
         * Finding the maximum square sub-matrix with all equal elements
         */
        int[][] a = { { 2, 2, 3, 3, 4, 4 }, { 5, 5, 7, 7, 7, 4 }, { 1, 2, 7, 7, 7, 4 }, { 4, 4, 7, 7, 7, 4 },
                { 5, 5, 5, 1, 2, 7 }, { 8, 7, 9, 4, 4, 4 } };

        dp.largestKSubmatrix(a);

        /**
         * Maximum path sum that starting with any cell of 0-th row and ending
         * with any cell of (N-1)-th row
         */
        int[][] mat = { { 4, 2, 3, 4 }, { 2, 9, 1, 10 }, { 15, 1, 3, 0 }, { 16, 92, 41, 44 } };
        //int max = Integer.MIN_VALUE;
        for (int i = 0; i < mat[0].length; i++) {
          //  max = Math.max(max, dp.maxPathSumRec(mat, mat.length - 1, i));
        }
        
        dp.maxPathSumDP(mat);
    }

    private void maxPathSumDP(int[][] mat) {

        int n = mat.length;
        
        int[][] dp = new int[n][n];
        
        
    }

    private int maxPathSumRec(int[][] mat, int i, int j) {

        if (i == 0 && j == 0)
            return mat[i][j];

        if (i == mat.length || j < 0)
            return 0;

        return Math.max(maxPathSumRec(mat, i - 1, j),
                Math.max(maxPathSumRec(mat, i - 1, j - 1), maxPathSumRec(mat, i - 1, j + 1))) + mat[i][j];

    }

    private void largestKSubmatrix(int[][] a) {

        int r = a.length;
        int c = a[0].length;
        int[][] dp = new int[r][c];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else {
                    if (a[i][j] == a[i - 1][j] && a[i][j] == a[i][j - 1] && a[i][j] == a[i - 1][j - 1]) {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    } else {
                        dp[i][j] = 1;
                    }
                }
                max = Math.max(max, dp[i][j]);
            }

        }

        System.out.println(max);

    }

}
