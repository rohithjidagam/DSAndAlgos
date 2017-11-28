package com.amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class INode {
	int x;
	int y;

	public INode(int i, int j) {
		x = i;
		y = j;
	}

	@Override
	public String toString() {
		return x + "-" + y;
	}
}

public class NoOfDistinctIslands {

	public static void main(String[] args) {

		int[][] grid = { { 1, 1, 0, 1, 1 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1 } };
		NoOfDistinctIslands n = new NoOfDistinctIslands();
		int no = n.numDistinctIslands(grid);
		System.out.println(no);

		int[][] grid2 = { { 1, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 1 } };
		int no2 = n.numDistinctIslands2(grid2);
		System.out.println(no2);
	}

	private int numDistinctIslands2(int[][] grid) {

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] != 0) {
					List<INode> list = new ArrayList<>();
					dfs(grid, i, j, list);
					System.out.println(list);
					computeHash(set, list);
				}
			}
		}

		return set.size();
	}

	private void computeHash(Set<Integer> set, List<INode> list) {

		int hash = 0;
		for (INode iNode : list) {
			hash += Math.abs(iNode.x - iNode.y);
		}
		
		if (!set.contains(hash)) {
			set.add(hash);
		}

	}

	private void dfs(int[][] grid, int i, int j, List<INode> list) {

		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0)
			return;

		grid[i][j] = 0;

		list.add(new INode(i, j));

		dfs(grid, i + 1, j, list);
		dfs(grid, i - 1, j, list);
		dfs(grid, i, j + 1, list);
		dfs(grid, i, j - 1, list);
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
