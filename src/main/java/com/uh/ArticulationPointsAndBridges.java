package com.uh;

import java.util.LinkedList;
import java.util.ListIterator;

public class ArticulationPointsAndBridges {

    private int V;
    private int time;
    private LinkedList<Integer>[] adjList;

    public ArticulationPointsAndBridges(int v) {
        this.V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    private void addEdge(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
    }

    public static void main(String[] args) {

        ArticulationPointsAndBridges g1 = new ArticulationPointsAndBridges(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);

        g1.bridge();
    }

    private void bridge() {

        boolean vis[] = new boolean[V];
        int[] parent = new int[V];
        int[] disc = new int[V];
        int[] low = new int[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i])
                DFS(vis, parent, disc, low, i);
        }
    }

    private void DFS(boolean[] vis, int[] parent, int[] disc, int[] low, int u) {

        vis[u] = true;
        disc[u] = low[u] = ++time;

        ListIterator<Integer> listIterator = adjList[u].listIterator();
        while (listIterator.hasNext()) {
            int v = listIterator.next();

            if (!vis[v]) {
                parent[v] = u;
                DFS(vis, parent, disc, low, v);

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    System.out.println(u + "-" + v);
                }
            } else if(v != parent[u])
                low[u] = Math.min(low[u], disc[v]);
        }

    }

}
