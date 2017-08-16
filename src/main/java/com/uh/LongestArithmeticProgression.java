package com.uh;

public class LongestArithmeticProgression {

    public static void main(String[] args) {

        int[] arr = { 1, 3, 6, 10, 14, 16, 17 };
        boolean flag = isAPWith3Elements(arr, arr.length);
        System.out.println(flag);

        int set2[] = { 1, 7, 10, 15, 27, 29 };
        int llap = lengthLongestAP(set2);
        System.out.println(llap);
    }

    private static int lengthLongestAP(int[] arr) {

        int n = arr.length;
        int[][] dp = new int[n][n];
        int llap = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            dp[i][n - 1] = 2;
        }

        for (int j = n - 2; j >= 1; j--) {

            int i = j - 1;
            int k = j + 1;

            while (i >= 0 && k < n) {
                if (arr[i] + arr[k] < 2 * arr[j]) {
                    k++;
                } else if (arr[i] + arr[k] > 2 * arr[j]) {
                    dp[i][j] = 2;
                    i--;
                } else {
                    dp[i][j] = 1 + dp[j][k];
                    llap = Math.max(llap, dp[i][j]);
                    i--;
                    k++;
                }
            }

            while (i >= 0) {
                dp[i][j] = 2;
                i--;
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return llap;
    }

    private static boolean isAPWith3Elements(int[] arr, int n) {

        int j = 0, k = 0;
        for (int i = 1; i < n - 1; i++) {
            j = i - 1;
            k = i + 1;
            while (j >= 0 && k < n) {
                if (arr[j] + arr[k] == 2 * arr[i])
                    return true;
                else if (arr[j] + arr[k] < 2 * arr[i])
                    j--;
                else
                    k++;
            }
        }
        return false;
    }

}
