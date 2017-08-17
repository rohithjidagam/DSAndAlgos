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
        
        maxSumNotConsecutive();
        
        //without skipping 2 consecutive
        minTimeToFinishTaks();
        
        
    }

    private static void minTimeToFinishTaks() {

        int arr[] = {10, 5, 2, 4, 8, 6, 7, 10};
        
        int incl = arr[0];
        int excl = 0;
        
        for (int i = 1; i < arr.length; i++) {
            
            int inclNew = arr[i] + Math.min(incl, excl);
            int exclNew = incl;
            
            incl = inclNew;
            excl = exclNew;
        }
        
        System.out.println(excl);
        
    }

    private static void maxSumNotConsecutive() {

        int arr[] = {5, 5, 10, 100, 10, 5};
        
        int incl = arr[0];
        int excl = 0;
        
        for (int i = 1; i < arr.length; i++) {
            
            int inclNew = arr[i] + excl;
            int exclNew = incl;
            
            incl = inclNew;
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
            left[i] = Math.max(left[i-1], price[i] - min);
            min = Math.min(min, price[i]);
        }
        
        int max = price[n-1];
        right[n-1] = 0;
        for (int i = n-2; i >= 0; i--) {
            right[i] = Math.max(right[i+1], max - price[i]);
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
