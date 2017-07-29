package com.uh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimsAlgo {

    public static void main(String[] args) {

        int V = 9;

        PrimNode g = new PrimNode(V);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        g.primMST();

    }

}

class PrimNode {
    int V;
    List<IPair>[] adj;

    public PrimNode(int v) {
        V = v;
        adj = new LinkedList[9];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<IPair>();
        }
    }

    public void addEdge(int s, int d, int w) {
        adj[s].add(new IPair(d, w));
        adj[d].add(new IPair(s, w));
    }

    public void primMST() {

        Queue<IPair> pq = new PriorityQueue<>();
        int src = 0;
        int[] keys = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            keys[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            visited[i] = false;
        }

        pq.add(new IPair(0, src));
        keys[src] = 0;

        while (!pq.isEmpty()) {

            int u = pq.poll().i;

            visited[u] = true;

            ListIterator<IPair> listIterator = adj[u].listIterator();
            while (listIterator.hasNext()) {
                IPair next = listIterator.next();
                int v = next.i;
                int w = next.j;

                if (!visited[v] && keys[v] > w) {
                    keys[v] = w;
                    pq.add(new IPair(v, w));
                    parent[v] = u;
                }
            }

        }

        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + "--" + i);
        }
    }

}

class IPair implements Comparable<IPair> {
    int i;
    int j;

    IPair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public int compareTo(IPair o) {
        return i > o.i ? 1 : -1;
    }

    @Override
    public String toString() {
        return i + "-" + j;
    }
}
