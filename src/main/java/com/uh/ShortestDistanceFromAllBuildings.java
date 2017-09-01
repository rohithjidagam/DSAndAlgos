package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings {

    public static void main(String[] args) {

        int[][] grid = { { 1, 0, 2, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } };
        ShortestDistanceFromAllBuildings s = new ShortestDistanceFromAllBuildings();
        int min = s.shortestDistance(grid);
        System.out.println(min);
    }

    public int shortestDistance(int[][] grid) {

        int m = grid.length;
        if (m == 0)
            return -1;
        int n = grid[0].length;
        Queue<SDCell> q = new LinkedList<SDCell>();
        int num = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.add(new SDCell(i, j, 1));
                    num++;
                }
            }
        }

        int[][] dist = new int[m][n];
        int[][] reach = new int[m][n];

        while (!q.isEmpty()) {
            boolean[][] vis = new boolean[m][n];
            Queue<SDCell> adj = new LinkedList<SDCell>();
            adj.add(q.poll());
            while (!adj.isEmpty()) {
                SDCell poll = adj.poll();
                int i = poll.i;
                int j = poll.j;
                int d = poll.d;

                if (isSafe(i + 1, j, vis, grid)) {
                    updateDist(dist, reach, vis, adj, i + 1, j, d);
                }

                if (isSafe(i - 1, j, vis, grid)) {
                    updateDist(dist, reach, vis, adj, i - 1, j, d);
                }

                if (isSafe(i, j + 1, vis, grid)) {
                    updateDist(dist, reach, vis, adj, i, j+1, d);
                }

                if (isSafe(i, j - 1, vis, grid)) {
                    updateDist(dist, reach, vis, adj, i, j - 1, d);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(reach[i][j] + " ");
            }
            System.out.println();
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == num) {
                    min = Math.min(min, dist[i][j]);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }

    private void updateDist(int[][] dist, int[][] reach, boolean[][] vis, Queue<SDCell> adj, int i, int j, int d) {
        dist[i][j] += d;
        reach[i][j]++;
        vis[i][j] = true;
        adj.add(new SDCell(i, j, d + 1));
    }

    boolean isSafe(int i, int j, boolean[][] vis, int[][] grid) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && !vis[i][j] && grid[i][j] == 0;
    }
}

class SDCell {
    int i;
    int j;
    int d;

    public SDCell(int x, int y, int k) {
        i = x;
        j = y;
        d = k;
    }
}
