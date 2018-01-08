package com.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SkyLine {

	public static void main(String[] args) {

		SkyLine s = new SkyLine();
		// int[][] building = { { 1, 3, 4 }, { 3, 4, 4 }, { 2, 6, 2 }, { 8, 11, 4 }, {
		// 7, 9, 3 }, { 10, 11, 2 } };
		int[][] buildings = { { 1, 3, 3 }, { 2, 4, 4 }, { 5, 8, 2 }, { 6, 7, 4 }, { 8, 9, 4 } };
		List<int[]> skyline = s.getSkyline(buildings);
		for (int[] is : skyline) {
			System.out.println(is[0] + "--" + is[1]);
		}
	}

	public List<int[]> getSkyline(int[][] buildings) {

		int n = buildings.length;
		Building[] buildingPoints = new Building[n * 2];
		int j = 0;
		for (int i = 0; i < buildings.length; i++) {
			int[] b = buildings[i];
			buildingPoints[j++] = new Building(b[0], b[2], true);
			buildingPoints[j++] = new Building(b[1], b[2], false);
		}
		Arrays.sort(buildingPoints);

		List<int[]> res = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		pq.add(0);
		
		int prevMaxHeight = 0;

		for (Building b : buildingPoints) {

			if (b.start) {
				pq.add(b.h);
			} else {
				pq.remove(b.h);
			}
			
			int maxHeight = pq.peek();
			if (maxHeight != prevMaxHeight) {
				res.add(new int[] { b.x, maxHeight });
				prevMaxHeight = maxHeight;
			}

		}

		return res;
	}
}

class Building implements Comparable<Building> {
	int x;
	int h;
	boolean start;

	public Building(int i, int j, boolean b) {
		x = i;
		h = j;
		start = b;
	}

	// first compare by x.
	// If they are same then use this logic
	// if two starts are compared then higher height building should be picked first
	// if two ends are compared then lower height building should be picked first
	// if one start and end is compared then start should appear before end
	@Override
	public int compareTo(Building o) {

		if (this.x != o.x)
			return this.x - o.x;
		else {
			return (this.start ? -this.h : this.h) - (o.start ? -o.h : o.h);
		}
	}

	@Override
	public String toString() {
		return x + "-" + h + "-" + start;
	}
}
