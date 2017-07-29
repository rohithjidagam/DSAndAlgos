package com.uh;

public class LIS {

    public static void main(String[] args) {

        int arr[] = { 51, 7, 2, 94, 49, 30, 24, 85, 55, 57, 41 };
        int n = arr.length;
        System.out.println("Length of LIS is " + lis(arr, n));

        System.out.println("Length of LIS is " + lisDP(arr, n));

        int a[] = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        int n1 = a.length;
        System.out.println("Length of LBS is " + lbs(a, n1));
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

        return lis[n - 1];
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
