package com.uh;

import java.util.Arrays;

public class MaximumSumSubArray {

    //by removing 1 element
    public static void main(String[] args) {

        Integer arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        
        int n = arr.length;
        
        Integer[] f = new Integer[n];
        Integer[] b = new Integer[n];
        
        int max_sum = Integer.MIN_VALUE;
        int cur_sum = 0;
        for (int i = 0; i < n; i++) {
            cur_sum += arr[i];
            if(max_sum < cur_sum){
                max_sum = cur_sum;
            }
            if(cur_sum < 0){
                cur_sum = 0;
            }
            f[i] = cur_sum;
        }
        System.out.println(max_sum);
        int cur = 0;
        for (int i = n-1; i >= 0; i--) {
            cur += arr[i];
            if(max_sum < cur){
                max_sum = cur;
            }
            if(cur < 0){
                cur = 0;
            }
            b[i] = cur; 
        }
        
        int fans = max_sum;
        System.out.println(fans);
        System.out.println(Arrays.deepToString(arr));
        System.out.println(Arrays.deepToString(f));
        System.out.println(Arrays.deepToString(b));
        for (int i = 1; i < n-1; i++) {
            fans = Math.max(fans, f[i-1]+b[i+1]);
        }
        
        System.out.println(fans);
    }

}
