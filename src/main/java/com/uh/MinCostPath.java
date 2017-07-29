package com.uh;

public class MinCostPath {

    public static void main(String[] args) {

        int[][] grid = { { 31, 100, 65, 12, 18 }, { 10, 13, 47, 157, 6 }, { 100, 113, 174, 11, 33 },
                { 88, 124, 41, 20, 140 }, { 99, 32, 111, 41, 20 } };

        int R = grid.length;
        int C = grid[0].length;

        int n = minCost(grid, R-1, C-1);
        System.out.println(n);
    }

    private static int minCost(int[][] grid, int r, int c) {

        if (r < 0 || c < 0)
            return Integer.MAX_VALUE;
        else if (r == 0 && c == 0)
            return grid[r][c];
        else
            return grid[r][c] + Math.min(minCost(grid, r - 1, c),
                    Math.min(minCost(grid, r - 1, c - 1), minCost(grid, r, c - 1)));
    }

}
