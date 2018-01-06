package com.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmallerOnRight {

	public static void main(String[] args) {

		int[] arr = { 5, 4, 2, 6, 3, 1 };

		List<Integer> res = new ArrayList<>();
		int n = arr.length;
		CSNode root = new CSNode(arr[n - 1]);
		res.add(0);

		for (int i = n - 2; i >= 0; i--) {

			int count = insert(root, arr[i]);
			res.add(count);
		}

		Collections.reverse(res);
		System.out.println(Arrays.deepToString(res.toArray()));
		
		ineorder(root);

	}

	private static void ineorder(CSNode root) {

		if(root == null)
			return;
		ineorder(root.left);
		System.out.println(root.val + "--" + root.count);
		ineorder(root.right);
	}

	private static int insert(CSNode root, int val) {

		int count = 0;

		while (true) {
			if (val <= root.val) {
				root.count++;
				if (root.left == null) {
					root.left = new CSNode(val);
					break;
				} else
					root = root.left;
			} else {
				count += root.count;
				if (root.right == null) {
					root.right = new CSNode(val);
					break;
				} else
					root = root.right;
			}
		}

		return count;
	}

}

class CSNode {
	int val;
	int count = 1;
	CSNode left;
	CSNode right;

	public CSNode(int v) {
		val = v;
	}
}
