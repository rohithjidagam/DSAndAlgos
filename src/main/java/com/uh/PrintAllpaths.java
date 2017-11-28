package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintAllpaths {

	static final int rC = 3;
	static final int cC = 3;

	public static void main(String[] args) {

		int mat[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		Integer[] path = new Integer[5];

		print(mat, 0, 0, path, 0);

		printPaths(mat, new ArrayList<>(), 0, 0);

		count(rC, cC);

		int points[][] = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };

		reachDestination(points);

		countZeros();

	}

	private static void printPaths(int[][] mat, List<Integer> temp, int i, int j) {

		if (i >= mat.length || j >= mat[i].length)
			return;
		
		if (i == mat.length - 1 && j == mat[i].length - 1) {
			System.out.println(Arrays.deepToString(temp.toArray()));
			return;
		}

		temp.add(mat[i][j]);
		printPaths(mat, temp, i+1, j);
		printPaths(mat, temp, i, j+1);
		temp.remove(temp.size() - 1);
	}

	private static void countZeros() {

		int mat[][] = { { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 1 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };

		int r = mat.length;
		int c = mat[0].length;

		int i = 0;
		int j = c - 1;
		int count = 0;
		int n = r - 1;

		while (j >= 0 && i < r) {

			if (mat[i][j] != 0) {
				j--;
			} else {
				count += (j + 1);
				i++;
			}
		}
		System.out.println(count);
	}

	private static void reachDestination(int[][] points) {

		int[][] dp = new int[rC][];

		// dp[rC - 1][cC - 1] = points[rC - 1][cC - 1] > 0 ? 1 :
		// Math.abs(points[rC - 1][cC - 1]) + 1;

		for (int i = rC - 2; i >= 0; i--) {
			// dp[i][cC - 1] = Math.max(dp[i + 1][cC - 1] - points[i][cC - 1],
			// 1);
		}
	}

	private static void count(int r, int c) {

		int[][] mat = new int[r][c];

		for (int i = 0; i < c; i++) {
			mat[0][i] = 1;
		}

		for (int i = 0; i < r; i++) {
			mat[i][0] = 1;
		}

		for (int i = 1; i < r; i++) {
			for (int j = 1; j < c; j++) {
				mat[i][j] = mat[i - 1][j] + mat[i][j - 1];// + mat[i - 1][j -
															// 1];
			}
		}

		System.out.println(mat[r - 1][c - 1]);

	}

	private static void print(int[][] mat, int r, int c, Integer[] path, int index) {

		if (r >= rC || c >= cC)
			return;

		if (r == rC - 1 && c == cC - 1) {
			path[index] = mat[r][c];
			System.out.println(Arrays.deepToString(path));
			return;
		}

		path[index] = mat[r][c];

		print(mat, r + 1, c, path, index + 1);
		print(mat, r, c + 1, path, index + 1);
		// print(mat, r + 1, c + 1, path, index + 1);

	}

}
