package com.uh;

import java.util.Arrays;
import java.util.Comparator;

public class OverlappingIntervals {

    public static void main(String[] args) {

        Integer[][] arr = { new Integer[] { 0, 2 }, new Integer[] { 3, 7 }, new Integer[] { 4, 6 },
                new Integer[] { 7, 8 }, new Integer[] { 1, 5 } };

        countOverLapping(arr);

        countOverLapping2(arr);
    }

    private static void countOverLapping2(Integer[][] arr) {

        Integer[] start = new Integer[arr.length];
        Integer[] end = new Integer[arr.length];

        int i = 0, j = 0;
        for (Integer[] integer : arr) {
            start[i++] = integer[0];
            end[j++] = integer[1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        i = 0;
        j = 0;
        int n = arr.length;
        int count = 0;
        int max = Integer.MIN_VALUE;

        while (i < n && j < n) {
            if (start[i] < end[j]) {
                count++;
                max = Math.max(max, count);
                i++;
            } else {
                count--;
                j++;
            }
        }
        System.out.println(max);
    }

    private static void countOverLapping(Integer[][] arr) {

        Arrays.sort(arr, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        System.out.println(Arrays.deepToString(arr));

        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1][1] > arr[i][0])
                count++;
        }
        System.out.println(count);

    }

}
