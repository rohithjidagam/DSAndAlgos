package com.uh;

public class LCIS {

    public static void main(String[] args) {

        int[] arr1 = { 3, 4, 9, 1 };
        int[] arr2 = { 5, 3, 8, 9, 10, 2, 1 };
        int n = arr1.length;
        int m = arr2.length;

        int[] dp = new int[m];

        for (int i = 0; i < n; i++) {

            int cur = 0;
            for (int j = 0; j < m; j++) {

                if (arr1[i] == arr2[j]) {
                    if (1 + cur > dp[j])
                        dp[j] = 1 + cur;
                }

                if (arr1[i] > arr2[j]) {
                    if (dp[j] > cur)
                        cur = dp[j];
                }
            }
        }

        int max = -1;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
            System.out.print(dp[i] + " ");
        }

        System.out.println();

        System.out.println(max);
    }

}
