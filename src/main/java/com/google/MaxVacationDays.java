package com.google;

public class MaxVacationDays {

	int res = 0;

	public static void main(String[] args) {

		int[][] flights = { { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 0 } };
		int[][] days = { { 1, 3, 1 }, { 6, 0, 3 }, { 3, 3, 3 } };
		MaxVacationDays m = new MaxVacationDays();
		// O(N ^ K)
		int maxVacation = m.dfs(flights, days);
		System.out.println(maxVacation);

		int maxVac = m.dp(flights, days);
		System.out.println(maxVac);
	}

	/*
	 * O(K * N * N) time O(NK) space can be reduced to O(N) space
	 * 
	 * 1. define subproblems dp[i][j]: The maximum vacation days we can get in week
	 * i staying at city j number of subproblems O(K * N)
	 * 
	 * 2. guess In week i - 1, which city we are in. number of choices O(N)
	 * 
	 * 3. relate subproblem solutions dp[i][j] = max(dp[i - 1][k] + days[j][i]) (k =
	 * {previous reachable cities}, if we can go from previous city k to city j)
	 * time per subproblem O(N) corner case: week 0, the previous city is 0, no
	 * guesses dp[0][j] = days[j][0] if city j is reachable
	 * 
	 * 4. topological order week 0 to K - 1
	 * 
	 * 5. original problem max(dp[K - 1][j]) (j = 0, 1, ... N)
	 */
	private int dp(int[][] flights, int[][] days) {

		int N = flights.length;
		int K = days.length;

		int[][] dp = new int[K][N];
		boolean[][] vis = new boolean[K][N];

		// first week, no guesses for the previous city
		for (int city = 0; city < vis.length; city++) {
			if (city == 0 || flights[0][city] != 0) {
				dp[0][city] = days[city][0];
				vis[0][city] = true;
			}
		}

		// topological order(week)
		for (int week = 1; week < K; week++) {
			for (int city = 0; city < N; city++) {
				for (int prevCity = 0; prevCity < N; prevCity++) {
					if (vis[week - 1][prevCity] && (city == prevCity || flights[prevCity][city] != 0)) {
						dp[week][city] = Math.max(dp[week][city], dp[week - 1][prevCity] + days[city][week]);
						vis[week][city] = true;
					}
				}
			}
		}

		int res = 0;
		for (int city = 0; city < N; city++) {
			res = Math.max(res, dp[K - 1][city]);
		}

		return res;
	}

	private int dfs(int[][] flights, int[][] days) {

		int n = flights.length;
		int k = days.length;

		dfs(flights, days, 0, 0, 0, n, k);
		return res;
	}

	private void dfs(int[][] flights, int[][] days, int city, int week, int sum, int N, int K) {

		if (week == K) {
			res = Math.max(res, sum);
			return;
		}

		for (int nextCity = 0; nextCity < N; nextCity++) {
			if (city == nextCity || flights[city][nextCity] != 0) {
				dfs(flights, days, nextCity, week + 1, sum + days[nextCity][week], N, K);
			}
		}
	}

}
