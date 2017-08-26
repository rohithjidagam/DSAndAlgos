package com.uh;

public class Knapsack01 {

    public static void main(String[] args) {

        int val[] = new int[] { 60, 100, 120 };
        int wt[] = new int[] { 10, 20, 30 };
        int W = 50;
        int n = val.length;

        int maxValue = kanpsackRecur(val, wt, n, W);
        System.out.println(maxValue);

        int max2 = knapsackDP(val, wt, n, W);
        System.out.println(max2);
    }

    private static int knapsackDP(int[] val, int[] wt, int n, int w) {

        int[][] dp = new int[n + 1][w + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (i == 0 || w == 0)
                    dp[i][j] = 0;
                else if (wt[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i - 1][j - wt[i - 1]]);
            }
        }
        return dp[n][w];
    }

    private static int kanpsackRecur(int[] val, int[] wt, int n, int w) {

        if (w == 0 || n == 0)
            return 0;

        if (wt[n - 1] > w)
            return kanpsackRecur(val, wt, n - 1, w);

        return Math.max(kanpsackRecur(val, wt, n - 1, w), val[n - 1] + kanpsackRecur(val, wt, n - 1, w - wt[n - 1]));
    }

}
