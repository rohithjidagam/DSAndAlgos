package com.uh;

public class StringDecodings {

    static String alphabet = "#abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        int[] str = { 1, 2, 1 };

        int c = count(str, str.length);
        System.out.println(c);

        int cp = countDp(str, str.length);
        System.out.println(cp);

        String s = "121";
        printDecodings(s, 0, s.length() - 1, "");

        String s2 = "1*";
        long d2 = decode2(s2, s2.length());
        System.out.println(d2);

        long d23 = decode3(s2, s2.length());
        System.out.println(d23);
    }

    private static long decode3(String s, int n) {
        int mod = 1000000007;

        long f1 = 1;
        long f2 = helper(s.substring(0, 1));
        
        for (int i = 1; i < n; i++) {
            long f3 = f2 * helper(s.substring(i, 2)) + f1 * helper(s.substring(i-1, 2));
            f1 = f2;
            f2 = f3%mod;
        }
        return f2;
    }

    private static long helper(String s) {
        if (s.length() == 1) {
            if (s.charAt(0) == '*')
                return 9;
            return s.charAt(0) == '0' ? 0 : 1;
        }

        else if (s.equals("**")) {
            return 15;
        } else if (s.charAt(1) == '*') {
            if (s.charAt(0) == '1')
                return 9;
            return s.charAt(0) == '2' ? 6 : 0;
        } else if (s.charAt(0) == '*') {
            return s.charAt(1) <= '6' ? 2 : 1;
        } else {
            int n = Integer.parseInt(s);
            if (n >= 10 && n <= 26)
                return 1;
            else
                return 0;
        }

    }

    private static long decode2(String s, int n) {
        int len = s.length();
        int mod = 1000000007;
        long[] dp = new long[len + 1];
        dp[0] = 1;
        if (s.charAt(0) == '0')
            return 0;
        if (s.charAt(0) == '*') {
            dp[1] = 9;
        } else {
            dp[1] = 1;
        }
        for (int i = 2; i <= len; i++) { // i-1 the index of current character
                                         // in s.
            if (s.charAt(i - 1) == '0') {
                if (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2') {
                    dp[i] = dp[i - 2];
                } else if (s.charAt(i - 2) == '*') {
                    dp[i] = 2 * dp[i - 2];
                } else {
                    return 0;
                }
            } else if (s.charAt(i - 1) >= '1' && s.charAt(i - 1) <= '9') {
                dp[i] = dp[i - 1];
                if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                    dp[i] = (dp[i] + dp[i - 2]) % mod;
                } else if (s.charAt(i - 2) == '*') {
                    if (s.charAt(i - 1) <= '6') {
                        dp[i] = (dp[i] + dp[i - 2] * 2) % mod;
                    } else {
                        dp[i] = (dp[i] + dp[i - 2]) % mod;
                    }
                }
            } else { // s.charAt(i-1) == '*'
                dp[i] = (9 * dp[i - 1]) % mod;
                if (s.charAt(i - 2) == '1') { // 11 - 19
                    dp[i] = (dp[i] + 9 * dp[i - 2]) % mod;
                } else if (s.charAt(i - 2) == '2') { // 21 - 26
                    dp[i] = (dp[i] + 6 * dp[i - 2]) % mod;
                } else if (s.charAt(i - 2) == '*') {
                    dp[i] = (dp[i] + 15 * dp[i - 2]) % mod;
                }
            }
        }
        return (int) dp[len];
    }

    private static void printDecodings(String string, int i, int j, String result) {
        if (i > j) {
            System.out.println(result);
            return;
        }

        int c = Integer.parseInt(string.charAt(j) + "");

        if (c <= 26)
            printDecodings(string, i, j - 1, alphabet.charAt(c) + result);

        if (j > 0) {
            c = Integer.parseInt(string.charAt(j - 1) + "" + string.charAt(j) + "");
            if (c <= 26)
                printDecodings(string, i, j - 2, alphabet.charAt(c) + result);
        }

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
