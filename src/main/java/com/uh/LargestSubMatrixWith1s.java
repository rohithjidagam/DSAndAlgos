package com.uh;

public class LargestSubMatrixWith1s {

    public static void main(String[] args) {

        int[][] mat = { { 0, 1, 1, 0, 1 }, { 1, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0 } };

        int R = mat.length;
        int C = mat[0].length;

        int[][] sol = new int[R][C];

        for (int i = 0; i < R; i++) {
            sol[i][0] = mat[i][0];
        }

        for (int i = 0; i < C; i++) {
            sol[0][i] = mat[0][i];
        }

        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (mat[i][j] == 0) {
                    sol[i][j] = 0;
                } else {
                    sol[i][j] = 1 + Math.min(sol[i - 1][j], Math.min(sol[i][j - 1], sol[i - 1][j - 1]));
                }
            }
        }
        printMatrix(R, C, sol);
        
        int max = sol[0][0];
        int max_i = 0;
        int max_j = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
         
                if(sol[i][j] > max){
                    max = sol[i][j];
                    max_i = i;
                    max_j = j;
                }
                
            }
        }
        
        System.out.println(max_i);
        System.out.println(max_j);
        System.out.println(max);
        
        for (int i = max_i; i > max_i - max; i--) {
            for (int j = max_j; j > max_j - max; j--) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        
    }

    private static void printMatrix(int R, int C, int[][] sol) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

}
