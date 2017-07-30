package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArraysExamples {

    public static void main(String[] args) {

        // tripletSum();

        // sortEvenIncOddDec();

        // sumInRanges();

        // missingSmallestPositive();

        // trappingRainWater();

         powerSet();

        // permutations("ABC");

        // countFreq();

        //maxSlidingWindow();

    }

    private static void maxSlidingWindow() {

        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        int[] result = new int[nums.length - k + 1];

        List<Integer> list = new ArrayList<>();
        int i;
        for (i = 0; i < k; i++) {
            list.add(nums[i]);
        }

        int p = 0;
        result[p++] = list.stream().max(Integer::compare).get();
        for (; i < nums.length; i++) {

            if (i >= k) {
                list.remove(0);
            }

            list.add(nums[i]);
            result[p++] = list.stream().max(Integer::compare).get();
        }

        for (int j = 0; j < result.length; j++) {
            System.out.println(result[j]);
        }
    }

    private static void countFreq() {

        int[] arr = { 1, 2, 3, 1, 1, 2, 2, 3, 4, 5, 4, 5, 1, 3, 4 };

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        System.out.println(map);
    }

    private static void permutations(String s) {

        permute(s, 0, s.length() - 1);
    }

    private static void permute(String s, int i, int j) {

        if (i == j) {
            System.out.println(s);
        } else {
            for (int k = i; k <= j; k++) {
                s = swap(s, k, i);
                permute(s, i + 1, j);
                s = swap(s, k, i);
            }
        }
    }

    private static String swap(String s, int k, int i) {
        char[] ch = s.toCharArray();
        char c = ch[k];
        ch[k] = ch[i];
        ch[i] = c;
        return new String(ch);
    }

    private static void powerSet() {

        Integer[] nums = { 1, 2, 3 };
        List<Integer> list = Arrays.asList(nums);
        Set<Set<Integer>> set = power(list);
        for (Set<Integer> set2 : set) {
            System.out.println(set2);
        }
    }

    private static Set<Set<Integer>> power(List<Integer> list) {

        List<List<Integer>> lists = new ArrayList<>();

        Set<Set<Integer>> sets = new HashSet(lists);
        List<List<Integer>> lists1 = new ArrayList(sets);
        if (list.isEmpty()) {
            sets.add(new HashSet<>());
            return sets;
        }

        int head = list.get(0);
        List<Integer> rem = list.subList(1, list.size());

        Set<Set<Integer>> power = power(rem);
        for (Set<Integer> set : power) {
            Set<Integer> s = new HashSet<>();
            s.add(head);
            s.addAll(set);
            sets.add(s);
            sets.add(set);
        }

        return sets;
    }

    private static void trappingRainWater() {

        int[][] heightMap = { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } };

        print(heightMap);
        System.out.println("--------");
        int m = heightMap.length;
        int n = heightMap[0].length;

        int[][] top = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0)
                    top[i][j] = heightMap[i][j];
                if (j == 0)
                    top[i][j] = heightMap[i][j];
                if (i == m - 1)
                    top[i][j] = heightMap[i][j];
                if (j == n - 1)
                    top[i][j] = heightMap[i][j];
            }
        }

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                top[i][j] = Math.min(heightMap[i][j - 1],
                        Math.min(heightMap[i - 1][j], Math.min(heightMap[i + 1][j], heightMap[i][j + 1])));
            }
        }

        print(top);

        int water = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                water += Math.min(top[i][j - 1], Math.min(top[i - 1][j], Math.min(top[i + 1][j], top[i][j + 1])))
                        - heightMap[i][j];
            }
        }

        System.out.println(water);
    }

    static void print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void missingSmallestPositive() {

        Integer[] arr = { 1, 2, 3, 7, 6, 8, -1, -10, 15 };

        int n = arr.length;

        int i = 0;
        int j = n - 1;

        while (i < j) {

            while (arr[i] > 0 && i < j)
                i++;

            while (arr[j] < 0 && i < j)
                j--;

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        System.out.println(Arrays.deepToString(arr));
        System.out.println(i + " " + j);

        Integer[] a = Arrays.copyOfRange(arr, 0, j);
        System.out.println(Arrays.deepToString(a));

        for (int k = 0; k < a.length; k++) {
            if (Math.abs(a[k]) - 1 < a.length && a[Math.abs(a[k]) - 1] >= 0)
                a[Math.abs(a[k]) - 1] = -a[Math.abs(a[k]) - 1];
            System.out.println(Arrays.deepToString(a));
        }

        for (int k = 0; k < a.length; k++) {
            if (a[k] > 0) {
                System.out.println(k + 1);
                break;
            }
        }
    }

    private static void sumInRanges() {

        int[] nums = { 3, 0, -2, 6, -3, 2 };

        int[][] queries = { { 0, 2 }, { 2, 5 }, { 0, 5 } };

        int totalSum = 0;
        int sum = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            map.put(i, totalSum);
        }

        map.put(-1, 0);
        for (int i = 0; i < queries.length; i++) {

            int x = queries[i][0];
            int y = queries[i][1];

            sum += map.get(y) - map.get(x - 1);

        }

        System.out.println(sum);

    }

    private static void sortEvenIncOddDec() {

        Integer[] arr = { 0, 1, 4, 3, 2, 7, 6, 5 };
        int n = arr.length;

        Integer[] odd = new Integer[n / 2];
        Integer[] even = new Integer[n / 2];

        int k = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0)
                even[k++] = arr[i];
            else
                odd[j++] = arr[i];
        }

        Arrays.sort(even);
        Arrays.sort(odd, new Comp());
        System.out.println(Arrays.deepToString(even));
        System.out.println(Arrays.deepToString(odd));
        int i;
        for (i = 0; i < even.length; i++) {
            arr[i] = even[i];
        }

        for (int l = 0; l < odd.length; l++) {
            arr[i++] = odd[l];
        }

        System.out.println(Arrays.deepToString(arr));

    }

    private static void tripletSum() {
        int[] arr = { 1, 9, 6, 3, 2, 4, 5, 8, 7 };
        int sum = 9;

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {

            int j = i + 1;
            int k = arr.length - 1;

            while (j >= 0 && k >= 0 && k < arr.length && i != j && j != k) {
                if (arr[i] + arr[j] + arr[k] == sum) {
                    System.out.println(arr[i] + "+" + arr[j] + "+" + arr[k] + "=" + sum);
                    j++;
                    k--;
                } else if (arr[i] + arr[j] + arr[k] > sum) {
                    k--;
                } else {
                    j++;
                }
            }

        }
    }

}

class Comp implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }

}
