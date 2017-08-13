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
            if (citations[mid] < citations.length - mid)
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
