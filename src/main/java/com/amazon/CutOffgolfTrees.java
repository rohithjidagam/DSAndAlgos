package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CutOffgolfTrees {

	public static void main(String[] args) {

		CutOffgolfTrees g = new CutOffgolfTrees();
		List<List<Integer>> forest = new ArrayList<>();
		forest.add(Arrays.asList(1, 3, 2));
		forest.add(Arrays.asList(0, 0, 4));
		forest.add(Arrays.asList(7, 6, 5));
		int min = g.cutOffGolfCell(forest);
		System.out.println(min);

	}

	public int cutOffGolfCell(List<List<Integer>> forest) {

		if (forest.size() == 0 || forest.get(0).size() == 0)
			return 0;
		int m = forest.size();
		int n = forest.get(0).size();
		int total = 0;
		PriorityQueue<GolfCell> queue = new PriorityQueue<GolfCell>((a, b) -> a.h - b.h);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (forest.get(i).get(j) > 1) {
					queue.add(new GolfCell(forest.get(i).get(j), i, j));
				}
			}
		}
		GolfCell cur = new GolfCell(1, 0, 0);
		while (!queue.isEmpty()) {
			GolfCell next = queue.poll();
			int steps = bfs(forest, cur.i, cur.j, next);
			if (steps == -1)
				return -1;
			total += steps;
			cur = next;
		}
		return total;

	}

	int bfs(List<List<Integer>> forest, int x, int y, GolfCell next) {
		int m = forest.size();
		int n = forest.get(0).size();
		boolean[][] visited = new boolean[m][n];
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { x, y });
		visited[x][y] = true;
		int steps = 0;
		int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				if (cur[0] == next.i && cur[1] == next.j)
					return steps;
				for (int[] d : dir) {
					int nx = cur[0] + d[0];
					int ny = cur[1] + d[1];
					if (nx >= 0 && nx < m && ny >= 0 && ny < n && forest.get(nx).get(ny) != 0 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						queue.add(new int[] { nx, ny });
					}
				}
			}
			steps++;
		}
		return -1;

	}

	void checkCell(int i, int j, boolean[][] vis, List<List<Integer>> forest, Queue<GolfCell> q) {
		if (i >= 0 && j >= 0 && i < forest.size() && j < forest.get(i).size() && forest.get(i).get(j) != 0
				&& !vis[i][j]) {
			vis[i][j] = true;
			q.add(new GolfCell(i, j));
		}
	}
}

class GolfCell {
	int i;
	int j;
	int h;

	public GolfCell(int d, int x, int y) {
		i = x;
		j = y;
		h = d;
	}

	public GolfCell(int x, int y) {
		i = x;
		j = y;
	}

	@Override
	public String toString() {
		return i + "--" + j + "--" + h;
	}
}