package com.uh;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {

        Integer[] arr = { 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4 };
        int n = arr.length;
        int k = 3;

        System.out.println(removeDuplicates(arr, n, k));
        System.out.println(Arrays.deepToString(arr));
    }

    static int removeDuplicates(Integer A[], int n, int k) {

        if (n <= k)
            return n;

        int i = 1, j = 1;
        int cnt = 1;
        while (j < n) {
            if (A[j] != A[j - 1]) {
                cnt = 1;
                A[i++] = A[j];
            } else {
                if (cnt < k) {
                    A[i++] = A[j];
                    cnt++;
                }
            }
            ++j;
        }
        return i;
    }

}
