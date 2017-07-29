package com.uh;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class AlienLanguage {

    int V;
    LinkedList<Integer>[] adj;

    public AlienLanguage(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) {

        String words[] = { "baa", "abcd", "abca", "cab", "cad" };
        int n = words.length;
        int alpha = 4; // letters in alien language; a,b,c,d

        AlienLanguage graph = new AlienLanguage(alpha);

        for (int i = 0; i < words.length - 1; i++) {

            String word1 = words[i];
            String word2 = words[i + 1];

            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {

                if (word1.charAt(j) != word2.charAt(j)) {
                    graph.addEdge(word1.charAt(j) - 'a', word2.charAt(j) - 'a');
                    break;
                }
            }
        }
        
       for (int i = 0; i < graph.adj.length; i++) {
        System.out.println(i + "-->" + Arrays.deepToString(graph.adj[i].toArray()));
    }

        graph.topSort();

    }

    private void topSort() {

        boolean[] vis = new boolean[V];
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < vis.length; i++) {
            if (!vis[i])
                dfs(vis, i, stack);
        }

        while (!stack.isEmpty()) {
            System.out.print((char)(stack.pop() + 'a') + " ");
        }
        System.out.println();
    }

    private void dfs(boolean[] vis, int i, Stack stack) {

        vis[i] = true;
        ListIterator<Integer> listIterator = adj[i].listIterator();
        while (listIterator.hasNext()) {
            Integer j = listIterator.next();
            if (!vis[j])
                dfs(vis, j, stack);
        }
        stack.push(i);
    }

    private void addEdge(int i, int j) {
        adj[i].add(j);
    }

}
