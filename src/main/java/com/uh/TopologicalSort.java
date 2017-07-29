package com.uh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class TopologicalSort {

    int V;
    List<Integer>[] adj;
    int[] indegree;

    public TopologicalSort(int v) {
        V = v;
        adj = new ArrayList[v];
        indegree = new int[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int s, int d) {
        adj[s].add(d);
        indegree[d]++;
    }

    public static void main(String[] args) {
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        g.topologicalSort();
        
        g.topSortDFS();
        
        TopologicalSort g1 = new TopologicalSort(4);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 3);
        g1.addEdge(3, 3);
        g1.detectLoop();

    }

    private void detectLoop() {

        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        
        for(int i=0;i<V;i++)
            if(!visited[i])
                System.out.println(DFSUtilLoop(visited,i,recStack));
        
    }

    private boolean DFSUtilLoop(boolean[] visited, int i, boolean[] recStack) {

        visited[i] = true;
        recStack[i] = true;
        
        ListIterator<Integer> listIterator = adj[i].listIterator();
        while (listIterator.hasNext()) {
            int d = listIterator.next();
            if(!visited[d] && DFSUtilLoop(visited, d, recStack))
                return true;
            else if(recStack[d])
                return true;
        }
        
        recStack[i] = false;
        return false;
    }

    private void topSortDFS() {

        boolean[] visited = new boolean[V];
        
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0;i<V;i++)
            if(!visited[i])
                DFSUtil(visited,i,stack);
        
        int[] dist = new int[V];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = 1000;
        }
        dist[0] = 0;
        
        while(!stack.isEmpty()){
            int s = stack.pop();
            
            if(dist[s]!=1000){
                ListIterator<Integer> listIterator = adj[s].listIterator();
                while(listIterator.hasNext()){
                    int d = listIterator.next();
                    if(dist[d] > s + 1){
                        dist[d] = s + 1;
                    }
                }
            }
        }
        
        for (int i = 0; i < dist.length; i++) {
            System.out.println(dist[i]);
        }
        while(!stack.isEmpty())
            System.out.println(stack.pop());
    }

    private void DFSUtil(boolean[] visited, int i, Stack<Integer> stack) {

        visited[i] = true;
        ListIterator<Integer> listIterator = adj[i].listIterator();
        while (listIterator.hasNext()) {
            int d = listIterator.next();
            if(!visited[d])
                DFSUtil(visited, d, stack);
        }
        stack.push(i);
    }

    private void topologicalSort() {

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        int count = 0;
        List<Integer> result = new ArrayList<>();

        while (!q.isEmpty()) {

            int s = q.poll();
            result.add(s);

            ListIterator<Integer> listIterator = adj[s].listIterator();
            while (listIterator.hasNext()) {
                int d = listIterator.next();

                if (--indegree[d] == 0)
                    q.add(d);
            }
            count++;
        }
        
        if(count != V) 
            System.out.println("Loop");
        
        result.forEach(a -> System.out.println(a));

    }

}
