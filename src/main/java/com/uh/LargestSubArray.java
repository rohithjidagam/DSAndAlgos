package com.uh;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LargestSubArray {

    public static void main(String[] args) {

        int arr[] = { 1, 56, 58, 57, 90, 92, 94, 93, 91, 45 };
        int n = arr.length;

        int len = largestSubArray(arr, n);
        System.out.println(len);

        int arr_d[] = { 10, 12, 12, 10, 10, 11, 10 };
        int len_d = largestSubArrayDups(arr_d, arr_d.length);
        System.out.println(len_d);

        int arr_l[] = { 1, 0, 0, 1, 1, 1,0 };
        int len_l = largestSubArrayWithEqual0and1(arr_l, arr_l.length);
        System.out.println(len_l);
    }

    private static int largestSubArrayWithEqual0and1(int[] a, int n) {

        for (int i = 0; i < n; i++) {
            a[i] = a[i] == 0 ? -1 : 1;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int max_len = 0;
        int ending_index = -1;
        int starting_index = -1;

        int sum = 0;

        for (int i = 0; i < n; i++) {

            sum += a[i];

            if (sum == 0) {
                max_len = i + 1;
                ending_index = i;
            }

            if (map.containsKey(sum)) {
                if (max_len < i - map.get(sum)) {
                    max_len = i - map.get(sum);
                    ending_index = i;
                }
            } else {
                map.put(sum, i);
            }
        }
        
        starting_index = ending_index - max_len + 1;
        
        System.out.println(starting_index + "->" + ending_index);

        return max_len;
    }

    private static int largestSubArrayDups(int[] a, int n) {

        int max_len = 1;

        for (int i = 0; i < n - 1; i++) {

            int min = a[i];
            int max = a[i];

            Set<Integer> set = new HashSet<>();
            set.add(a[i]);

            for (int j = i + 1; j < n; j++) {

                if (set.contains(a[j]))
                    break;

                set.add(a[j]);
                min = Math.min(a[j], min);
                max = Math.max(a[j], max);

                if (max - min == j - i) {
                    max_len = Math.max(max_len, max - min + 1);
                }
            }
        }

        return max_len;
    }

    private static int largestSubArray(int[] arr, int n) {

        int max_len = 1;

        for (int i = 0; i < n - 1; i++) {
            int min = arr[i];
            int max = arr[i];
            for (int j = i + 1; j < n; j++) {
                min = Math.min(arr[j], min);
                max = Math.max(arr[j], max);
                if (max - min == j - i)
                    max_len = Math.max(max_len, max - min + 1);
            }
        }
        return max_len;

    }

}
