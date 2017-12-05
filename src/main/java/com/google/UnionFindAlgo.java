package com.google;

/**
 * Detect cycle in undirected graph.
 * 
 * @author Rohith
 *
 */
public class UnionFindAlgo {

    public static void main(String[] args) {

        UnionFindAlgo g = new UnionFindAlgo();
        int V = 3;
        int E = 3;
        Edge[] edges = new Edge[E];
        edges[0] = new Edge(0, 1);
        edges[1] = new Edge(1, 2);
        edges[2] = new Edge(0, 2);

        unionFind(g, V, edges);
        System.out.println();
        
        unionFindWithPathCompression(g, V, edges);

    }

    private static void unionFindWithPathCompression(UnionFindAlgo g, int v, Edge[] edges) {

        SubSets[] subsets = new SubSets[v];
        for (int i = 0; i < subsets.length; i++) {
            subsets[i] = new SubSets(i, 0);
        }
        
        for (Edge e : edges) {

            int x = g.findByPC(subsets, e.st);
            int y = g.findByPC(subsets, e.end);

            if (x == y) {
                System.out.println("Cycle Exists.");
                break;
            }

            g.unionByPC(subsets, x, y);

        }

        for (int i = 0; i < subsets.length; i++) {
            System.out.println(subsets[i].parent  + "-" + subsets[i].rank);
        }
        
        
    }

    private void unionByPC(SubSets[] subsets, int i, int j) {

        int x = findByPC(subsets, i);
        int y = findByPC(subsets, j);
        
        if(subsets[x].rank < subsets[y].rank)
            subsets[x].parent = y;
        else if(subsets[y].rank < subsets[x].rank)
            subsets[y].parent = x;
        else{
            subsets[y].parent = x;
            subsets[x].rank++;
        }
    }

    private int findByPC(SubSets[] subsets, int i) {
        if(subsets[i].parent != i)
            subsets[i].parent = findByPC(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    private static void unionFind(UnionFindAlgo g, int V, Edge[] edges) {
        int[] parent = new int[V];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }

        for (Edge e : edges) {

            int x = g.find(parent, e.st);
            int y = g.find(parent, e.end);

            if (x == y) {
                System.out.println("Cycle Exists.");
                break;
            }

            g.union(parent, x, y);

        }

        for (int i = 0; i < parent.length; i++) {
            System.out.print(parent[i] + " ");
        }
    }

    private void union(int[] parent, int i, int j) {
        int x = find(parent, i);
        int y = find(parent, j);
        parent[x] = y;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

}

class Edge {
    int st;
    int end;

    public Edge(int i, int j) {
        st = i;
        end = j;
    }
}

class SubSets {
    int parent;
    int rank;

    public SubSets(int p, int r) {
        parent = p;
        rank = r;
    }
}
