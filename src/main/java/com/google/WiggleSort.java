package com.google;

import java.util.Arrays;

public class WiggleSort {

    public static void main(String[] args) {

        Integer[] nums = {3, 5, 2, 1, 6, 4};
        
        for (int i = 0; i < nums.length; i++) {
            if(i%2 == 1){
                if(nums[i-1] > nums[i])
                    swap(nums, i);
            } else if(i != 0 && nums[i-1] < nums[i])
                swap(nums, i);
            System.out.println(Arrays.deepToString(nums));
        }
    }

    private static void swap(Integer[] nums, int i) {
        int temp = nums[i-1];
        nums[i-1] = nums[i];
        nums[i] = temp;
    }

}
