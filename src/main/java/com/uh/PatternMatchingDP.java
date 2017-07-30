package com.uh;

public class PatternMatchingDP {

    /*
     * // If current characters match, result is same as // result for lengths
     * minus one. Characters match // in two cases: // a) If pattern character
     * is '?' then it matches // with any character of text. // b) If current
     * characters in both match if ( pattern[j – 1] == ‘?’) || (pattern[j – 1]
     * == text[i - 1]) T[i][j] = T[i-1][j-1]
     * 
     * // If we encounter ‘*’, two choices are possible- // a) We ignore ‘*’
     * character and move to next // character in the pattern, i.e., ‘*’ //
     * indicates an empty sequence. // b) '*' character matches with ith
     * character in // input else if (pattern[j – 1] == ‘*’) T[i][j] = T[i][j-1]
     * || T[i-1][j]
     * 
     * else // if (pattern[j – 1] != text[i - 1]) T[i][j] = false
     */
    public static void main(String[] args) {

        String str = "abcd";
        String pattern = ".*";

        int m = str.length();
        int n = pattern.length();

        boolean match = match(str.toCharArray(), pattern.toCharArray(), m, n);
        System.out.println(match);

        boolean matchR = matchRecur(pattern, str);
        System.out.println(matchR);
    }

    private static boolean matchRecur(String p, String s) {

        int n = p.length();
        int m = s.length();

        if (m == 0 && n == 0)
            return true;

        if (n > 1 && p.charAt(0) == '*' && m == 0)
            return false;

        if ((n > 1 && p.charAt(0) == '?') || (n != 0 && m != 0 && p.charAt(0) == s.charAt(0)))
            return matchRecur(p.substring(1), s.substring(1));

        if (n != 0 && p.charAt(0) == '*')
            return matchRecur(p.substring(1), s) || matchRecur(p, s.substring(1));

        return false;
    }

    private static boolean match(char[] str, char[] pat, int m, int n) {

        if (n == 0)
            return m == 0;
        // m - string, n - pattern
        // dp[i][j] is true if first i characters in given string matches the
        // first j characters of pattern.
        boolean[][] dp = new boolean[m + 1][n + 1];

        // empty pattern can match with empty string
        dp[0][0] = true;

        // Only '*' can match with empty string
        for (int i = 1; i <= n; i++) {
            if (pat[i - 1] == '*') {
                dp[0][i] = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (pat[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }

                else if (pat[j - 1] == '?' || str[i - 1] == pat[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = false;
            }
        }

        return dp[m][n];
    }

}
