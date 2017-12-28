package com.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimalCashFlow {

	int count = 0;
	public static void main(String[] args) {

		int[][] transactions = { { 0, 1, 10 }, { 2, 0, 5 } };

		OptimalCashFlow o = new OptimalCashFlow();
		int mini = o.minTransfers(transactions);
		System.out.println(mini);

		int count = o.optimalCashFlow(transactions);
		System.out.println(count);
	}

	private int optimalCashFlow(int[][] transactions) {

		int[][] graph = { { 0, 10, 0 }, { 0, 0, 0 }, { 5, 0, 0 } };
		Integer[] amount = new Integer[graph.length];
		Arrays.fill(amount, 0);

		for (int i = 0; i < amount.length; i++) {
			for (int j = 0; j < amount.length; j++) {
				amount[i] += (graph[j][i] - graph[i][j]);
			}
		}

		System.out.println(Arrays.deepToString(amount));

		minCashFlow(amount);

		return count;
	}

	private void minCashFlow(Integer[] amount) {

		int maxCredit = getMax(amount);
		int maxDebit = getMin(amount);

		if (amount[maxCredit] == 0 && amount[maxDebit] == 0)
			return;

		int min = getMin(-amount[maxDebit], amount[maxCredit]);

		amount[maxCredit] -= min;
		amount[maxDebit] += min;

		System.out.println("Person " + maxCredit + " pays " + min + " to " + maxDebit);
		count++;

		minCashFlow(amount);
	}

	private int getMin(int x, int y) {
		return (x < y) ? x : y;
	}

	private int getMax(Integer[] arr) {
		int maxInd = 0;
		for (int i = 1; i < arr.length; i++)
			if (arr[i] > arr[maxInd])
				maxInd = i;
		return maxInd;
	}

	private int getMin(Integer[] arr) {
		int minInd = 0;
		for (int i = 1; i < arr.length; i++)
			if (arr[i] < arr[minInd])
				minInd = i;
		return minInd;
	}

	private int minTransfers(int[][] transactions) {

		Map<Integer, Long> map = new HashMap<>();
		for (int[] t : transactions) {
			long val1 = map.getOrDefault(t[0], 0L);
			long val2 = map.getOrDefault(t[1], 0L);

			map.put(t[0], val1 - t[2]);
			map.put(t[1], val1 + t[2]);
		}

		System.out.println(map);

		List<Long> list = new ArrayList();
		for (long val : map.values()) {
			if (val != 0)
				list.add(val);
		}

		Long[] debts = new Long[list.size()];
		debts = list.toArray(debts);
		return helper(debts, 0, 0);
	}

	int helper(Long[] debts, int pos, int count) {
		while (pos < debts.length && debts[pos] == 0)
			pos++;
		int res = Integer.MAX_VALUE;
		long pre = 0;
		for (int i = pos + 1; i < debts.length; i++) {
			if (debts[i] != pre && debts[pos] * debts[i] < 0) {
				debts[i] += debts[pos];
				res = Math.min(res, helper(debts, pos + 1, count + 1));
				debts[i] = debts[i] - debts[pos];
				pre = debts[i];
			}
		}
		return res == Integer.MAX_VALUE ? count : res;
	}
}
