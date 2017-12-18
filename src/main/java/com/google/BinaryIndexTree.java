package com.google;

public class BinaryIndexTree {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		BinaryIndexTree b = new BinaryIndexTree();
		int[] bit = b.constructTree(arr);

		int sum = b.getSum(bit, 5);
		System.out.println(sum);
		
		System.out.println(b.getSum(bit, 6));
	}

	private int getSum(int[] bit, int i) {

		i = i + 1;
		int sum = 0;
		while (i > 0) {
			sum += bit[i];
			i = getParent(i);
		}
		return sum;
	}

	private int[] constructTree(int[] arr) {

		int[] bit = new int[arr.length + 1];
		for (int i = 1; i <= arr.length; i++) {
			updateBIT(bit, arr[i - 1], i);
		}
		return bit;
	}

	private void updateBIT(int[] bit, int val, int i) {
		while (i < bit.length) {
			bit[i] += val;
			i = getNext(i);
		}
	}

	/**
	 * To get next 
	 * 1) 2's complement of get minus of index 
	 * 2) AND this with index 
	 * 3) Add it to index
	 */
	private int getNext(int i) {
		return i + (i & -i);
	}

	/**
	 * To get next 
	 * 1) 2's complement of get minus of index 
	 * 2) AND this with index 
	 * 3) Subtract it to index
	 */
	private int getParent(int i) {
		return i - (i & -i);
	}

}
