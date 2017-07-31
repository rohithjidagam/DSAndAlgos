package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTrackingExamples {

    public static void main(String[] args) {

        int[] nums = { 1, 2, 3 };

        List<List<Integer>> lists = new ArrayList<>();
        subsets(lists, new ArrayList<>(), nums, 0);
        print(lists);

        int[] nums2 = { 1, 2, 2 };
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
        
        
    }

    private static void permutationsWithDups(List<List<Integer>> lists, ArrayList temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            lists.add(new ArrayList(temp));
        } else
            for (int j = 0; j < nums.length; j++) {
                if(used[j] || j>0 &&nums[j] == nums[j-1] && !used[j-1])
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

    private static void print(List<List<Integer>> lists) {
        for (List<Integer> l : lists) {
            System.out.println(Arrays.deepToString(l.toArray()));
        }
        lists.clear();
        System.out.println("*******************");
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

}
