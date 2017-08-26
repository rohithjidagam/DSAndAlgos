package com.uh;

import java.util.Arrays;
import java.util.Comparator;

public class LIS {

    public static void main(String[] args) {

        int arr[] = { 51, 7, 2, 94, 49, 30, 24, 85, 55, 57, 41 };
        int n = arr.length;
        System.out.println("Length of LIS is " + lis(arr, n));

        System.out.println("Length of LIS is " + lisDP(arr, n));

        int a[] = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        int n1 = a.length;
        System.out.println("Length of LBS is " + lbs(a, n1));

        int bs = lisBinarySearch(arr, n);
        System.out.println(bs);

        Integer[][] chains = { { 1, 2 }, { 3, 4 }, { 2, 3 } };
        int maxLen = maxPairs(chains, chains.length);
        System.out.println(maxLen);

        Integer[][] briges = { { 6, 2 }, { 4, 3 }, { 2, 6 }, {1,5} };
        int maxBridges = maxBridges(briges, briges.length);
        System.out.println(maxBridges);
    }

    private static int maxBridges(Integer[][] briges, int n) {

        Arrays.sort(briges, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] > o2[0] ? 1 : -1;
                } else if (o1[1] > o2[1]) {
                    return 1;
                }
                return -1;
            }
        });

        System.out.println(Arrays.deepToString(briges));
        int[] dp=new int[n];
        for (int i = 0; i < dp.length; i++) {
            dp[i] =1;
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(briges[i][0] >= briges[j][0] && dp[i] < 1+dp[j] )
                    dp[i] = 1+dp[j];
            }
        }
        int max = dp[0];
        for (int i = 1; i < n; i++)
            max = Math.max(max, dp[i]);

        return max;
    }

    private static int maxPairs(Integer[][] chains, int n) {

        Arrays.sort(chains, new Comparator<Integer[]>() {
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0] > o2[0] ? 1 : -1;
            }
        });

        System.out.println(Arrays.deepToString(chains));

        int[] dp = new int[n];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (chains[j][1] < chains[i][0] && dp[i] < 1 + dp[j])
                    dp[i] = 1 + dp[j];
            }
        }

        int max = dp[0];
        for (int i = 1; i < n; i++)
            max = Math.max(max, dp[i]);

        return max;

    }

    private static int lisBinarySearch(int[] arr, int n) {

        int[] parent = new int[n];
        int[] lis = new int[n + 1];
        int len = 0;

        for (int i = 0; i < n; i++) {

            int l = 1;
            int h = len;

            while (l <= h) {
                int mid = (l + h) / 2;

                if (arr[lis[mid]] < arr[i])
                    l = mid + 1;
                else
                    h = mid - 1;
            }

            int pos = l;
            parent[i] = lis[pos - 1];
            lis[pos] = i;

            len = Math.max(len, pos);
        }
        return len;
    }

    private static int lbs(int[] a, int n) {

        int[] lis = new int[n];

        for (int i = 0; i < n; i++) {
            lis[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;
            }
        }

        int[] lds = new int[n];

        for (int i = 0; i < n; i++) {
            lds[i] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                if (a[i] > a[j] && lds[i] < lds[j] + 1)
                    lds[i] = lds[j] + 1;
            }

        }

        int max = lis[0] + lds[0] - 1;
        for (int i = 1; i < n; i++)
            if (lis[i] + lds[i] - 1 > max)
                max = lis[i] + lds[i] - 1;

        return max;
    }

    private static int lisDP(int[] arr, int n) {

        int[] lis = new int[n];
        for (int i = 0; i < n; i++) {
            lis[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < 1 + lis[j]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        int max = -1;
        for (int i = 0; i < lis.length; i++) {
            max = Math.max(max, lis[i]);
        }

        return max;
    }

    private static int lis(int[] arr, int n) {

        if (n == 1)
            return 1;

        int cur_lis = 1;

        for (int i = 0; i < n; i++) {

            int lis = lis(arr, i);

            if (arr[i] < arr[n - 1] && cur_lis < 1 + lis)
                cur_lis = 1 + lis;
        }

        return cur_lis;
    }

}
