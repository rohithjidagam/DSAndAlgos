package com.uh;

import java.util.Collections;
import java.util.Comparator;

public class BinarySearch {

	public static void main(String[] args) {

		int arr[] = { 4, 5, 6, 7, 8, 9, 1, 2, 3 };
		int i = searchSortedAndRotated(arr, 0, arr.length - 1, 6);
		System.out.println(i);

		int min = findMin(arr, 0, arr.length - 1);
		System.out.println(min);

		int max = findMax(arr, 0, arr.length - 1);
		System.out.println(max);

		boolean flag = pairsSum(arr, 0, arr.length - 1, 7);

		int a[] = { 3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170 };
		int pos = positionInInfiniteStream(a, 0, a.length - 1, 10);
		System.out.println(pos);

		int[] ar = { 5, 7, 7, 8, 8, 8, 8, 8, 10 };
		int key = 9;
		int st = binStart(ar, 0, ar.length - 1, key, ar.length);
		System.out.println(st);

		int end = binEnd(ar, 0, ar.length - 1, key, ar.length);
		System.out.println(end);

		Collections.sort(null, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return 0;
			}

		});
	}

	private static int binStart(int[] nums, int l, int h, int key, int n) {
		if (l > h)
			return -1;
		int mid = (l + h) / 2;

		if (mid == 0 || (nums[mid] == key && nums[mid - 1] < key))
			return mid;

		if (key > nums[mid])
			return binStart(nums, mid + 1, h, key, n);

		return binStart(nums, l, mid - 1, key, n);
	}

	private static int binEnd(int[] nums, int l, int h, int key, int n) {
		if (l > h)
			return -1;

		int mid = (l + h) / 2;

		if (mid == n - 1 || (nums[mid] == key && nums[mid + 1] > key))
			return mid;

		if (key < nums[mid])
			return binEnd(nums, l, mid - 1, key, n);

		return binEnd(nums, mid + 1, h, key, n);
	}

	private static int positionInInfiniteStream(int[] a, int i, int j, int k) {

		int l = 0;
		int h = 1;
		int val = a[h];

		while (k > val) {
			l = h;
			h = 2 * h;
			val = a[h];
		}

		return binarySearch(a, l, h, k);
	}

	private static int binarySearch(int[] a, int i, int j, int k) {

		if (i > j)
			return -1;

		int mid = (i + j) / 2;

		if (a[mid] == k)
			return mid;

		if (a[mid] > k)
			return binarySearch(a, i, mid - 1, k);

		return binarySearch(a, mid + 1, j, k);
	}

	private static boolean pairsSum(int[] arr, int i, int j, int S) {

		int max = 0;
		for (int k = 0; k < arr.length; k++) {
			if (arr[k] > max)
				max = k;
		}

		// int min = findMin(arr, i, j);
		// int l = min;
		// int h = min-1;

		int h = max;
		int l = max + 1;

		while (l < arr.length && h >= 0) {
			int sum = arr[l] + arr[h];
			if (sum == S) {
				System.out.println(S + "=" + arr[l] + "+" + arr[h]);
				l++;
				h--;
			} else if (sum < S) {
				l++;
			} else {
				h--;
			}

		}
		return false;
	}

	private static int findMin(int[] arr, int i, int j) {

		while (i < j) {

			if (arr[i] < arr[j])
				return arr[i];

			int mid = (i + j) / 2;

			if (arr[mid] > arr[j])
				i = mid + 1;
			else
				j = mid;

		}

		return arr[i];

	}
	
	private static int findMax(int[] arr, int i, int j) {

		while (i < j) {
			if (arr[i] < arr[j])
				return arr[j];

			int mid = (i + j) / 2;

			if (arr[mid] > arr[i])
				i = mid + 1;
			else
				j = mid ;
		}
		return arr[i];
	}

	private static int searchSortedAndRotated(int[] arr, int i, int j, int k) {

		if (i > j)
			return -1;

		int mid = (i + j) / 2;

		if (arr[mid] == k)
			return mid;

		// low to mid is sorted
		if (arr[i] <= arr[mid]) {

			if (k >= arr[i] && k < arr[mid])
				return searchSortedAndRotated(arr, i, mid - 1, k);
			else
				return searchSortedAndRotated(arr, mid + 1, j, k);

		}

		// mid to high is sorted
		if (k > arr[mid] && k <= arr[j])
			return searchSortedAndRotated(arr, mid + 1, j, k);
		else
			return searchSortedAndRotated(arr, i, mid - 1, k);

	}

}
