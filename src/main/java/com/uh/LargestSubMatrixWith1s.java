package com.uh;

import java.util.Stack;

public class LargestSubMatrixWith1s {

    public static void main(String[] args) {

        int[][] mat = { { 0, 1, 1, 0, 1 }, { 1, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0 } };

        int R = mat.length;
        int C = mat[0].length;

        printMatrix(R, C, mat);

        largestSquareMatrix(mat, R, C);

        maxAreaMatrix(R, C, mat);

    }

    private static void maxAreaMatrix(int R, int C, int[][] mat) {

        int result = maxArea(mat[0]);

        for (int i = 1; i < R; i++) {

            for (int j = 0; j < C; j++) {
                if (mat[i][j] == 1)
                    mat[i][j] += mat[i - 1][j];
            }
            result = Math.max(result, maxArea(mat[i]));

        }

        System.out.println("Max area ::" + result);
    }

    private static int maxArea(int[] arr) {

        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int i = 0;
        int max = Integer.MIN_VALUE;
        int area = 0;

        while (i < n) {
            if (stack.isEmpty() || arr[stack.peek()] <= arr[i])
                stack.push(i++);
            else {
                int top = arr[stack.pop()];
                area = top * i;

                if (!stack.isEmpty())
                    area = top * (i - stack.peek() - 1);
                max = Math.max(max, area);
            }

        }

        while (!stack.isEmpty()) {
            int top = arr[stack.pop()];
            area = top * i;

            if (!stack.isEmpty())
                area = top * (i - stack.peek() - 1);
            max = Math.max(max, area);
        }

        return max;
    }

    private static void largestSquareMatrix(int[][] mat, int R, int C) {
        int[][] sol = new int[R][C];

        for (int i = 0; i < R; i++) {
            sol[i][0] = mat[i][0];
        }

        for (int i = 0; i < C; i++) {
            sol[0][i] = mat[0][i];
        }

        int max = sol[0][0];
        int max_i = 0;
        int max_j = 0;

        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (mat[i][j] == 0) {
                    sol[i][j] = 0;
                } else {
                    sol[i][j] = 1 + Math.min(sol[i - 1][j], Math.min(sol[i][j - 1], sol[i - 1][j - 1]));
                    if (sol[i][j] > max) {
                        max = sol[i][j];
                        max_i = i;
                        max_j = j;
                    }
                }
            }
        }
        printMatrix(R, C, sol);

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
        System.out.println("******************");
    }

}
