package com.google;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch {

	public static void main(String[] args) {

		List<String> list = binaryWatch(1);
		System.out.println(list);
	}

	private static List<String> binaryWatch(int n) {

		List<String> res = new ArrayList<>();
		if (n == 0)
			return res;

		int[] num1 = { 8, 4, 2, 1 };
		int[] num2 = { 32, 16, 8, 4, 2, 1 };

		for (int i = 0; i <= n; i++) {

			List<Integer> list1 = generate(num1, i);
			List<Integer> list2 = generate(num2, n - i);

			for (Integer l1 : list1) {
				if (l1 >= 12)
					continue;
				for (Integer l2 : list2) {
					if (l2 >= 60)
						continue;
					res.add(l1 + ":" + (l2 < 10 ? "0" + l2 : l2));

				}
			}
		}

		return res;
	}

	private static List<Integer> generate(int[] nums, int count) {
		List<Integer> res = new ArrayList<>();
		generate(nums, count, 0, 0, res);
		return res;
	}

	private static void generate(int[] nums, int count, int pos, int sum, List<Integer> res) {

		if (count == 0) {
			res.add(sum);
			return;
		}

		for (int i = pos; i < nums.length; i++) {
			generate(nums, count - 1, i + 1, sum + nums[i], res);
		}

	}

}
