package com.uh;

public class AllPairsShortestPath {

    public static void main(String[] args) {

        int[][] graph = { { 0, 2, 0, 4, 0 }, { 2, 0, 1, 0, 0 }, { 0, 1, 0, 3, 0 }, { 4, 0, 3, 0, 1 },
                { 0, 0, 0, 1, 0 } };

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("**************");

        shortestPath(graph);
    }

    public static int[][] shortestPath(int[][] graph) {

        int n = graph.length;

        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == 0)
                    dist[i][j] = 9999;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }

        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > i)
                    System.out.print(dist[i][j] + " ");
                else
                    System.out.print(0 + " ");

            }
            System.out.println();
        }

        int s = 1;
        int f = 5;
        int w = 2;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // min = Math.min(min, Math.min(dist[s - 1][f - 1], Math.min(dist[s
            // - 1][i] + w, dist[i][f - 1] + w)));
        }

        // if (graph[s - 1][f - 1] == 0) {
        // System.out.println(Math.min(min, w));

        // }
        // System.out.println(min);
        return dist;
    }

}
