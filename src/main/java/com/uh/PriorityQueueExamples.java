package com.uh;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PriorityQueueExamples {

	public static void main(String[] args) {

		int[][] arr = { { 1, 3 }, { -2, 2 } };
		int K = 1;

		PriorityQueue<IPoint> pq = new PriorityQueue<IPoint>();

		for (int[] a: arr) {
			pq.add(new IPoint(a));
		}
		
		int[][] res = new int[K][2];
		for(int i = 0;i<K;i++) {
			IPoint poll = pq.poll();
			res[i][0] = poll.i;
			res[i][1] = poll.j;
		}
		
		System.out.println(Arrays.deepToString(res));
		
		

	}

}

class IPoint implements Comparable<IPoint> {
	int i;
	int j;
	int d;

	IPoint(int[] pt) {
		this.i = pt[0];
		this.j = pt[1];
		this.d = i * i + j * j;
	}

	@Override
	public int compareTo(IPoint o) {
		return this.d - o.d;
	}
}
