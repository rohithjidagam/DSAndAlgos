package com.uh;

public class MaxProfitStocks {

	public static void main(String[] args) {

		int k = 2;
		int price[] = { 10, 22, 5, 75, 65, 80 };
		int n = price.length;

		int[][] dp = new int[k + 1][n + 1];

		// For day 0, you can't earn money
		// irrespective of how many times you trade
		for (int i = 0; i <= k; i++) {
			dp[i][0] = 0;
		}

		// profit is 0 if we don't do any transation
		// (i.e. k =0)
		for (int i = 0; i <= n; i++) {
			dp[0][i] = 0;
		}

		// O(k*n^2)
		ktransactions(k, price, n, dp);

		// O(k*n)
		optimizedKTransactions(k, price, n, dp);

		k2tranSactions(price, n);

		// 3 state machine
		/*
		 * rest(self loop) s0 --rest- s2 \buy /sell \ / \s1/ rest(self loop)
		 */
		maxProfitWithCoolDown();

		maxProfitWithTransactionFee();

		maxSumNotConsecutive();

		// without skipping 2 consecutive
		minTimeToFinishTaks();

		paintHouse2();

	}

	private static void maxProfitWithTransactionFee() {

		int[] prices = { 1, 3, 2, 8, 4, 9 };
		int fee = 2;

		int n = prices.length;
		int[] buy = new int[n];
		int[] hold = new int[n];
		int[] skip = new int[n]; // do nothing with empty portfolio
		int[] sell = new int[n];

		// the moment we buy a stock, our balance should decrease
		buy[0] = -prices[0];
		// assume if we have stock in the first day, we are still in deficit
		hold[0] = -prices[0];

		for (int i = 1; i < n; i++) {
			/**  We can only buy on today if we sold stock or skipped with empty portfolio yesterday */
			buy[i] = Math.max(sell[i - 1], skip[i - 1]) - prices[i];
			/** Can only hold if we bought or already holding stock yesterday */
			hold[i] = Math.max(hold[i - 1], buy[i - 1]);
			/** Can skip only if we skipped, or sold stock yesterday*/
			skip[i] = Math.max(skip[i - 1], sell[i - 1]);
			/** Can sell only if we bought, or held stock yesterday*/
			sell[i] = Math.max(buy[i - 1], hold[i - 1]) + prices[i] - fee;

		}

		int max = Math.max(buy[n - 1], Math.max(sell[n - 1], Math.max(hold[n - 1], skip[n - 1])));
		System.out.println("Profit:" + max);

	}

	private static void paintHouse2() {

		int[][] costs = {};

		int prevMin = 0;
		int prevSecMin = 0;
		int prevColor = -1;

		for (int i = 0; i < costs.length; i++) {

			int curMin = Integer.MAX_VALUE;
			int curSecMin = Integer.MAX_VALUE;	
			int curColor = -1;

			for (int j = 0; j < costs[0].length; j++) {

				costs[i][j] = costs[i][j] + prevColor == j ? prevSecMin : prevMin;

				if (costs[i][j] < curMin) {
					curSecMin = curMin;
					curMin = costs[i][j];
					curColor = j;
				} else if (costs[i][j] < curSecMin) {
					curSecMin = costs[i][j];
				}

				prevMin = curMin;
				prevSecMin = curSecMin;
				prevColor = curColor;
			}

			System.out.println(prevMin);
		}
	}

	private static void maxProfitWithCoolDown() {

		int[] prices = { 1, 2, 3, 0, 2 };
		int n = prices.length;

		int[] s0 = new int[n];
		int[] s1 = new int[n];
		int[] s2 = new int[n];

		s0[0] = 0;
		s1[0] = -prices[0];
		s2[0] = Integer.MIN_VALUE;

		for (int i = 1; i < s2.length; i++) {
			s0[i] = Math.max(s0[i - 1], s2[i - 1]);
			s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]);
			s2[i] = prices[i] + s1[i - 1];
		}

		System.out.println(Math.max(s0[n - 1], s2[n - 1]));
	}

	private static void minTimeToFinishTaks() {

		int arr[] = { 10, 5, 2, 4, 8, 6, 7, 10 };

		int incl = arr[0];
		int excl = 0;

		for (int i = 1; i < arr.length; i++) {

			int inclNew = arr[i] + Math.min(incl, excl);
			int exclNew = incl;

			incl = inclNew;
			excl = exclNew;
		}

		System.out.println("Min time:" + Math.min(incl, excl));

	}

	private static void maxSumNotConsecutive() {

		int arr[] = { 5, 5, 10, 100, 10, 5 };

		int incl = arr[0];
		int excl = 0;

		for (int i = 1; i < arr.length; i++) {

			int exclNew = Math.max(incl, excl);

			incl = arr[i] + excl;
			excl = exclNew;
		}

		System.out.println(incl);
	}

	private static void k2tranSactions(int[] price, int n) {

		int left[] = new int[n];
		int right[] = new int[n];

		int min = price[0];
		left[0] = 0;
		for (int i = 1; i < n; i++) {
			left[i] = Math.max(left[i - 1], price[i] - min);
			min = Math.min(min, price[i]);
		}

		int max = price[n - 1];
		right[n - 1] = 0;
		for (int i = n - 2; i >= 0; i--) {
			right[i] = Math.max(right[i + 1], max - price[i]);
			max = Math.max(max, price[i]);
		}

		int profit = 0;
		for (int i = 0; i < n; i++) {
			profit = Math.max(profit, left[i] + right[i]);
		}

		System.out.println(profit);

	}

	private static void optimizedKTransactions(int k, int[] price, int n, int[][] dp) {

		for (int i = 1; i <= k; i++) {
			int prevDiff = Integer.MIN_VALUE;
			for (int j = 1; j < n; j++) {
				prevDiff = Math.max(prevDiff, dp[i - 1][j - 1] - price[j - 1]);
				dp[i][j] = Math.max(dp[i][j - 1], price[j] + prevDiff);
			}
		}

		for (int i = 0; i <= k; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println(dp[k][n - 1]);

	}

	private static void ktransactions(int k, int[] price, int n, int[][] dp) {

		for (int i = 1; i <= k; i++) {
			for (int j = 1; j < n; j++) {
				int max = Integer.MIN_VALUE;
				for (int l = 0; l < j; l++) {
					max = Math.max(max, price[j] - price[l] + dp[i - 1][l]);
				}
				dp[i][j] = Math.max(max, dp[i][j - 1]);
			}
		}

		for (int i = 0; i <= k; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println(dp[k][n - 1]);
	}

}
