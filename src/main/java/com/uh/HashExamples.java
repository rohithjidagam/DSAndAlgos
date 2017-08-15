package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class HashExamples {

    public static void main(String[] args) {

        largestSubArrayContiguous();

        subArrayWithSum();

        int[] nums = { 12, 3, 6, 1, 6, 9 };
        int sum = 24;
        List<List<Integer>> list = findTriplets(nums, sum);
        for (List<Integer> list2 : list) {
            System.out.println(list2);
        }

        int arr1[] = { 1, 4, 5, 6 };
        int arr2[] = { 2, 3, 7, 8 };
        int arr3[] = { 1, 4, 6, 10 };
        int arr4[] = { 2, 4, 7, 8 };

        int count = countQuadraples(arr1, arr2, arr3, arr4, arr1.length, 30);
        System.out.println(count);

        int mat[][] = { { 2, 1, 4, 3 }, { 1, 2, 3, 2 }, { 3, 6, 2, 3 }, { 5, 2, 5, 3 } };
        countCommons(mat, mat.length);

        System.out.println();
        int[] arr = { 2, 1, 3, 4, 2, 1, 5, 1, 7 };
        maxDiffBetOccurences(arr, arr.length);

        int[] arr5 = { 1, 1, 1, 1, 1 };
        System.out.println(subarraySum(arr5, 2));
        
        /*
         * check if the array has a continuous subarray of size at least 2 that 
         * sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
         */
        int[] arr6 = {23, 2, 6, 4, 7};
        System.out.println(checkSubarraySum(arr6, 6));

    }

    private static void maxDiffBetOccurences(int[] arr, int n) {

        Map<Integer, Integer> map = new HashMap<>();
        int max = -1;

        for (int i = 0; i < n; i++) {
            
            if (map.containsKey(arr[i])) {

                if (i - map.get(arr[i]) > max) {
                    max = i - map.get(arr[i]);
                }
            } else {
                map.put(arr[i], i);
            }
        }

        System.out.println(max);

    }

    private static void countCommons(int[][] mat, int n) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(mat[0][i]);
        }

        Set<Integer> temp;
        for (int i = 1; i < n; i++) {
            temp = new HashSet<>();
            for (int j = 0; j < n; j++)
                temp.add(mat[i][j]);

            Iterator<Integer> iterator = set.iterator();
            while (iterator.hasNext()) {
                if (!temp.contains(iterator.next()))
                    iterator.remove();
            }

        }

        for (Integer integer : set) {
            System.out.print(integer + ",");
        }

    }

    private static int countQuadraples(int[] arr1, int[] arr2, int[] arr3, int[] arr4, int n, int sum) {

        Map<Integer, Integer> map = new HashMap<>();

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int s = arr1[i] + arr2[j];
                if (map.containsKey(s)) {
                    map.put(s, map.get(s) + 1);
                } else {
                    map.put(s, 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int s = arr3[i] + arr4[j];
                if (map.containsKey(sum - s)) {
                    count += map.get(sum - s);
                }
            }
        }
        return count;
    }

    private static List<List<Integer>> findTriplets(int[] nums, int sum) {

        List<List<Integer>> res = new ArrayList<>();
        Set<String> set = new TreeSet<>();
        List<Integer> list = null;

        Arrays.sort(nums);
        int n = nums.length;
        int j, k;
        for (int i = 0; i < n - 2; i++) {
            j = i + 1;
            k = n - 1;

            while (j < k) {

                if (nums[i] + nums[j] + nums[k] == sum) {

                    String s = nums[i] + "-" + nums[j] + "-" + nums[k];
                    if (!set.contains(s)) {
                        list = new ArrayList<>();
                        set.add(s);
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        res.add(list);
                    }
                    j++;
                    k--;

                } else if (nums[i] + nums[j] + nums[k] < sum) {
                    j++;
                } else {
                    k--;
                }
            }

        }

        return res;
    }

    private static void subArrayWithSum() {

        int arr[] = { 1, 4, 20, 3, 10, 5 };
        int sum = 33;

        Map<Integer, Integer> map = new HashMap<>();

        int cursum = 0;

        for (int i = 0; i < arr.length; i++) {

            cursum += arr[i];

            if (cursum == sum) {
                System.out.println("Sum Found : " + 0 + "-" + i);
                return;
            }

            if (map.containsKey(cursum - sum)) {
                System.out.println("Sum Found : " + (map.get(cursum - sum) + 1) + "-" + i);
                return;
            }

            map.put(cursum, i);

        }

    }

    private static boolean checkSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (k != 0)
                sum %= k;

            if (map.containsKey(sum)) {
                if (i - map.get(sum) >= 2)
                    return true;
            } else
                map.put(sum, i);
            
            System.out.println(map);
        }

        return false;
    }

    private static int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum - k))
                count += map.get(sum - k);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    private static void largestSubArrayContiguous() {

        int[] arr = { 1, 56, 58, 57, 90, 92, 94, 93, 91, 45 };

        int max_len = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            int max = arr[i];
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {

                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                if ((max - min) == j - i) {
                    max_len = Math.max(max_len, max - min + 1);
                }
            }

        }
        System.out.println(max_len);
    }

}
