package com.amazon;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

	public static void main(String[] args) {

		int arr[][] = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 1, 0, 2, 1 } };

		int m = arr.length;
		int n = arr[0].length;

		Queue<Orange> q = new LinkedList<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 2) {
					q.add(new Orange(i, j));
				}
			}
		}
		boolean[][] vis = new boolean[m][n];
		int minTime = 0;
		while (!q.isEmpty()) {

			int size = q.size();

			boolean flag = checkArray(arr);
			if (!flag)
				break;

			while (size-- > 0) {

				Orange poll = q.poll();
				int i = poll.x;
				int j = poll.y;

				checkAdj(arr, i + 1, j, q, vis);
				checkAdj(arr, i - 1, j, q, vis);
				checkAdj(arr, i, j + 1, q, vis);
				checkAdj(arr, i, j - 1, q, vis);

			}
			minTime++;
		}

		print(arr, m, n);

		System.out.println(minTime);

	}

	private static boolean checkArray(int[][] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1)
					return true;
			}
		}

		return false;
	}

	private static void print(int[][] arr, int m, int n) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void checkAdj(int[][] arr, int i, int j, Queue<Orange> pq, boolean[][] vis) {

		if (i >= 0 && j >= 0 && i < arr.length && j < arr[0].length && !vis[i][j] && arr[i][j] == 1) {
			vis[i][j] = true;
			pq.add(new Orange(i, j));
			arr[i][j] = 2;
		}
	}

}

class Orange {
	int x;
	int y;

	public Orange(int i, int j) {
		x = i;
		y = j;
	}
}
