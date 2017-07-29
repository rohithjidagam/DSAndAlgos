package com.uh;

public class LPS {

    static String str = "GEEKSFORGEEKS";
    static int N = str.length();

    public static void main(String[] args) {

        int n = lps(str, 0, str.length() - 1);
        System.out.println(n);

        int m = lpsDP(str, 0, str.length());
        System.out.println(m);

       // O(n^2) time and O(n^2) space
        lpSubstring("forgeeksskeegfor");
        
        //O(n^2) time and O(1) space
        lpSubstringWithoutDP("forgeeksskeegfor");
    }

    private static void lpSubstringWithoutDP(String str) {

        int low , high; 
        int start = 0;
        int max_length = 1;
        int len = str.length();
        
        for (int i = 0; i < len; i++) {
            
            low = i-1;
            high = i;
            while(low >=0 && high < len && str.charAt(low) == str.charAt(high)){
                if(max_length < high - low +1){
                    max_length = high-low+1;
                    start = low;
                }
                --low;
                ++high;
            }
            
            low = i-1;
            high = i+1;
            
            while(low >= 0 && high < len && str.charAt(low) == str.charAt(high)){
                if(high-low+1 > max_length){
                    max_length = high-low+1;
                    start = low;
                }
                --low;
                ++high;
            }
            
        }
        
        System.out.println(str.substring(start, start + max_length));
        System.out.println(max_length);
        
        
        
    }

    private static void lpSubstring(String st) {

        int n = st.length();

        boolean[][] dp = new boolean[n][n];

        int max_length = 1;

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int start = 0;
        for (int i = 0; i < n - 2; i++) {
            if (st.charAt(i) == st.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                max_length = 2;
            }
        }

        for (int k = 3; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;

                if (st.charAt(i) == st.charAt(j) && dp[i + 1][j - 1]) {

                    dp[i][j] = true;
                    if (k > max_length) {
                        max_length = k;
                        start = i;
                    }
                }

            }
        }

        System.out.println(st.substring(start, start + max_length));
        System.out.println(max_length);

    }

    private static int lpsDP(String str, int s, int n) {

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int k = 2; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;
                if (k == 2 && str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = 2;
                } else if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        /*
         * for (int i = 0; i < n; i++) {
         * 
         * for (int j = 0; j < n; j++) { System.out.print(dp[i][j] + " "); }
         * System.out.println(); }
         */

        return dp[0][n - 1];
    }

    private static int lps(String str, int s, int e) {

        if (s == e)
            return 1;
        if (s + 1 == e && str.charAt(s) == str.charAt(e))
            return 2;

        if (str.charAt(s) == str.charAt(e))
            return 2 + lps(str, s + 1, e - 1);
        else
            return Math.max(lps(str, s, e - 1), lps(str, s + 1, e));
    }

}
