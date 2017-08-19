package com.uh;

public class CoinChange {

    public static void main(String[] args) {

        int[] coins = { 1, 2, 3 };
        int sum = 4;

        int count = count(coins, coins.length, sum);
        System.out.println(count);

        int countDP = countDP(coins, coins.length, sum);
        System.out.println(countDP);

        
        int[] coins2 = {7, 3, 2, 6};
        sum = 13;
        int min = minCoins(coins2, coins2.length, sum);
        System.out.println(min);

        int minDP = minCoinsDP(coins2, coins2.length, sum);
        System.out.println(minDP);
        
        int minDP2 = minCoinsDP2(coins2, coins2.length, sum);
        System.out.println(minDP2);
    }

    private static int minCoinsDP2(int[] coins, int n, int sum) {

        int[] dp = new int[sum+1];
        int[] r = new int[sum+1];
        
        
        for (int i = 1; i < r.length; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
            r[i] = -1;
        }
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= sum; j++) {
                if(dp[j-coins[i]] + 1 < dp[j]){
                    dp[j] = 1 + dp[j-coins[i]];
                    r[j] = i;
                }
            }
            
        }
        //printCoins(r, coins);
        return dp[sum];
    }

    private static void printCoins(int R[], int coins[]) {
        if (R[R.length - 1] == -1) {
            System.out.print("No solution is possible");
            return;
        }
        int start = R.length - 1;
        System.out.print("Coins used to form total ");
        while ( start != 0 ) {
            int j = R[start];
            System.out.print(coins[j] + " ");
            start = start - coins[j];
        }
        System.out.println();
    }

    private static int minCoinsDP(int[] coins, int m, int n) {

        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for (int i = 0; i <= n; i++) {

            for (int j = 0; j < m; j++) {

                if (coins[j] <= i) {
                    int sub = dp[i - coins[j]];
                    if (sub != Integer.MAX_VALUE && 1 + sub < dp[i])
                        dp[i] = 1 + sub;
                }

            }

        }
        return dp[n];

    }

    private static int minCoins(int[] coins, int m, int n) {

        if (n == 0)
            return 0;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {

            if (coins[i] <= n) {
                int sub = minCoins(coins, m, n - coins[i]);

                if (sub != Integer.MAX_VALUE && sub + 1 < res)
                    res = sub + 1;
            }

        }
        return res;
    }

    private static int countDP(int[] coins, int m, int sum) {

        int[] dp = new int[sum + 1];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = coins[i]; j <= sum; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
        
        return dp[sum];
    }

    private static int count(int[] coins, int m, int n) {

        if (n == 0)
            return 1;

        if (n < 0)
            return 0;
        if (m <= 0 && n >= 1)
            return 0;

        return count(coins, m - 1, n) + count(coins, m, n - coins[m - 1]);
    }

}
