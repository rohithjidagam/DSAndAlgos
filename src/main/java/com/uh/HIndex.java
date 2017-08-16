package com.uh;

import java.util.Arrays;

public class HIndex {

    public static void main(String[] args) {

        int[] citations = { 2, 0, 6, 1, 5, 3, 4, 4 };

        int bf = buckerSortApproach(citations);
        System.out.println(bf);

        Arrays.sort(citations);
        int bfs = sortedApproach(citations);
        System.out.println(bfs);

        int[] arr = { 3, 4, 1, 9, 56, 7, 9, 12 };
        int m = 5;
        // 3,4,7,9,9
        int chocs = chocolateDistribution(arr, m);
        System.out.println(chocs);
    }

    private static int chocolateDistribution(int[] arr, int m) {

        Arrays.sort(arr);

        int n = arr.length;

        int st = 0, end = 0, min = Integer.MAX_VALUE;

        for (int i = 0; i + m - 1 < n; i++) {

            int diff = arr[i + m - 1] - arr[i];
            if (diff < min) {
                min = diff;
                st = i;
                end = i + m - 1;
            }
        }
        return arr[end] - arr[st];
    }

    private static int sortedApproach(int[] citations) {
        if (citations == null || citations.length == 0)
            return 0;
        int l = 0, r = citations.length;
        int n = citations.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (citations[mid] == n - mid)
                return n - mid;
            if (citations[mid] < n - mid)
                l = mid + 1;
            else
                r = mid;
        }
        return n - l;
    }

    private static int buckerSortApproach(int[] citations) {

        int n = citations.length;
        int[] buckets = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n)
                buckets[n]++;
            else
                buckets[citations[i]]++;
        }

        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += buckets[i];
            if (count >= i)
                return i;
        }
        return 0;
    }

}
