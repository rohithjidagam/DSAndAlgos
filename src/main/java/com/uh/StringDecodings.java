package com.uh;

public class StringDecodings {

    public static void main(String[] args) {

        int[] str = { 1, 2, 1 };

        int c = count(str, str.length);
        System.out.println(c);

        int cp = countDp(str, str.length);
        System.out.println(cp);
    }

    private static int countDp(int[] str, int n) {

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {

            if (str[i - 1] > 0)
                dp[i] = dp[i - 1];

            if (str[i - 2] == 1 || (str[i - 2] == 2 && str[i - 1] < 7))
                dp[i] += dp[i - 2];
        }
        return dp[n];
    }

    private static int count(int[] str, int n) {

        if (n == 0 || n == 1)
            return 1;

        int count = 0;

        if (str[n - 1] > 0)
            count = count(str, n - 1);

        if (str[n - 2] == 1 || (str[n - 2] == 2 && str[n - 1] < 7))
            count += count(str, n - 2);

        return count;
    }

}
