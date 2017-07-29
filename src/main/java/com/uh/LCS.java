package com.uh;

public class LCS {

    public static void main(String[] args) {

        String X = "OldSite:GeeksforGeeks.org";
        String Y = "NewSite:GeeksQuiz.comGeeks";
     
        int m = X.length();
        int n = Y.length();
        
        //longest commonm subsequence
        int lcss = lcss(X,Y,m,n);
        System.out.println(lcss);
        
        //longest common substring
        int lcst = lcst(X,Y,m,n);
        System.out.println(lcst);
        
    }

    private static int lcst(String X, String Y, int m, int n) {

        int[][] dp = new int[m+1][n+1];
        int result = 0;
        for (int i = 0; i <=m; i++) {
            for (int j = 0; j <=n; j++) {
                if(i==0 || j==0)
                    dp[i][j] = 0;
                else if(X.charAt(i-1) == Y.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1];
                    result = Math.max(result, dp[i][j]);
                } else{
                    dp[i][j] = 0;
                }
            }
            
        }
        
        return result;
    }

    private static void print(int[][] dp, int m,int n) {

        for (int i = 0; i < m; i++) {
            for (int j = 0; j <n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int lcss(String X, String Y, int m, int n) {

        int[][] dp = new int[m+1][n+1];
        
        for (int i = 0; i <=m; i++) {
            
            for (int j = 0; j <=n; j++) {
                
                if( i==0 || j==0)
                    dp[i][j] = 0;
                else if(X.charAt(i-1) == Y.charAt(j-1))
                    dp[i][j] = 1+ dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
            
        }
        return dp[m][n];
    }

}
