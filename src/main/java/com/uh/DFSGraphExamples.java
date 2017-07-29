package com.uh;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class DFSGraphExamples {

    LinkedList<Integer>[] graph;

    LinkedList<GWNode>[] graphW;

    public DFSGraphExamples(int d) {
        graph = new LinkedList[d];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
    }

    public DFSGraphExamples(int d, boolean weight) {
        graphW = new LinkedList[d];
        for (int i = 0; i < graphW.length; i++) {
            graphW[i] = new LinkedList<>();
        }
    }

    private void addEdge(int i, int j) {
        graph[i].add(j);
    }

    private void addEdge(int i, int j, int w) {
        graphW[i].add(new GWNode(j, w));
    }

    public static void main(String[] args) {

        DFSGraphExamples graph = new DFSGraphExamples(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        graph.printGraph();

        System.out.println(graph.detectCycle());

        graph.topologicalSort();

        DFSGraphExamples graphW = new DFSGraphExamples(6, true);
        graphW.addEdge(0, 1, 5);
        graphW.addEdge(0, 2, 3);
        graphW.addEdge(1, 3, 6);
        graphW.addEdge(1, 2, 2);
        graphW.addEdge(2, 4, 4);
        graphW.addEdge(2, 5, 2);
        graphW.addEdge(2, 3, 7);
        graphW.addEdge(3, 5, 1);
        graphW.addEdge(3, 4, -1);
        graphW.addEdge(4, 5, -2);

        int n = graphW.longestPath(1);

    }

    private int longestPath(int s) {

        boolean[] vis = new boolean[graphW.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vis.length; i++) {
            if (!vis[i])
                dfsWeight(vis, stack, i);
        }

        int[] dist = new int[graphW.length];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MIN_VALUE;
        }

        dist[s] = 0;

        int[] dists = new int[graphW.length];
        for (int i = 0; i < dists.length; i++) {
            dists[i] = Integer.MAX_VALUE;
        }

        dists[s] = 0;

        while (!stack.isEmpty()) {
            Integer j = stack.pop();
            if (dists[j] != Integer.MAX_VALUE) {
                ListIterator<GWNode> listIterator = graphW[j].listIterator();
                while (listIterator.hasNext()) {
                    GWNode node = listIterator.next();
                    if (dist[node.v] < dist[j] + node.w)
                        dist[node.v] = dist[j] + node.w;

                    if (dists[node.v] > dists[j] + node.w)
                        dists[node.v] = dists[j] + node.w;
                }
            }
        }

        for (int i = 0; i < dist.length; i++) {
            System.out.println(s + "-->" + i + "==" + dist[i]);
        }

        for (int i = 0; i < dists.length; i++) {
            System.out.println(s + "-->" + i + "==" + dists[i]);
        }
        return 0;
    }

    private void dfsWeight(boolean[] vis, Stack<Integer> stack, int i) {

        vis[i] = true;

        ListIterator<GWNode> listIterator = graphW[i].listIterator();
        while (listIterator.hasNext()) {
            int j = listIterator.next().v;
            if (!vis[j])
                dfsWeight(vis, stack, j);
        }
        stack.push(i);
    }

    private void topologicalSort() {

        boolean[] vis = new boolean[graph.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vis.length; i++) {
            if (!vis[i])
                dfsTop(i, vis, stack);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    private void dfsTop(int i, boolean[] vis, Stack<Integer> stack) {

        vis[i] = true;
        ListIterator<Integer> listIterator = graph[i].listIterator();
        while (listIterator.hasNext()) {
            Integer j = listIterator.next();
            if (!vis[j])
                dfsTop(j, vis, stack);
        }
        stack.push(i);
    }

    private boolean detectCycle() {

        boolean[] vis = new boolean[graph.length];
        boolean[] stack = new boolean[graph.length];

        for (int i = 0; i < stack.length; i++) {
            if (!vis[i] && dfs(vis, stack, i))
                return true;
        }

        return false;
    }

    private boolean dfs(boolean[] vis, boolean[] stack, int i) {

        vis[i] = true;
        stack[i] = true;

        ListIterator<Integer> listIterator = graph[i].listIterator();
        while (listIterator.hasNext()) {
            int j = listIterator.next();
            if (!vis[j] && dfs(vis, stack, j))
                return true;
            else if (stack[j])
                return true;
        }
        stack[i] = false;
        return false;
    }

    private void printGraph() {

        for (int i = 0; i < graph.length; i++) {
            System.out.println(i + "-->" + Arrays.deepToString(graph[i].toArray()));
        }
    }

}

class GWNode {
    int v;
    int w;

    public GWNode(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
