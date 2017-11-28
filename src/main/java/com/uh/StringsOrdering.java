package com.uh;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StringsOrdering {

    public static void main(String[] args) {

        String arr[] = { "acb", "bac", "cad", "dre", "esd", "dra" };

        possibleOrderings(arr, arr.length - 1);
    }

    private static void possibleOrderings(String[] arr, int length) {

        List<Integer>[] graph = new ArrayList[length];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] in = new int[26];
        int[] out = new int[26];
        boolean[] mark = new boolean[26];

        for (int i = 0; i <= length; i++) {

            int f = arr[i].charAt(0) - 'a';
            int l = arr[i].charAt(arr[i].length() - 1) - 'a';

            mark[f] = true;
            mark[l] = true;

            out[f]++;
            in[l]++;

            graph[f].add(l);

        }

        for (int i = 0; i < length; i++) {
            if (in[i] != out[i])
                System.out.println(false);
        }

        System.out.println(isConnected(graph, mark, arr[0].charAt(0) - 'a'));

    }

    private static boolean isConnected(List<Integer>[] graph, boolean[] mark, int s) {
        boolean[] visited = new boolean[26];

        dfs(graph, s, visited);

        for (int i = 0; i < graph.length; i++) {

            if (mark[i] && !visited[i])
                return false;
        }
        return true;
    }

    private static void dfs(List<Integer>[] graph, int s, boolean[] visited) {

        visited[s] = true;

        ListIterator<Integer> listIterator = graph[s].listIterator();
        while (listIterator.hasNext()) {
            Integer d = listIterator.next();
            if (!visited[d])
                dfs(graph, d, visited);
        }
    }

}
