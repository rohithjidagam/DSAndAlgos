package com.uh;

public class MinCutsPalindrome {

    public static void main(String[] args) {

        String str = "abcbd";

        int n = str.length();

        boolean[][] dp = new boolean[n][n];
        int[] c = new int[n];

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                dp[i][i] = true;
            }
        }

        for (int k = 2; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;

                if (k == 2) {
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1];
                }

            }
        }

        for (int i = 0; i < n; i++) {
            if (dp[0][i])
                c[i] = 0;
            else {
                c[i] = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (dp[j + 1][i] && 1 + c[j] < c[i])
                        c[i] = 1 + c[j];
                }
            }
        }
        System.out.println(c[n-1]);
    }

}
