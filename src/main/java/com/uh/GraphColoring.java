package com.uh;

public class GraphColoring {

    int[] color;
    int V;

    public GraphColoring(int v) {
        V = v;
        color = new int[v];
    }

    public static void main(String[] args) {

        int[][] graph = { { 0, 1, 1, 1 }, { 1, 0, 1, 0 }, { 1, 1, 0, 1 }, { 1, 0, 1, 0 } };
        int colors = 3;

        GraphColoring g = new GraphColoring(graph.length);
        g.graphColoring(graph, colors);
    }

    private boolean graphColoring(int[][] graph, int m) {

        if (!graphColoringUtil(graph, m, 0)) {
            System.out.println("Solution doesn't exist");
            return false;
        }

        for (int i = 0; i < color.length; i++) {
            System.out.println(color[i]);
        }

        return true;
    }

    private boolean graphColoringUtil(int[][] graph, int m, int v) {

        if (v == V)
            return true;

        for (int c = 1; c <= m; c++) {

            if (isSafe(v, graph, c)) {
                color[v] = c;

                if (graphColoringUtil(graph, m, v + 1))
                    return true;

                color[v] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int v, int[][] graph, int c) {

        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1 && c == color[i])
                return false;
        }
        return true;
    }

}
