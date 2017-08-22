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

        String str = "aab";
        String pattern = "c*a*b";

        int m = str.length();
        int n = pattern.length();

        boolean match = match(str.toCharArray(), pattern.toCharArray(), m, n);
        System.out.println(match);

        boolean matchR = matchRecur(pattern, str);
        System.out.println(matchR);
        
        boolean matchL = matchLinear(pattern, str);
        System.out.println(matchL);
        
        

        // '.' Matches any single character.
        // '*' Matches zero or more of the preceding element.
        // Note preceding with *

        String str2 = "aab";
        String pat2 = "c*a*b"; // 0c 2a 1b matches.

        boolean m1 = isMatch(str2, pat2);
        System.out.println(m1);

        boolean m2 = matchDP(str2, pat2);
        System.out.println(m2);
    }

    private static boolean matchLinear(String p, String s) {

        int m = s.length();
        int n = p.length();
        
        int i = 0; //string
        int j= 0; //pattern
        int match = 0;
        int start = -1;
        
        while(i < m){
            // advancing both pointers
            if( j <n && p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)){
                i++;
                j++;
                
            }// * found, only advancing pattern pointer 
            else if(j < n && p.charAt(j) == '*'){
                start = j;
                match = i;
                j++;
            } //last pattern pointer was *, advancing string pointer
            else if(start != -1){
                j = start + 1;
                match++;
                i = match;
            } else{
                //current pattern pointer is not star, last patter pointer was not *
                //characters do not match
                return false;
            }
        }
        
      //check for remaining characters in pattern
        while(j < n && p.charAt(j) == '*')
            j++;
        
        return j == n;
        
    }

    private static boolean isMatch(String s, String p) {
        // base case
        int n = p.length();
        int m = s.length();

        if (n == 0) {
            return m == 0;
        }

        // special case
        if (n == 1) {

            // if the length of s is 0, return false
            if (m < 1) {
                return false;
            }

            // if the first does not match, return false
            else if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            }

            // otherwise, compare the rest of the string of s and p.
            else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }

        // case 1: when the second char of p is not '*'
        if (p.charAt(1) != '*') {
            if (m < 1) {
                return false;
            }
            if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }

        // case 2: when the second char of p is '*', complex case.
        else {
            // case 2.1: a char & '*' can stand for 0 element
            if (isMatch(s, p.substring(2))) {
                return true;
            }

            // case 2.2: a char & '*' can stand for 1 or more preceding element,
            // so try every sub string
            int i = 0;
            while (i < m && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }

    
    /*
     * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
       2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
       3, If p.charAt(j) == '*': 
             here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     */
    private static boolean matchDP(String s, String p) {

        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = (dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j]);
                    }
                }
            }
        }
        return dp[m][n];
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
