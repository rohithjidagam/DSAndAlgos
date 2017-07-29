package com.uh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllTopologicalSorts {

    private int V;
    private List<Integer> adj[];
    private int[] indegree;

    public AllTopologicalSorts(int v) {

        this.V = v;
        adj = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int s, int d) {
        adj[s].add(d);
    }

    public static void main(String[] args) {

        AllTopologicalSorts graph = new AllTopologicalSorts(6);

        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        graph.printAllTopSorts();

    }

    private void printAllTopSorts() {

        indegree = new int[V];
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            List<Integer> list = adj[i];
            for (Integer integer : list) {
                indegree[integer]++;
            }
            visited[i] = false;

            List<Integer> res = new LinkedList<>();
            printAllTopSortsUtil(res, visited);
        }

    }

    private void printAllTopSortsUtil(List<Integer> res, boolean[] visited) {

        boolean flag = false;

        for (int i = 0; i < V; i++) {

            if (indegree[i] == 0 && !visited[i]) {
                for (Integer integer : adj[i]) {
                    indegree[integer]--;
                }
                res.add(i);
                visited[i] = true;
                printAllTopSortsUtil(res, visited);
                
                visited[i] = false;
                res.remove(res.size() - 1);
                for (Integer integer : adj[i]) {
                    indegree[integer]++;
                }
                
                flag = true;
                
                
            }
        }
        
        if(!flag){
            for (Integer b : res) {
                System.out.print(b + " ");
            }
            System.out.println();
        }

    }

}
