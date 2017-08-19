package com.uh;

public class MatrixChainMultiplication {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 4 };
        int min = matrixChain(arr, 1, arr.length - 1);
        System.out.println(min);

        int minDp = matrixChainDP(arr, arr.length);
        System.out.println(minDp);
    }

    private static int matrixChainDP(int[] arr, int n) {

        int[][] dp = new int[n][n];

        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 0;
        }

        for (int l = 2; l < dp.length; l++) {
            for (int i = 1; i < n - l + 1; i++) {

                int j = i+l-1;
                
                if(j == n)
                    continue;
                
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    
                    int q = dp[i][k] + dp[k+1][j] + arr[i-1] * arr[k] * arr[j];
                    if(q < dp[i][j])
                        dp[i][j] = q;
                }
            }
        }
        return dp[1][n-1];
    }

    private static int matrixChain(int[] arr, int i, int j) {

        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {

            int count = matrixChain(arr, i, k) + matrixChain(arr, k + 1, j) + arr[i - 1] * arr[k] * arr[j];

            if (count < min)
                min = count;

        }

        return min;
    }

}
