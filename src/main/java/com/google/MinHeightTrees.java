package com.google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class MinHeightTrees {

    public static void main(String[] args) {

        MinHeightTrees m = new MinHeightTrees();
        int[][] edges = { { 1, 0 }, { 1, 2 }, { 1, 3 } };
        List<Integer> list = m.findMinHeightTrees(4, edges);
        System.out.println(list);

        int[][] edges2 = { { 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 5, 4 } };
        List<Integer> list2 = m.findMinHeightTrees(6, edges2);
        System.out.println(list2);
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();

        for (int[] edg : edges) {
            adj[edg[0]].add(edg[1]);
            adj[edg[1]].add(edg[0]);
        }

        int min = Integer.MAX_VALUE;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            int m = bfs(adj, i, n);
            if (m < min) {
                list.clear();
                list.add(i);
                min = m;
            } else if (m == min) {
                list.add(i);
            }
        }

        return list;
    }

    int bfs(List<Integer>[] adj, int s, int k) {

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[k];
        q.add(s);
        int c = 0;
        while (!q.isEmpty()) {

            int n = q.size();
            while (n-- > 0) {

                Integer d = q.poll();
                vis[d] = true;
                ListIterator<Integer> it = adj[d].listIterator();
                while (it.hasNext()) {
                    int p = it.next();
                    if (!vis[p])
                        q.add(p);
                }
            }
            c++;
        }

        return c;

    }

}
