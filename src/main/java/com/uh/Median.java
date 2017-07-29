package com.uh;

import java.util.Arrays;

public class Median {

    public static void main(String[] args) {

        int ar1[] = { 1, 2, 3, 6 };
        int ar2[] = { 4, 6, 8, 10 };

        int n = ar1.length;

        System.out.println(getMedian(ar1, ar2, n));
    }

    private static int getMedian(int[] ar1, int[] ar2, int n) {

        if (n <= 0)
            return 1;
        if (n == 1)
            return (ar1[0] + ar2[0]) / 2;
        if (n == 2)
            return (Math.max(ar1[0], ar2[0]) + Math.min(ar1[1], ar2[1])) / 2;

        int m1 = getMedian(ar1, n);
        int m2 = getMedian(ar2, n);

        if (m1 == m2)
            return m1;

        if (m1 > m2) {
            if (n % 2 == 0)
                return getMedian(Arrays.copyOfRange(ar1, 0, m1), Arrays.copyOfRange(ar2, m2, n - 1), n - n / 2 + 1);
            else
                return getMedian(Arrays.copyOfRange(ar1, 0, m1), Arrays.copyOfRange(ar2, m2, n - 1), n - n / 2);
        } else {

            if (n % 2 == 0)
                return getMedian(Arrays.copyOfRange(ar1, m1, n - 1), Arrays.copyOfRange(ar2, 0, m2), n - n / 2 + 1);
            else
                return getMedian(Arrays.copyOfRange(ar1, m1, n - 1), Arrays.copyOfRange(ar2, 0, m2), n - n / 2);

        }

    }

    private static int getMedian(int[] ar, int n) {
        if (n % 2 == 0)
            return (ar[n / 2] + ar[n / 2 - 1]) / 2;
        else
            return ar[n / 2 - 1];
    }

}
