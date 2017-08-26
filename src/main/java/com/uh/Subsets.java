package com.uh;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class Subsets {

    public static void main(String[] args) {

        char[] set = { 'a', 'b' };
        generate(set.length, set);

        long start = System.currentTimeMillis();
        int[] arr = { 83, 86, 77, 15, 93, 35, 86, 92, 49, 21, 62, 27, 90, 59, 63, 26, 40, 26, 72, 36, 11, 68, 67, 29,
                82, 30, 62, 23, 67, 35, 29, 2, 22, 58, 69, 67, 93, 56, 11, 42, 29, 73, 21, 19, 84, 37, 98, 24, 15, 70,
                13, 26, 91, 80, 56, 73, 62, 70, 96, 81, 5, 25, 84, 27, 36, 5, 46, 29, 13, 57, 24, 95, 82, 45, 14, 67,
                34, 64, 43, 50, 87, 8, 76, 78, 88, 84, 3, 51, 54, 99, 32, 60, 76, 68, 39, 12, 26, 86, 94, 39 };
        // sum(arr, arr.length);
        System.out.println("Time:" + (System.currentTimeMillis() - start));

        int[] set2 = { 10, 12, 12 };
        uniqueGenerate(set2.length, set2);
        Subsets subsets = new Subsets();
        int[] set3 = { 1, 2, 3 };
        boolean b = subsets.subSetSum(set3, set3.length, 9);
        System.out.println(b);

        boolean bD = subsets.subSetSumDP(set3, set3.length, 5);
        System.out.println(bD);

        boolean bD1 = subsets.subSetSumDP1(set3, set3.length, 5);
        System.out.println(bD1);

        subsets.bitSetSum(set3, set3.length);
    }

    private void bitSetSum(int[] s, int n) {

        BitSet b = new BitSet();
        b.set(0);

        for (int i = 0; i < n; i++) {

        }

        System.out.println(b);

    }

    private boolean subSetSumDP1(int[] s, int n, int sum) {

        // The value of subset[i][j] will be true if there
        // is a subset of set[0..j-1] with sum equal to i
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // If sum is 0, then answer is true
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= sum; i++) {
            dp[0][i] = false;
        }

        // Fill the subset table in botton up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= s[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - s[i - 1]];
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[n][sum];
    }

    private boolean subSetSumDP(int[] s, int n, int sum) {

        // The value of subset[i][j] will be true if there
        // is a subset of set[0..j-1] with sum equal to i
        boolean[][] dp = new boolean[sum + 1][n + 1];

        // If sum is 0, then answer is true
        for (int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }

        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= sum; i++) {
            dp[i][0] = false;
        }

        // Fill the subset table in botton up manner
        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1];
                if (i >= s[j - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - s[j - 1]][j - 1];
                }
            }
        }

        for (int i = 0; i <= sum; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[sum][n];
    }

    private boolean subSetSum(int[] s, int n, int sum) {

        if (sum == 0)
            return true;
        if (n == 0 && sum != 0)
            return false;
        if (s[n - 1] > sum)
            return subSetSum(s, n - 1, sum);

        return subSetSum(s, n - 1, sum) || subSetSum(s, n - 1, sum - s[n - 1]);

    }

    private static void uniqueGenerate(int n, int[] set) {
        int m = 1 << n;
        Set<String> sets = new HashSet<>();
        String s = "";
        for (int i = 0; i < m; i++) {
            s = "";
            for (int j = 0; j < n; j++) {

                if ((i & (1 << j)) > 0) {
                    s += set[j];
                }
            }
            sets.add(s);
        }

        for (String string : sets) {
            System.out.println(string);
        }

    }

    private static void sum(int[] arr, int n) {

        int m = 1 << n;
        int sum;
        for (int i = 0; i < m; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    sum += arr[j];
                }
            }
            // System.out.print(sum + " ");
        }
        // System.out.println();
    }

    private static void generate(int n, char[] set) {

        int m = 1 << n;
        for (int i = 0; i < m; i++) {
            System.out.print("{");
            for (int j = 0; j < n; j++) {

                if ((i & (1 << j)) > 0) {
                    System.out.print(set[j]);
                }
            }
            System.out.println("}");
        }

    }

}
