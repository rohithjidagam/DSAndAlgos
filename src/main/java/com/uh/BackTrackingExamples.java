package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BackTrackingExamples {

    public static void main(String[] args) {

        int[] nums = { 1, 2, 3 };

        List<List<Integer>> lists = new ArrayList<>();
        subsets(lists, new ArrayList<>(), nums, 0);
        print(lists);

        int[] nums2 = { 2, 1, 2 };
        Arrays.sort(nums2);
        subsetsWithDups(lists, new ArrayList<>(), nums2, 0);
        print(lists);

        permutations(lists, new ArrayList<>(), nums);
        print(lists);

        int[] nums3 = { 1, 1, 2 };
        Arrays.sort(nums3);
        boolean[] used = new boolean[nums3.length];
        permutationsWithDups(lists, new ArrayList<>(), nums3, used);
        print(lists);

        int[] arr = { 2, 3, 6, 7 };
        int sum = 7;
        combinations(lists, new ArrayList<>(), arr, sum, 0);
        print(lists);

        int[] arr2 = { 10, 1, 2, 7, 6, 1, 5 };
        sum = 8;
        Arrays.sort(arr2);
        combinationsUnique(lists, new ArrayList<>(), arr2, sum, 0);
        print(lists);

        String s = "aab";
        palindromePartitions(lists, new ArrayList<>(), s, 0);
        print(lists);

        String st = "ABC";
        List<String> list = new ArrayList<>();
        permutations(st, 0, st.length() - 1, list);
        printList(list);

        int n = 32;
        factorCombinations(lists, new ArrayList<>(), n, 2);
        print(lists);

        int k = 3;
        sum = 9;
        combinaitonsSumWithKNums(lists, new ArrayList<>(), k, 1, sum);
        print(lists);

        String sp = "aabbc";
        List<String> list2 = generatePalindromes(sp);
        printList(list2);

        // nums = 123 -> 132
        nextPermutation(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        System.out.println("***********************");

        String ip = "25525511135";
        restoreIPAddress(ip, list, "", 0, 0);
        printList(list);
    }

    private static void restoreIPAddress(String ip, List<String> list, String cur, int id, int count) {

        if (count > 4)
            return;
        if (count == 4 && id == ip.length()) {
            list.add(cur);
        }

        for (int i = 1; i < 4; i++) {
            if (id + i > ip.length())
                break;
            String s = ip.substring(id, id + i);
            if ((s.startsWith("0") && s.length() > 1) || (i == 3 && Integer.parseInt(s) >= 256))
                continue;
            restoreIPAddress(ip, list, cur + s + (count == 3 ? "" : "."), id + i, count + 1);
        }

    }

    private static void combinaitonsSumWithKNums(List<List<Integer>> lists, ArrayList temp, int k, int st, int sum) {
        if (temp.size() == k && sum == 0) {
            lists.add(new ArrayList(temp));
            return;
        }
        for (int i = st; i <= 9; i++) {
            temp.add(i);
            combinaitonsSumWithKNums(lists, temp, k, i + 1, sum - i);
            temp.remove(temp.size() - 1);
        }

    }

    private static void nextPermutation(int[] nums) {

        int k = -1;

        // largest k such that arr[k] < arr[k+1]
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                k = i;
                break;
            }
        }

        if (k == -1) { // means arr elements are in descending order
            reverse(nums, 0, nums.length - 1);
        }

        int l = -1;
        // largest index l greater than k such that nums[k] < nums[l].
        for (int i = nums.length - 1; i > k; i--) {
            if (nums[i] > nums[k]) {
                l = i;
                break;
            }
        }

        // swap
        int temp = nums[k];
        nums[k] = nums[l];
        nums[l] = temp;

        reverse(nums, k + 1, nums.length - 1);
    }

    private static void reverse(int[] nums, int l, int r) {

        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }

    }

    private static List<String> generatePalindromes(String s) {
        List<String> list = new ArrayList<>();
        int odd = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        Set<Character> keySet = map.keySet();
        for (Character character : keySet) {
            if (map.get(character) % 2 != 0)
                odd++;
        }

        if (odd > 1)
            return list;

        String mid = "";
        List<Character> temp = new ArrayList<>();
        Set<Entry<Character, Integer>> entrySet = map.entrySet();
        for (Entry<Character, Integer> entry : entrySet) {
            char ch = entry.getKey();
            int f = entry.getValue();

            if (f % 2 != 0)
                mid += ch;
            for (int i = 0; i < f / 2; i++) {
                temp.add(ch);
            }
        }

        generatePalindromes(list, temp, new boolean[temp.size()], mid, new StringBuilder());
        return list;
    }

    private static void generatePalindromes(List<String> res, List<Character> list, boolean[] used, String mid,
            StringBuilder sb) {
        if (sb.length() == list.size()) {
            res.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1])
                continue;

            if (!used[i]) {
                used[i] = true;
                sb.append(list.get(i));
                generatePalindromes(res, list, used, mid, sb);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }

    private static void factorCombinations(List<List<Integer>> lists, ArrayList temp, int n, int st) {
        if (n <= 1) {
            if (temp.size() > 1)
                lists.add(new ArrayList(temp));
            return;
        }

        for (int i = st; i <= n; i++) {
            if (n % i == 0) {
                temp.add(i);
                factorCombinations(lists, temp, n / i, i);
                temp.remove(temp.size() - 1);
            }
        }

    }

    private static void permutations(String s, int i, int j, List<String> list) {
        if (i == j) {
            list.add(s);
        }
        for (int k = i; k <= j; k++) {
            s = swap(s, k, i);
            permutations(s, i + 1, j, list);
            s = swap(s, k, i);
        }

    }

    private static String swap(String s, int i, int j) {

        char[] ch = s.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return new String(ch);

    }

    private static void palindromePartitions(List<List<Integer>> lists, ArrayList temp, String s, int st) {
        if (st == s.length()) {
            lists.add(new ArrayList(temp));
        } else
            for (int j = st; j < s.length(); j++) {
                if (isPalindrome(s, st, j)) {
                    temp.add(s.substring(st, j + 1));
                    palindromePartitions(lists, temp, s, j + 1);
                    temp.remove(temp.size() - 1);
                }
            }
    }

    private static boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--))
                return false;
        return true;
    }

    private static void combinationsUnique(List<List<Integer>> lists, ArrayList temp, int[] arr, int sum, int i) {

        if (sum < 0)
            return;
        else if (sum == 0)
            lists.add(new ArrayList(temp));
        else {
            for (int j = i; j < arr.length; j++) {
                if (j > i && arr[j] == arr[j - 1])
                    continue;
                temp.add(arr[j]);
                combinationsUnique(lists, temp, arr, sum - arr[j], j + 1);
                temp.remove(temp.size() - 1);
            }
        }

    }

    private static void combinations(List<List<Integer>> lists, ArrayList temp, int[] arr, int sum, int i) {
        if (sum < 0)
            return;
        else if (sum == 0)
            lists.add(new ArrayList(temp));
        else {
            for (int j = i; j < arr.length; j++) {
                temp.add(arr[j]);
                combinations(lists, temp, arr, sum - arr[j], j);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private static void permutationsWithDups(List<List<Integer>> lists, ArrayList temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            lists.add(new ArrayList(temp));
        } else
            for (int j = 0; j < nums.length; j++) {
                if (used[j] || j > 0 && nums[j] == nums[j - 1] && !used[j - 1])
                    continue;
                used[j] = true;
                temp.add(nums[j]);
                permutationsWithDups(lists, temp, nums, used);
                used[j] = false;
                temp.remove(temp.size() - 1);
            }
    }

    private static void permutations(List<List<Integer>> lists, ArrayList temp, int[] nums) {

        if (temp.size() == nums.length) {
            lists.add(new ArrayList(temp));
        } else
            for (int j = 0; j < nums.length; j++) {
                if (temp.contains(nums[j]))
                    continue;
                temp.add(nums[j]);
                permutations(lists, temp, nums);
                temp.remove(temp.size() - 1);
            }

    }

    private static void subsetsWithDups(List<List<Integer>> lists, ArrayList temp, int[] nums, int i) {

        lists.add(new ArrayList(temp));
        for (int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j - 1])
                continue;
            temp.add(nums[j]);
            subsetsWithDups(lists, temp, nums, j + 1);
            temp.remove(temp.size() - 1);
        }
    }

    private static void subsets(List<List<Integer>> lists, List<Integer> temp, int[] nums, int st) {
        lists.add(new ArrayList(temp));
        for (int i = st; i < nums.length; i++) {
            temp.add(nums[i]);
            subsets(lists, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }

    }

    private static void printList(List<String> list) {
        System.out.println(Arrays.deepToString(list.toArray()));
        list.clear();
        System.out.println("*******************");
    }

    private static void print(List<List<Integer>> lists) {
        for (List<Integer> l : lists) {
            System.out.println(Arrays.deepToString(l.toArray()));
        }
        lists.clear();
        System.out.println("*******************");
    }

}
