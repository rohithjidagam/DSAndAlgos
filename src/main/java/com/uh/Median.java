package com.uh;

import java.util.Arrays;

public class Median {

	public static void main(String[] args) {

		int ar1[] = { 1, 2, 3, 6 };
		int ar2[] = { 4, 6, 8, 10 };

		int n = ar1.length;

		//System.out.println(getMedian(ar1, ar2, n));

		int a1[] = { 1, 2, 3, 6, 7, 9 };
		int a2[] = { 4, 6, 8, 10 };
		System.out.println(findMedianSortedArrays(a1, a2));

		//O(log(m+n))
		System.out.println(findMedian(a1, a2));
	}

	private static double findMedian(int[] nums1, int[] nums2) {

		if (nums1.length > nums2.length)
			findMedian(nums2, nums1);

		int m = nums1.length;
		int n = nums2.length;

		int st = 0;
		int end = m - 1;

		while (st < end) {

			int partX = (st + end) / 2;
			int partY = (m + n + 1) / 2 - partX;

			int maxLeftX = partX == 0 ? Integer.MIN_VALUE : nums1[partX - 1];
			int minRightX = partX == m ? Integer.MAX_VALUE : nums1[partX];

			int maxLeftY = partY == 0 ? Integer.MIN_VALUE : nums2[partY - 1];
			int minRightY = partY == m ? Integer.MAX_VALUE : nums2[partY];

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {

				if ((m + n) % 2 == 0) {
					return (double) (Math.min(minRightX, minRightY) + Math.max(maxLeftX, maxLeftY)) / 2;
				}
				return (double) Math.max(maxLeftX, maxLeftY);
			} else if (maxLeftX > minRightY) {
				end = partX - 1;
			} else {
				st = partX + 1;
			}
		}

		return -1;
	}

	private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int total = nums1.length + nums2.length;
		if (total % 2 == 0) {
			return (findKth(total / 2 + 1, nums1, nums2, 0, 0) + findKth(total / 2, nums1, nums2, 0, 0)) / 2.0;
		} else {
			return findKth(total / 2 + 1, nums1, nums2, 0, 0);
		}
	}

	private static int findKth(int k, int[] nums1, int[] nums2, int s1, int s2) {
		if (s1 >= nums1.length)
			return nums2[s2 + k - 1];

		if (s2 >= nums2.length)
			return nums1[s1 + k - 1];

		if (k == 1)
			return Math.min(nums1[s1], nums2[s2]);

		int m1 = s1 + k / 2 - 1;
		int m2 = s2 + k / 2 - 1;

		int mid1 = m1 < nums1.length ? nums1[m1] : Integer.MAX_VALUE;
		int mid2 = m2 < nums2.length ? nums2[m2] : Integer.MAX_VALUE;

		if (mid1 < mid2) {
			return findKth(k - k / 2, nums1, nums2, m1 + 1, s2);
		} else {
			return findKth(k - k / 2, nums1, nums2, s1, m2 + 1);
		}
	}

	private static int getMedian(int[] ar1, int[] ar2, int n) {

		if (n <= 0)
			return 1;
		if (n == 1)
			return (ar1[0] + ar2[0]) / 2;
		if (n == 2)
			return (Math.max(ar1[0], ar2[0]) + Math.min(ar1[1], ar2[1])) / 2;

		int m1 = getMedian(ar1, n);
		int m2 = getMedian(ar2, n);

		if (m1 == m2)
			return m1;

		if (m1 > m2) {
			if (n % 2 == 0)
				return getMedian(Arrays.copyOfRange(ar1, 0, m1), Arrays.copyOfRange(ar2, m2, n - 1), n - n / 2 + 1);
			else
				return getMedian(Arrays.copyOfRange(ar1, 0, m1), Arrays.copyOfRange(ar2, m2, n - 1), n - n / 2);
		} else {

			if (n % 2 == 0)
				return getMedian(Arrays.copyOfRange(ar1, m1, n - 1), Arrays.copyOfRange(ar2, 0, m2), n - n / 2 + 1);
			else
				return getMedian(Arrays.copyOfRange(ar1, m1, n - 1), Arrays.copyOfRange(ar2, 0, m2), n - n / 2);

		}

	}

	private static int getMedian(int[] ar, int n) {
		if (n % 2 == 0)
			return (ar[n / 2] + ar[n / 2 - 1]) / 2;
		else
			return ar[n / 2 - 1];
	}

}
