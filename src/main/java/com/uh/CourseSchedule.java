package com.uh;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CourseSchedule {

	int k = 0;
	public static void main(String[] args) {

		int numOfCourses = 2;
		int[][] prerequisites = { { 1, 0 }, { 0, 1 } };

		boolean canFinish = canFinish(numOfCourses, prerequisites);
		System.out.println(canFinish);
		
		CourseSchedule c = new CourseSchedule();
		int[][] pre = {{1,0}};
		int[] findOrder = c.findOrder(2, pre);
		for (int i = 0; i < findOrder.length; i++) {
			System.out.print(findOrder[i] + " ");
		}
	}

	private static boolean canFinish(int numOfCourses, int[][] prerequisites) {

		List[] adj = new LinkedList[numOfCourses];
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
			if (!vis[i] && dfs(vis, rec, i, adj))
				return false;
		}

		return true;
	}

	private static boolean dfs(boolean[] vis, boolean[] rec, int i, List[] adj) {

		vis[i] = true;
		rec[i] = true;

		ListIterator listIterator = adj[i].listIterator();
		while (listIterator.hasNext()) {
			int j = (int) listIterator.next();
			if (!vis[j] && dfs(vis, rec, j, adj))
				return true;
			else if (rec[j])
				return true;
		}
		rec[i] = false;
		return false;
	}

	boolean dfs(int i, int[] vis, int[] res, List<Integer>[] adj, StringBuilder sb) {
		vis[i] = 1;
		ListIterator<Integer> listIterator = adj[i].listIterator();
		while (listIterator.hasNext()) {
			int j = listIterator.next();
			if (vis[j] == 1)
				return true;
			else if (vis[j] == 0 && dfs(j, vis, res, adj, sb))
				return true;
		}

		vis[i] = 2;
		sb.append(i);
		res[--k] = i;
		return false;

	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {

		List<Integer>[] adj = new LinkedList[numCourses];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}
		for (int i = 0; i < prerequisites.length; i++) {
			adj[prerequisites[i][1]].add(prerequisites[i][0]);
		}

		int[] vis = new int[numCourses];
		int[] res = new int[numCourses];
		k = numCourses;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < vis.length; i++) {
			if (vis[i] == 0 && dfs(i, vis, res, adj, sb))
				return new int[0];
		}
		System.out.println(sb.reverse().toString());

		return res;
	}

}
