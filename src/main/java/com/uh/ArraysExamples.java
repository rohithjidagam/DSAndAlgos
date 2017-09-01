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

        missingSmallestPositive();

        // trappingRainWater();

        // powerSet();

        // permutations("ABC");

        // countFreq();

        // maxSlidingWindow();

        maxProductSubArray();

        productExceptItself();

        // K transactions
        maxProfit();

        intToRoman();

        romanToInteger();

        celebrity(4);

        System.out.println(addBinary("1111", "1111"));
        
        System.out.println(excelColumnName(987));
        
        System.out.println(excelColumnNumber("AKY"));

        //handles all negative numbers (kadanes variation)
        int max_sum = maxSumSubArray();
        System.out.println(max_sum);
        
        //handles all negative numbers (kadanes variation)
        int max_sum_k = maxSumSubArrayWithAtLeastKNums();
        System.out.println(max_sum_k);
    }
    
    private static int maxSumSubArrayWithAtLeastKNums() {
        Integer[] arr = {-2, -1, -3, -4, -1};
        int k = 2;
        int n = arr.length;
        
        Integer[] maxSum = new Integer[n];
        maxSum[0] = arr[0];
        
        int cur = arr[0];
        for(int i=1;i<n;i++){
            cur += arr[i];
            maxSum[i] = Math.max(cur, arr[i]);
        }
        
        int sum = 0;
        int i=0;
        for(;i<k;i++)
            sum+=arr[i];
        
        int res = sum;
        for(;i<n;i++){
            sum  = sum + arr[i] - arr[i-k];
            
            res = Math.max(res, sum);
            res = Math.max(res, sum + maxSum[i-k]);
        }
        
        return res;
    }

    private static int maxSumSubArray() {

        int[] arr = {-2, -1, -3, -4, -1};
        
        int cur_sum = arr[0];
        int max = arr[0];
        int s = 0, e = 0, st = 0;
        
        for (int i = 1; i < arr.length; i++) {
            
            cur_sum += arr[i];
            
            if(cur_sum < arr[i]){
                cur_sum = arr[i];
                s = i;
            }
            
            if(cur_sum > max){
                max = cur_sum;
                st = s;
                e = i;
            }
        }
        return max;
    }

    private static int excelColumnNumber(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 26;
            int n = s.charAt(i) - 'A' + 1;
            num += n;
        }
        return num;
    }

    private static String excelColumnName(int n) {

        String s = "";
        int r;
        while (n > 0) {
            r = n % 26;
            if (r == 0) {
                s = "Z" + s;
                n = n / 26 - 1;
            } else {
                char c = (char) ((r - 1) + 'A');
                s = c + s;
                n = n / 26;
            }
        }
        return s;
    }

    public static String addBinary(String a, String b) {

        if (a == null || a.length() == 0)
            return b;
        if (b == null || b.length() == 0)
            return a;

        int m = a.length();
        int n = b.length();

        int i = m - 1;
        int j = n - 1;
        String s = "";
        int carry = 0;
        while (i >= 0 || j >= 0) {

            int sum = (i >= 0 ? a.charAt(i) - '0' : 0) ^ (j >= 0 ? b.charAt(j) - '0' : 0) ^ carry;

            carry = ((i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0) + carry) >= 2 ? 1 : 0;

            s = sum + s;

            i--;
            j--;
        }

        if (carry > 0)
            s = carry + s;

        return s;

    }

    private static void celebrity(int n) {

        int[][] map = { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 } };

        int i = 0;
        int j = n - 1;

        while (i < j) {
            if (knows(map, i, j))
                i++;
            else
                j--;
        }

        for (int k = 0; k < n; k++) {

            if (k != i && (knows(map, i, k) || !knows(map, k, i)))
                return;
        }

        System.out.println(i);

    }

    private static boolean knows(int[][] map, int i, int j) {
        return map[i][j] == 1;
    }

    private static void romanToInteger() {

        String s = "MMMMMMMMMDCCCLXXVI";
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int len = s.length(), result = map.get(s.charAt(len - 1));
        for (int i = len - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
                result += map.get(s.charAt(i));
            else
                result -= map.get(s.charAt(i));
        }

        System.out.println(result);
    }

    enum Roman {
        M(1000), CM(900), D(500), CD(400), C(100), XC(90), L(50), XL(40), X(10), IX(9), V(5), IV(4), I(1);
        final int value;

        private Roman(int v) {
            value = v;
        }
    }

    private static void intToRoman() {

        int n = 9876;

        Roman[] values = Roman.values();
        String s = "";
        for (Roman r : values) {
            while (n >= r.value) {
                n -= r.value;
                s += r;
            }
        }

        System.out.println(s);

    }

    private static void maxProfit() {

        int[] arr = { 100, 180, 260, 310, 40, 535, 695, 70, 110, 220, 900 };
        int n = arr.length;
        int i = 0;
        int st = 0;
        int end = 0;
        int count = 0;
        int k = 2;
        while (i < n - 1) {

            // local minima
            while (i < n - 1 && arr[i + 1] <= arr[i])
                i++;

            if (i == n - 1) {
                break;
            }

            st = i;
            i++;

            // local maxima
            while (i < n && arr[i] >= arr[i - 1])
                i++;

            end = i - 1;

            count++;

            System.out.println(st + "--" + end);

            if (k == count) {
                break;
            }

        }
    }

    private static void productExceptItself() {

        int[] arr = { 1, 2, 3, 4 };
        int n = arr.length;
        Integer[] res = new Integer[n];

        res[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--)
            res[i] = res[i + 1] * arr[i + 1];
        int left = 1;
        for (int i = 0; i < n; i++) {
            res[i] = left * res[i];
            left = left * arr[i];
        }
        System.out.println(Arrays.deepToString(res));
    }

    private static void maxProductSubArray() {

        int[] arr = { 2, 3, -2, 4 };
        int n = arr.length;
        int[] max = new int[n];
        int[] min = new int[n];
        int result = arr[0];

        max[0] = arr[0];
        min[0] = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > 0) {
                max[i] = Math.max(arr[i], max[i - 1] * arr[i]);
                min[i] = Math.min(arr[i], min[i - 1] * arr[i]);
            } else {
                max[i] = Math.max(arr[i], min[i - 1] * arr[i]);
                min[i] = Math.min(arr[i], max[i - 1] * arr[i]);
            }

            result = Math.max(result, max[i]);
        }

        System.out.println(result);
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

        Integer[] arr = { 2,1 };

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

        //System.out.println(Arrays.deepToString(arr));
        //System.out.println(i + " " + j);

        Integer[] a = Arrays.copyOfRange(arr, 0, j);
        //System.out.println(Arrays.deepToString(a));

        for (int k = 0; k < a.length; k++) {
            int val = Math.abs(a[k]) - 1;
            if (val < a.length && a[val] >= 0)
                a[val] = -a[val];
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
