package com.uh;

public class MaxPointsCollected {

    public static void main(String[] args) {

        int A[][] = { { 100, 100, 100 }, { 100, 1, 100 }, { 100, 100, 100 } };

        findMaxPoints(A);
    }

    private static void findMaxPoints(int[][] a) {

        int m = a.length;
        int n = a[0].length;

        int[][] dp1s = new int[m + 1][n + 1];
        int[][] dp1e = new int[m + 1][n + 1];
        int[][] dp2s = new int[m + 1][n + 1];
        int[][] dp2e = new int[m + 1][n + 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp1s[i][j] = a[i - 1][j - 1] + Math.max(dp1s[i - 1][j], dp1s[i][j - 1]);
            }
        }

        for (int i = m; i >= 1; i--) {
            for (int j = n; j >= 1; j--) {
                dp1e[i][j] = a[i - 1][j - 1] + Math.max(dp1e[i + 1][j], dp1e[i][j + 1]);
            }
        }

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= m; j++) {
                dp2s[i][j] = a[i - 1][j - 1] + Math.max(dp2s[i + 1][j], dp2s[i][j - 1]);
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                dp2e[i][j] = a[i - 1][j - 1] + Math.max(dp2e[i - 1][j], dp2e[i][j + 1]);
            }
        }
        
        int ans = 0;
        for (int i=2; i<n; i++)
        {
            for (int j=2; j<m; j++)
            {
                int op1 = dp1s[i][j-1] + dp1e[i][j+1] +
                        dp2s[i+1][j] + dp2e[i-1][j];
                int op2 = dp1s[i-1][j] + dp1e[i+1][j] +
                        dp2s[i][j-1] + dp2e[i][j+1];
                ans = Math.max(ans, Math.max(op1, op2));
            }
        }
        
        System.out.println(ans);

    }
}
