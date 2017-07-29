package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class GaurdsBanksAndWalls {

    public static void main(String[] args) {

        char matrix[][] = { { 'O', 'O', 'O', 'O', 'G' }, { 'O', 'W', 'W', 'O', 'O' }, { 'O', 'O', 'O', 'W', 'O' },
                { 'G', 'W', 'W', 'W', 'O' }, { 'O', 'O', 'O', 'O', 'G' } };

        findDistances(matrix);
    }

    private static void findDistances(char[][] matrix) {

        Queue<QNode> q = new LinkedList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dist = new int[m][n];
        
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = -1;
                if (matrix[i][j] == 'G') {
                    q.add(new QNode(i, j, 0));
                    dist[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {

            QNode qNode = q.poll();
            int x = qNode.i;
            int y = qNode.j;
            int d = qNode.dist;

            if (isValid(x + 1, y) && isSafe(matrix[x + 1][y], dist[x + 1][y])) {
                dist[x + 1][y] = 1 + d;
                q.add(new QNode(x + 1, y, dist[x + 1][y]));
            }

            if (isValid(x - 1, y) && isSafe(matrix[x - 1][y], dist[x - 1][y])) {
                dist[x - 1][y] = 1 + d;
                q.add(new QNode(x - 1, y, dist[x - 1][y]));
            }

            if (isValid(x, y + 1) && isSafe( matrix[x][y + 1], dist[x][y + 1])) {
                dist[x][y + 1] = 1 + d;
                q.add(new QNode(x, y + 1, dist[x][y + 1]));
            }

            if (isValid(x, y - 1) && isSafe(matrix[x][y - 1], dist[x][y - 1])) {
                dist[x][y - 1] = 1 + d;
                q.add(new QNode(x, y - 1, dist[x][y - 1]));
            }

        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static boolean isSafe(char c, int d) {
        if (c != 'O' || d != -1)
            return false;
        return true;
    }

    private static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < 5 && j < 5;
    }

}

class QNode {
    int i, j, dist;

    public QNode(int x, int y, int d) {
        i = x;
        j = y;
        dist = d;
    }
}
