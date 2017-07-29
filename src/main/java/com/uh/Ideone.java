package com.uh;

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */

class Ideone {

    public static void main(String[] args) {
        int mat[][] = { { 0, 0, 1, 0, 0 }, { 1, 0, 1, 0, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0 } };
        System.out.println(hasSol(mat, 0, 0, 3, 4));
    }

    private static int hasSol(int[][] mat, int x, int y, int eR, int eC) {
        int r, c;
        if (mat.length == 0)
            return -1;
        r = mat.length;
        c = mat[0].length;

        int MAX = r * c;
        int steps[][] = new int[r][c];

        // Initialize steps with index(0,0) to 0 and rest to MAX
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (i == 0 && j == 0)
                    steps[i][j] = 0;
                else
                    steps[i][j] = MAX;

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                assignMinSteps(mat, i, j, r, c, steps);

        for (int i = r - 1; i >= 0; i--)
            for (int j = c - 1; j >= 0; j--)
                assignMinSteps(mat, i, j, r, c, steps);

        // replace MAX with -1;
        replace(steps, r, c);
        return steps[eR][eC];
    }

    private static void assignMinSteps(int[][] mat, int x, int y, int r, int c, int[][] steps) {

        int MAX = r * c;
        if (!isSafe(mat, x, y, r, c)) {
            steps[x][y] = MAX;
            return;
        }
        int min = MAX;
        // Assign Left to min if it's valid and less than min
        if (isSafe(mat, x - 1, y, r, c) && steps[x - 1][y] < min)
            min = steps[x - 1][y];
        // Assign Right to min if it's valid and less than min
        if (isSafe(mat, x + 1, y, r, c) && steps[x + 1][y] < min)
            min = steps[x + 1][y];
        // Assign Top to min if it's valid and less than min
        if (isSafe(mat, x, y - 1, r, c) && steps[x][y - 1] < min)
            min = steps[x][y - 1];
        // Assign Bottom to min if it's valid and less than min
        if (isSafe(mat, x, y + 1, r, c) && steps[x][y + 1] < min)
            min = steps[x][y + 1];

        if (steps[x][y] > min + 1)
            steps[x][y] = min + 1;

    }

    private static boolean isSafe(int mat[][], int x, int y, int r, int c) {
        return (x >= 0 && x < r && y >= 0 && y < c && mat[x][y] == 0);

    }

    private static void replace(int[][] m, int r, int c) {
        int MAX = r * c;
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (m[i][j] == MAX)
                    m[i][j] = -1;

    }

}