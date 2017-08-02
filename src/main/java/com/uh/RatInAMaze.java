package com.uh;

public class RatInAMaze {

    static int M = 4;
    static int N = 4;

    public static void main(String[] args) {

        int mat[][] = { { 1, 1, 1, 1 }, { 1, 0, 1, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 } };

        System.out.println(hasSol(mat));

        System.out.println(countPaths(mat));
    }

    private static int countPaths(int[][] maze) {

        int[][] dp = new int[M][N];

        if (maze[0][0] == 0)
            return 0;

        dp[0][0] = 1;

        for (int i = 1; i < N; i++)
            if (maze[0][i] != 0)
                dp[0][i] = dp[0][i - 1];

        for (int i = 1; i < M; i++)
            if (maze[i][0] != 0)
                dp[i][0] = dp[i - 1][0];

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (maze[i][j] != 0)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        print(dp);
        return dp[M-1][N-1];

    }

    private static boolean hasSol(int[][] mat) {

        int sol[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        boolean flag = false;
        if (mazeUtil(mat, 0, 0, sol)) {
            flag = true;
        }
        print(sol);
        return flag;

    }

    private static void print(int[][] sol) {
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol[i].length; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean mazeUtil(int[][] mat, int i, int j, int[][] sol) {

        if (i == M - 1 && j == N - 1) {
            sol[i][j] = 1;
            return true;
        }

        if (isSafe(mat, i, j)) {

            sol[i][j] = 1;

            if (mazeUtil(mat, i + 1, j, sol))
                return true;

            if (mazeUtil(mat, i, j + 1, sol))
                return true;

            // backtrack
            sol[i][j] = 0;
        }

        return false;
    }

    static boolean isSafe(int mat[][], int x, int y) {
        return (x >= 0 && x < M && y >= 0 && y < N && mat[x][y] == 1);
    }

}
