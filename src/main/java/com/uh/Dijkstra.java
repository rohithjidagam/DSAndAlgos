package com.uh;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Dijkstra {

    // adjacency matrix
    private int distance[];
    private Set<Integer> settled;
    private Queue<GraphNode> priorityQueue;
    private int number_of_nodes;

    // adjacency list
    int V;
    List<GraphNode>[] adj;

    public Dijkstra(int n) {
        this.number_of_nodes = n;
        distance = new int[n + 1];
        settled = new HashSet<>();
        priorityQueue = new PriorityQueue<>();
        this.V = n;
        adj = new LinkedList[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) {

        // adjacency matrix
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        Dijkstra dijkstra = new Dijkstra(9);
        dijkstra.dijkstra(graph, 0);
        System.out.println("adjacency matrix");
        for (int i = 0; i < 9; i++) {
            System.out.println(i + "-->" + dijkstra.distance[i]);
        }
        System.out.println("adjacency list");
        // adjacency list
        int V = 9;
        Dijkstra g = new Dijkstra(V);
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

        g.shortestPath(0);

    }

    private void shortestPath(int s) {

        printAdjList();

        int[] dist = new int[V];
        int[] parent = new int[V];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<GraphNode> pq = new PriorityQueue<>();

        pq.add(new GraphNode(s, 0));
        dist[s] = 0;
        parent[s] = -1;

        while (!pq.isEmpty()) {

            GraphNode node = pq.poll();
            int src = node.node;

            ListIterator<GraphNode> listIterator = adj[src].listIterator();
            while (listIterator.hasNext()) {
                GraphNode next = listIterator.next();
                int dest = next.node;
                int weight = next.cost;
                if (dist[dest] > dist[src] + weight) {
                    dist[dest] = dist[src] + weight;
                    parent[dest] = src;
                    pq.add(new GraphNode(dest, dist[dest]));
                }
            }

        }

        for (int i = 0; i < dist.length; i++) {
            System.out.print(0 + "-->" + i + "--" + dist[i] + " ----->");
            printParent(parent, i);
            System.out.println();
        }
        
        
    }
    
    void printParent(int[] p,int i){
        if(p[i] == -1)
            return;
        
        printParent(p, p[i]);
        System.out.print(i + " ");
    }

    private void printAdjList() {
        for (int i = 0; i < adj.length; i++) {
            ListIterator<GraphNode> listIterator = adj[i].listIterator();
            System.out.print(i + "-->");
            while (listIterator.hasNext()) {
                System.out.print(listIterator.next().node + " ");
            }
            System.out.println();
        }
    }

    private void addEdge(int i, int j, int k) {
        adj[i].add(new GraphNode(j, k));
        adj[j].add(new GraphNode(i, k));
    }

    private void dijkstra(int[][] graph, int s) {

        for (int i = 0; i < number_of_nodes; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        priorityQueue.add(new GraphNode(s, 0));

        distance[s] = 0;

        while (!priorityQueue.isEmpty()) {
            GraphNode node = priorityQueue.remove();
            settled.add(node.node);
            evaluateNeighbours(node, graph);
        }

    }

    private void evaluateNeighbours(GraphNode node, int[][] graph) {

        int newDist = -1;

        for (int i = 1; i < number_of_nodes; i++) {
            if (!settled.contains(i)) {
                if (graph[node.node][i] != 0) {
                    newDist = graph[node.node][i] + distance[node.node];

                    if (newDist < distance[i]) {
                        distance[i] = newDist;
                    }
                    priorityQueue.add(new GraphNode(i, distance[i]));
                }
            }
        }
    }

}

class GraphNode implements Comparable<GraphNode> {
    public int node;
    public int cost;

    public GraphNode() {
    }

    public GraphNode(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(GraphNode other) {
        return cost < other.cost ? -1 : cost > other.cost ? 1 : 0;
    }
}
