package com.amazon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LongestPathInMatrix {

	static int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) {

		int[][] mat = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 19, 18, 17, 16, 15, 14, 13, 12, 11, 10 },
				{ 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 }, { 39, 38, 37, 36, 35, 34, 33, 32, 31, 30 },
				{ 40, 41, 42, 43, 44, 45, 46, 47, 48, 49 }, { 59, 58, 57, 56, 55, 54, 53, 52, 51, 50 },
				{ 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 }, { 79, 78, 77, 76, 75, 74, 73, 72, 71, 70 },
				{ 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 }, { 99, 98, 97, 96, 95, 94, 93, 92, 91, 90 },
				{ 100, 101, 102, 103, 104, 105, 106, 107, 108, 109 },
				{ 119, 118, 117, 116, 115, 114, 113, 112, 111, 110 },
				{ 120, 121, 122, 123, 124, 125, 126, 127, 128, 129 },
				{ 139, 138, 137, 136, 135, 134, 133, 132, 131, 130 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		// bfs(mat);

		int m = mat.length;
		int n = mat[0].length;
		int max = 0;
		int[][] cache = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				cache[i][j] = -1;
			}
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, dfs(mat, i, j, cache));
			}
		}
		
		System.out.println(max);

	}

	private static int dfs(int[][] mat, int i, int j, int[][] cache) {

		if (cache[i][j] != -1)
			return cache[i][j];

		int max = 1;

		for (int k = 0; k < dirs.length; k++) {
			int x = i + dirs[k][0];
			int y = j + dirs[k][1];
			
			if(x < 0 || y < 0 || x >= mat.length || y >= mat[0].length || mat[x][y] <= mat[i][j])
				continue;
			
			max = Math.max(max, 1+dfs(mat, x, y, cache));
		}
		
		cache[i][j] = max;

		return max;
	}

	private static void bfs(int[][] mat) {
		Queue<LCell> q = new LinkedList<>();

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				q.add(new LCell(i, j, 1));
			}
		}

		int max = Integer.MIN_VALUE;
		while (!q.isEmpty()) {

			Queue<LCell> pq = new LinkedList<>();

			pq.add(q.poll());

			while (!pq.isEmpty()) {

				LCell cur = pq.poll();
				int i = cur.x;
				int j = cur.y;
				int d = cur.d;
				int val = mat[i][j];
				max = Math.max(max, d);

				checkAdj(mat, i + 1, j, pq, val, d);
				checkAdj(mat, i - 1, j, pq, val, d);
				checkAdj(mat, i, j + 1, pq, val, d);
				checkAdj(mat, i, j - 1, pq, val, d);

			}

		}

		System.out.println(max);
	}

	private static void checkAdj(int[][] mat, int i, int j, Queue<LCell> pq, int val, int d) {

		if (i >= 0 && j >= 0 && i < mat.length && j < mat[0].length && mat[i][j] > val) {
			// vis[i][j] = true;
			pq.add(new LCell(i, j, d + 1));
		}
	}

}

class LCell {
	int x;
	int y;
	int d;

	public LCell(int i, int j, int k) {
		x = i;
		y = j;
		d = k;
	}

}
