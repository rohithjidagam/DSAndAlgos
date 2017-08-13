package com.uh;

import java.util.LinkedList;
import java.util.ListIterator;

public class CourseSchedule {

    public static void main(String[] args) {

        int numOfCourses = 2;
        int[][] prerequisites = { { 1, 0 }, { 0, 1 } };

        boolean canFinish = canFinish(numOfCourses, prerequisites);
        System.out.println(canFinish);
    }

    private static boolean canFinish(int numOfCourses, int[][] prerequisites) {

        LinkedList[] adj = new LinkedList[numOfCourses];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
        int m = prerequisites.length;
        if (m == 0)
            return false;
        int n = prerequisites[0].length;

        for (int i = 0; i < m; i++) {
            adj[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        boolean[] vis = new boolean[numOfCourses];
        boolean[] rec = new boolean[numOfCourses];
        
        for (int i = 0; i < rec.length; i++) {
            if(!vis[i] && dfs(vis, rec, i, adj))
                return false;
        }
        

        return true;
    }

    private static boolean dfs(boolean[] vis, boolean[] rec, int i, LinkedList[] adj) {

        vis[i] = true;
        rec[i] = true;
        
        ListIterator listIterator = adj[i].listIterator();
        while(listIterator.hasNext()){
            int j = (int) listIterator.next();
            if(!vis[j] && dfs(vis, rec, j, adj))
                return true;
            else if(rec[j])
                return true;
        }
        rec[i] = false;
        return false;
    }

}
