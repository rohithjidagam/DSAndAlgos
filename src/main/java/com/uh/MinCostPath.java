package com.uh;

public class MinCostPath {

    public static void main(String[] args) {

        int[][] grid = { { 31, 100, 65, 12, 18 }, { 10, 13, 47, 157, 6 }, { 100, 113, 174, 11, 33 },
                { 88, 124, 41, 20, 140 }, { 99, 32, 111, 41, 20 } };

        int R = grid.length;
        int C = grid[0].length;

        int n = minCost(grid, R - 1, C - 1);
        System.out.println(n);

        int[][] cost = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
        int minInitPoints = minInitialPoints(cost);
        System.out.println(minInitPoints);
    }

    private static int minInitialPoints(int[][] cost) {

        int m = cost.length;
        int n = cost[0].length;

        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = cost[m - 1][n - 1] > 0 ? 1 : 1 + Math.abs(cost[m - 1][n - 1]);

        for (int i = m - 2; i >= 0; i--)
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - cost[i][n - 1], 1);

        for (int i = n - 2; i >= 0; i--)
            dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - cost[m - 1][i], 1);

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int minPointsOnExit = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minPointsOnExit - cost[i][j], 1);
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[0][0];
    }

    private static int minCost(int[][] grid, int r, int c) {

        if (r < 0 || c < 0)
            return Integer.MAX_VALUE;
        else if (r == 0 && c == 0)
            return grid[r][c];
        else
            return grid[r][c]
                    + Math.min(minCost(grid, r - 1, c), Math.min(minCost(grid, r - 1, c - 1), minCost(grid, r, c - 1)));
    }

}
