package com.uh;

public class EditDistance {

    public static void main(String[] args) {

        String st1 = "satuday";
        String st2 = "sunday";

        System.out.println(editDistRec(st1, st2, st1.length(), st2.length()));
        System.out.println(editDistDP(st1, st2, st1.length(), st2.length()));
        System.out.println(isEditDist1("teacher", "tache"));
        
        double d = 25;
        System.out.println(d/2);
    }

    private static boolean isEditDist1(String st1, String st2) {

        int m = st1.length();
        int n = st2.length();
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < m && j < n) {

            if (st1.charAt(i) != st2.charAt(j)) {

                if (st1.charAt(i) != st2.charAt(j)) {

                    count++;
                    if (count > 1)
                        return false;

                    if (m > n)
                        j--;
                    else if (m < n)
                        i--;
                }
            }
            i++;
            j++;

        }
        return ((count == 1) || (count == 0 && m != n)) ? true : false;
    }

    private static int editDistDP(String st1, String st2, int m, int n) {

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (st1.charAt(i - 1) == st2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
            }
        }
        return dp[m - 1][n - 1];
    }

    private static int editDistRec(String st1, String st2, int m, int n) {

        if (m == 0)
            return n;
        if (n == 0)
            return m;

        if (st1.charAt(m - 1) == st2.charAt(n - 1)) {
            return editDistRec(st1, st2, m - 1, n - 1);
        } else {
            return 1 + Math.min(editDistRec(st1, st2, m, n - 1),
                    Math.min(editDistRec(st1, st2, m - 1, n), editDistRec(st1, st2, m - 1, n - 1)));
        }
    }

}
