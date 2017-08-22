package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class MaxConsecutiveOnes {

    public static void main(String[] args) {

        // flipping k 0's
        int[] arr = { 1, 0, 0, 1, 1, 0, 1, 0, 1, 1 };
        
        // k = 1
        int max = maxCons1s(arr, 2);
        System.out.println(max);
        
        System.out.println(findMaxConsecutiveOnes(arr, 2));
    }

    private static int maxCons1s(int[] arr, int k) {
        int max = 0, zero = 0;
        for (int i = 0, j = 0; j < arr.length; j++) {
            if (arr[j] == 0)
                zero++;
            
            while (zero > k) {
                if (arr[i++] == 0)
                    zero--;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
    
    /*
     * What if the input numbers come in one by one as an infinite stream? 
     * In other words, you can't store all numbers coming from the stream as 
     * it's too large to hold in memory. Could you solve it efficiently?
     */
    private static int findMaxConsecutiveOnes(int[] nums, int k) {                 
        int max = 0; // flip at most k zero
        Queue<Integer> zeroIndex = new LinkedList<>(); 
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zeroIndex.offer(h);
            if (zeroIndex.size() > k)                                   
                l = zeroIndex.poll() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;                     
    }

}
