package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class LandMines {

    static int M;
    static int N;

    public static void main(String[] args) {

        int mat[][] = { { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 } };
        M = mat.length;
        N = mat[0].length;
        Point src = new Point(0, 0);
        Point dest = new Point(3, 4);

        int d = shortestPath(mat, src, dest);
        System.out.println(d);

        int mat2[][] = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

        M = mat2.length;
        N = mat2[0].length;
        longestRouteBackTrack(mat2, 0, 0, 0, 2);
        
        Point src2 = new Point(0, 0);
        Point dest2 = new Point(0, 2);
        int d2 = shortestPath(mat2, src2, dest2);
        System.out.println(d2);
        
        longestRouteBackTrack(mat, 0, 0, 3, 4);
    }

    private static void longestRouteBackTrack(int[][] mat, int i, int j, int m, int n) {

        boolean[][] vis = new boolean[M][N];

        Point p = backTrack(mat, i, j, m, n, vis);
        System.out.println(p.d);

    }

    private static Point backTrack(int[][] mat, int i, int j, int m, int n, boolean[][] vis) {

        if (i == m && j == n) {
            Point p = new Point(0, true);
            return p;
        }

        if (i < 0 || i >= M || j < 0 || j >= N || mat[i][j] == 0 || vis[i][j]) {
            Point p = new Point(Integer.MAX_VALUE, false);
            return p;
        }

        vis[i][j] = true;
        int res = Integer.MIN_VALUE;

        Point p = backTrack(mat, i, j - 1, m, n, vis);
        if (p.found) {
            res = Math.max(res, p.d);
        }

        p = backTrack(mat, i, j + 1, m, n, vis);
        if (p.found) {
            res = Math.max(res, p.d);
        }

        p = backTrack(mat, i + 1, j, m, n, vis);
        if (p.found) {
            res = Math.max(res, p.d);
        }

        p = backTrack(mat, i - 1, j, m, n, vis);
        if (p.found) {
            res = Math.max(res, p.d);
        }

        vis[i][j] = false;

        if (res != Integer.MIN_VALUE) {
            Point p1 = new Point(1 + res, true);
            return p1;
        } else {
            Point p1 = new Point(Integer.MAX_VALUE, false);
            return p1;
        }
    }

    private static int shortestPath(int[][] mat, Point src, Point dest) {

        boolean[][] visited = new boolean[M][N];
        Queue<PNode> q = new LinkedList<>();

        visited[src.x][src.y] = true;
        q.add(new PNode(src, 0));

        while (!q.isEmpty()) {

            PNode node = q.poll();
            int i = node.p.x;
            int j = node.p.y;
            int d = node.d;

            if (i == dest.x && j == dest.y) {
                return d;
            }
            check(mat, visited, q, i + 1, j, d);
            check(mat, visited, q, i - 1, j, d);
            check(mat, visited, q, i, j + 1, d);
            check(mat, visited, q, i, j - 1, d);
        }

        return 0;
    }

    private static void check(int[][] mat, boolean[][] visited, Queue<PNode> q, int i, int j, int d) {
        if (i >= 0 && j >= 0 && i < M && j < N && mat[i][j] != 0 && !visited[i][j]) {
            visited[i][j] = true;
            q.add(new PNode(new Point(i, j), 1 + d));
        }
    }

}

class Point {
    int x;
    int y;

    int d;
    boolean found;

    Point(int d, boolean found) {
        this.d = d;
        this.found = found;
    }

    Point(int i, int j) {
        x = i;
        y = j;
    }
}

class PNode {

    Point p;
    int d;

    public PNode(Point p, int d) {
        this.p = p;
        this.d = d;
    }
}
