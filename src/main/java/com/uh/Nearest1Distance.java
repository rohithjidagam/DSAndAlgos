package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class Nearest1Distance {

    public static void main(String[] args) {

        int[][] arr = { { 0, 0, 0, 1,0 }, { 0, 0, 1, 1,1 }, { 0, 1, 1, 0,1 },{ 0, 1, 1, 0,1 } };

        distanceOfNeartest1(arr);
    }

    private static void distanceOfNeartest1(int[][] arr) {

        int n = arr.length;
        int m = arr[0].length;
        boolean[][] vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        Queue<QDNode> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    q.add(new QDNode(i, j, 0));
                    vis[i][j] = true;
                    arr[i][j] = 0;
                }
            }

        }

        bfs(q, arr, vis, n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs(Queue<QDNode> q, int[][] arr, boolean[][] vis, int n, int m) {

        while (!q.isEmpty()) {

            QDNode poll = q.poll();
            int x = poll.x;
            int y = poll.y;
            int d = poll.dist;

            checkCell(n, m, x, y + 1, arr, q, d, vis);
            checkCell(n, m, x - 1, y, arr, q, d, vis);
            checkCell(n, m, x + 1, y, arr, q, d, vis);
            checkCell(n, m, x, y - 1, arr, q, d, vis);
        }

    }

    private static void checkCell(int n, int m, int x, int y, int[][] arr, Queue<QDNode> q, int d, boolean[][] vis) {
        if (isValid(x, y, n, m) && !vis[x][y] && arr[x][y] == 0) {
            q.add(new QDNode(x, y, d + 1));
            arr[x][y] = d + 1;
            vis[x][y] = true;
        }
    }

    private static boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && j >= 0 && i < n & j < m;
    }

}

class QDNode {
    public QDNode(int i, int j, int k) {
        x = i;
        y = j;
        dist = k;
    }

    int x;
    int y;
    int dist;
}