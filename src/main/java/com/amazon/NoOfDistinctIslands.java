package com.amazon;

import java.util.HashSet;
import java.util.Set;

public class NoOfDistinctIslands {

	public static void main(String[] args) {

		int[][] grid = { { 1, 1, 0, 1, 1 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1 } };
		NoOfDistinctIslands n = new NoOfDistinctIslands();
		int no = n.numDistinctIslands(grid);
		System.out.println(no);
	}

	private int numDistinctIslands(int[][] grid) {

		int m = grid.length;
		int n = grid[0].length;
		StringBuilder sb = null;
		Set<String> set = new HashSet<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					sb = new StringBuilder();
					dfs(grid, i, j, sb, "o");
					System.out.println(sb.toString());
					set.add(sb.toString());
				}
			}
		}
		return set.size();
	}

	private void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {

		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0)
			return;

		sb.append(dir);
		grid[i][j] = 0;

		dfs(grid, i - 1, j, sb, "u");
		dfs(grid, i + 1, j, sb, "d");
		dfs(grid, i, j + 1, sb, "r");
		dfs(grid, i, j - 1, sb, "l");

		sb.append("b");

	}

}
