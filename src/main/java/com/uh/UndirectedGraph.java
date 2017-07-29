package com.uh;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class UndirectedGraph {

    int V;
    List<Integer>[] adj;
    int[] indegree;

    public UndirectedGraph(int v) {
        V = v;
        adj = new ArrayList[v];
        indegree = new int[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int s, int d) {
        adj[s].add(d);
        adj[d].add(s);
        indegree[d]++;
    }

    public static void main(String[] args) {
        UndirectedGraph g1 = new UndirectedGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 0);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);

        g1.detectLoop();
        
        g1.detectLoopUnionFind();

    }

    private void detectLoopUnionFind() {

        int[] parent = new int[V];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        
        for (int i = 0; i < adj.length; i++) {
            
        }
    }

    private void detectLoop() {

        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            if (!visited[i])
                System.out.println(DFSUtil(visited, i, -1));
    }

    private boolean DFSUtil(boolean[] visited, int i, int parent) {

        visited[i] = true;

        ListIterator<Integer> listIterator = adj[i].listIterator();
        while (listIterator.hasNext()) {
            int d = listIterator.next();
            if (!visited[i] && DFSUtil(visited, d, parent))
                return true;
            if (d != parent)
                return true;
        }

        return false;

    }

}
