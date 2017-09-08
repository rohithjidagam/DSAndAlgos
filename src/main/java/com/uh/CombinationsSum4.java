package com.uh;

import java.util.HashMap;
import java.util.Map;

public class CombinationsSum4 {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3 };
        int sum = 4;

        Map<Integer, Integer> map = new HashMap<>();
        int n = comboSum(arr, sum, map);
        System.out.println(n);

        int n2 = comboSumDP(arr, sum);
        System.out.println(n2);
    }

    private static int comboSumDP(int[] arr, int sum) {

        int[] dp = new int[sum + 1];
        dp[0] = 1;

        for (int i = 1; i <= sum; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i >= arr[j])
                    dp[i] += dp[i - arr[j]];
            }
        }
        return dp[sum];
    }

    private static int comboSum(int[] arr, int sum, Map<Integer, Integer> map) {

        if (arr.length == 0 || sum < 0)
            return 0;

        if (sum == 0)
            return 1;

        if (map.get(sum) != null)
            return map.get(sum);

        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            if (sum >= arr[i])
                res += comboSum(arr, sum - arr[i], map);
        }

        map.put(sum, res);
        return res;
    }

}
