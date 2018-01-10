package com.google;

public class BombEnemy {

    public static void main(String[] args) {

        char[][] grid = { { 'O', 'E', 'O', 'O' }, { 'E', 'O', 'W', 'E' }, { 'O', 'E', 'O', 'O' } };

        int n = bombEnemy(grid);
        System.out.println(n);
    }

    private static int bombEnemy(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int row = 0;
        int[] cols = new int[n];
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W')
                    continue;
                if (j == 0 || grid[i][j - 1] == 'W')
                    row = rows(grid, i, j);
                if (i == 0 || grid[i - 1][j] == 'W')
                    cols[j] = cols(grid, i, j);

                System.out.println("[" +i + "," + j + "]  -->  " + row + "--" + cols[j] );
                if (grid[i][j] == 'O')
                    max = Math.max(max, row + cols[j]);
            }
        }
        return max;
    }

    private static int cols(char[][] grid, int i, int j) {
        int num = 0;
        while(i < grid.length && grid[i][j] != 'W'){
            if(grid[i][j] == 'E')
                num++;
            i++;
        }
        return num;
    }

    private static int rows(char[][] grid, int i, int j) {
        int num = 0;
        while(j < grid[0].length && grid[i][j] != 'W'){
            if(grid[i][j] == 'E')
                num++;
            j++;
        }
        return num;
    }

}
