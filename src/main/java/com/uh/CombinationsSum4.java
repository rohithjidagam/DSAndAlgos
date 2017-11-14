package com.uh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CombinationsSum4 {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3 };
		int sum = 4;

		long currentTimeMillis = System.currentTimeMillis();
		Map<Integer, Integer> map = new HashMap<>();
		int n = comboSum(arr, sum, map);
		System.out.println(n);
		System.out.println("Time :" + (System.currentTimeMillis() - currentTimeMillis));
		int n2 = comboSumDP(arr, sum);
		System.out.println(n2);

		int[] arr2 = { 1, 1, 1, 1, 1 };
		int target = targetSum(arr2, 3);
		System.out.println(target);
	}

	private static int targetSum(int[] arr, int S) {

		int sum = 0;
		for (int n : arr)
			sum += n;

		return sum < S || (sum + S) % 2 != 0 ? 0 : ways(arr, (sum + S) / 2);
	}

	private static int ways(int[] nums, int sum) {

		Integer[] dp = new Integer[sum + 1];
		Arrays.fill(dp, 0);
		dp[0] = 1;

		for (int i = 0; i < nums.length; i++) {
			for (int j = sum; j >= 0; j--) {
				if (j >= nums[i]){
					dp[j] += dp[j - nums[i]];
				}
			}
			System.out.println(Arrays.deepToString(dp));
		}
		return dp[sum];
	}

	private static int comboSumDP(int[] arr, int sum) {

		Integer[] dp = new Integer[sum + 1];
		Arrays.fill(dp, 0);
		dp[0] = 1;

		for (int i = 1; i <= sum; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i >= arr[j])
					dp[i] += dp[i - arr[j]];
			}
			System.out.println(Arrays.deepToString(dp));
		}
		return dp[sum];
	}

	private static int comboSum(int[] arr, int sum, Map<Integer, Integer> map) {

		if (arr.length == 0 || sum < 0)
			return 0;

		if (sum == 0)
			return 1;

		if (map.get(sum) != null)
			return map.get(sum);

		int res = 0;

		for (int i = 0; i < arr.length; i++) {
			if (sum >= arr[i])
				res += comboSum(arr, sum - arr[i], map);
		}

		map.put(sum, res);
		return res;
	}

}
