package com.google;

public class SegmentTrees {

	int[] st;

	public SegmentTrees(int[] arr, int n) {

		int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
		int size = 2 * (int) Math.pow(2, x) - 1;
		st = new int[size];

		constructTree(arr, 0, n - 1, 0);


	}

	private int constructTree(int[] arr, int i, int j, int ind) {

		if (i == j) {
			st[ind] = arr[i];
			return st[ind];
		}

		int mid = (i + j) / 2;

		st[ind] = constructTree(arr, i, mid, 2 * ind + 1) + constructTree(arr, mid + 1, j, 2 * ind + 2);

		return st[ind];
	}

	public static void main(String[] args) {

		int[] arr = { 1, 3, 5, 7, 9, 11 };

		SegmentTrees trees = new SegmentTrees(arr, arr.length);

		int sum = trees.getSum(arr.length, 1, 3);
		System.out.println(sum);
		
		for (int i = 0; i < trees.st.length; i++) {
			System.out.print(trees.st[i] + " ");
		}
		System.out.println();

		trees.update(arr, arr.length, 1, 10);
		
		for (int i = 0; i < trees.st.length; i++) {
			System.out.print(trees.st[i] + " ");
		}
		System.out.println();
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		int sum2 = trees.getSum(arr.length, 1, 3);
		System.out.println(sum2);
		
		
		
		
	}

	private void update(int[] arr, int n, int ind, int val) {

		if (ind < 0 || ind > n - 1)
			return;

		int diff = val - arr[ind];

		arr[ind] = val;

		updateUtil(0, n - 1, ind, diff, 0);

	}

	private void updateUtil(int s, int e, int ind, int diff, int k) {

		if (ind < s || ind > e)
			return;

		st[k] = st[k] + diff;
		if (s != e) {
			int mid = (s + e) / 2;
			updateUtil(s, mid, ind, diff, 2 * k + 1);
			updateUtil(mid + 1, e, ind, diff, 2 * k + 1);

		}

	}

	private int getSum(int n, int i, int j) {

		if (i < 0 || j > n - 1 || i > j)
			return -1;

		return getSumUtil(0, n - 1, i, j, 0);
	}

	private int getSumUtil(int s, int e, int i, int j, int ind) {

		if (i <= s && j >= e)
			return st[ind];

		if (e < i || s > j)
			return 0;

		int mid = (s + e) / 2;

		return getSumUtil(s, mid, i, j, 2 * ind + 1) + getSumUtil(mid + 1, e, i, j, 2 * ind + 2);

	}

}
