package com.uh;

public class BellmanFord {

    class Edge {
        int src, dest, weight;

        Edge() {
            src = dest = weight = 0;
        }
    };

    int V, E;
    Edge edge[];

    // Creates a graph with V vertices and E edges
    BellmanFord(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    public static void main(String[] args) {

        int V = 5; // Number of vertices in graph
        int E = 8; // Number of edges in graph

        BellmanFord graph = new BellmanFord(V, E);

        // add edge 0-1 (or A-B in above figure)
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = -1;

        // add edge 0-2 (or A-C in above figure)
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        // add edge 1-2 (or B-C in above figure)
        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;

        // add edge 1-3 (or B-D in above figure)
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;

        // add edge 1-4 (or A-E in above figure)
        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;

        // add edge 3-2 (or D-C in above figure)
        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;

        // add edge 3-1 (or D-B in above figure)
        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;

        // add edge 4-3 (or E-D in above figure)
        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -3;

        graph.BellmanFord(graph, 0);

    }

    private void BellmanFord(BellmanFord graph, int i) {
        // TODO Auto-generated method stub

        int dist[] = new int[graph.V];

        for (int j = 0; j < graph.V; j++) {
            dist[j] = Integer.MAX_VALUE;
        }

        dist[i] = 0;

        for (int j = 1; j < graph.V; j++) {

            for (int j2 = 0; j2 < graph.E; j2++) {

                int s = graph.edge[j2].src;
                int d = graph.edge[j2].dest;
                int w = graph.edge[j2].weight;

                if (dist[s] != Integer.MAX_VALUE && dist[d] > dist[s] + w) {
                    dist[d] = dist[s] + w;
                }

            }

        }
        
        for (int j2 = 0; j2 < graph.E; j2++) {

            int s = graph.edge[j2].src;
            int d = graph.edge[j2].dest;
            int w = graph.edge[j2].weight;

            if (dist[s] != Integer.MAX_VALUE && dist[d] > dist[s] + w) {
                System.out.println("Negative weight cycle");
            }

        }
        
        for (int j = 0; j < graph.V; j++) {
            System.out.println(j + "-->" + dist[j]);
        }
    }

}
