package com.uh;

public class Sudoku {

    public static void main(String[] args) {

        // backtracking
        int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

        boolean flag = solveSudoku(grid);
        System.out.println(flag);

        printBoard(grid);

       // grid[3][4] = 4;
        boolean isValid = isValid(grid);
        System.out.println(isValid);
    }

    private static boolean isValid(int[][] grid) {

        int m = grid.length;
        boolean[][] r = new boolean[m][m];
        boolean[][] c = new boolean[m][m];
        boolean[][][] g = new boolean[m][m][m];
        int num;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {

                num = grid[i][j] - 1;
                if (r[i][num])
                    return false;
                r[i][num] = true;

                if (c[j][num])
                    return false;
                c[j][num] = true;

                if (g[i / 3][j / 3][num])
                    return false;
                g[i / 3][j / 3][num] = true;
            }
        }

        return true;
    }

    private static void printBoard(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
                if (j != 0 && (j + 1) % 3 == 0)
                    System.out.print("|");
            }
            if (i != 0 && (i + 1) % 3 == 0) {
                System.out.println();
                for (int k = 0; k < grid[0].length; k++)
                    System.out.print("---");
            }
            System.out.println();
        }
    }

    private static boolean solveSudoku(int[][] grid) {

        SCell c = new SCell();

        if (!findUnassignedLocation(grid, c))
            return true;

        for (int i = 1; i <= 9; i++) {

            if (isSafe(grid, c.r, c.c, i)) {

                grid[c.r][c.c] = i;

                if (solveSudoku(grid))
                    return true;

                // backtrack
                grid[c.r][c.c] = 0;

            }
        }

        return false;
    }

    private static boolean isSafe(int[][] grid, int r, int c, int num) {
        return !checkRow(grid, r, num) && !checkColumn(grid, c, num) && !checkGrid(grid, r - r % 3, c - c % 3, num);
    }

    private static boolean checkGrid(int[][] grid, int i, int j, int num) {

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid[row + i][col + j] == num)
                    return true;
        return false;
    }

    private static boolean checkColumn(int[][] grid, int c, int num) {

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][c] == num)
                return true;
        }
        return false;
    }

    private static boolean checkRow(int[][] grid, int r, int num) {

        for (int i = 0; i < grid.length; i++) {
            if (grid[r][i] == num)
                return true;
        }
        return false;
    }

    private static boolean findUnassignedLocation(int[][] grid, SCell c) {

        for (c.r = 0; c.r < grid.length; c.r++) {
            for (c.c = 0; c.c < grid[c.r].length; c.c++) {
                if (grid[c.r][c.c] == 0)
                    return true;
            }
        }

        return false;
    }

}

class SCell {
    int r;
    int c;
}
