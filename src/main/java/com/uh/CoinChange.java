package com.uh;

public class CoinChange {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3 };
        int sum = 4;

        int count = count(arr, arr.length, sum);
        System.out.println(count);

        int countDP = countDP(arr, arr.length, sum);
        System.out.println(countDP);

        int min = minCoins(arr, arr.length, sum);
        System.out.println(min);

        int minDP = minCoinsDP(arr, arr.length, sum);
        System.out.println(minDP);
    }

    private static int minCoinsDP(int[] arr, int m, int n) {

        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for (int i = 0; i <= n; i++) {

            for (int j = 0; j < m; j++) {

                if (arr[j] <= i) {
                    int sub = dp[i - arr[j]];
                    if (sub != Integer.MAX_VALUE && 1 + sub < dp[i])
                        dp[i] = 1 + sub;
                }

            }

        }
        return dp[n];

    }

    private static int minCoins(int[] arr, int m, int n) {

        if (n == 0)
            return 0;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {

            if (arr[i] <= n) {
                int sub = minCoins(arr, m, n - arr[i]);

                if (sub != Integer.MAX_VALUE && sub + 1 < res)
                    res = sub + 1;
            }

        }
        return res;
    }

    private static int countDP(int[] arr, int m, int sum) {

        int[] dp = new int[sum + 1];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = arr[i]; j <= sum; j++) {
                dp[j] += dp[j - arr[i]];
            }
        }

        return dp[sum];
    }

    private static int count(int[] arr, int m, int n) {

        if (n == 0)
            return 1;

        if (n < 0)
            return 0;
        if (m <= 0 && n >= 1)
            return 0;

        return count(arr, m - 1, n) + count(arr, m, n - arr[m - 1]);
    }

}
