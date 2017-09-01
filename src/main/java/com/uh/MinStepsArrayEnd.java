package com.uh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinStepsArrayEnd {
    //BFS

    public static void main(String[] args) {

        int arr[] = { 0, 1, 2, 3, 4, 5, 6, 7, 5, 4, 3, 6, 0, 1, 2, 3, 4, 5, 7 };
        int n = arr.length;

        int steps = getMinSteps(arr, n);
        System.out.println(steps);
        
        int[] sum = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        //O(n) solution
        int min = minStepsLinear(sum, sum.length);
        System.out.println(min);
    }

    private static int minStepsLinear(int[] arr, int n) {

        int maxReach = arr[0];
        int step = arr[0];
        int jump = 1;
        
        for (int i = 1; i < n; i++) {
            
            if(i == n-1)
                return jump;
            
            maxReach = Math.max(maxReach, i+arr[i]);
            step--;
            
            if(step == 0){
                jump++;
                if(i >= maxReach)
                    return -1;
                step = maxReach - i;
            }
        }
        return -1;
    }

    private static int getMinSteps(int[] arr, int n) {

        boolean[] visit = new boolean[n];
        int[] distance = new int[n];
        List<Integer>[] graph = new ArrayList[100];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            graph[arr[i]].add(i);
        }

        for (int i = 0; i < graph.length; i++) {
            // System.out.println(i + "" +
            // Arrays.deepToString(graph[i].toArray()));
        }

        distance[0] = 0;
        visit[0] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        while (!q.isEmpty()) {

            int id = q.poll();

            if (id == n - 1)
                break;

            int val = arr[id];

            for (int i = 0; i < graph[val].size(); i++) {

                int next = graph[val].get(i);

                if (!visit[next]) {
                    visit[next] = true;
                    q.add(next);
                    distance[next] = 1 + distance[id];
                }

            }

            if (id - 1 >= 0 && !visit[id - 1]) {
                visit[id - 1] = true;
                q.add(id - 1);
                distance[id - 1] = 1 + distance[id];
            }

            if (id + 1 >= 0 && !visit[id + 1]) {
                visit[id + 1] = true;
                q.add(id + 1);
                distance[id + 1] = 1 + distance[id];
            }
        }

        return distance[n - 1];
    }

}
