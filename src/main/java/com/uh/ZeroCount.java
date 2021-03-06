package com.uh;

public class ZeroCount {

    public static void main(String[] args) {

        int nums[] = { 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1 };
        int k = 1;

        int max = 0, zero = 0;
        int ind = -1;
        for (int i = 0, j = 0; j < nums.length; j++) {
            if (nums[j] == 0)
                zero++;
            while (zero > k) {
                if (nums[i++] == 0)
                    zero--;
            }
            max = Math.max(max, j - i + 1);
            ind = i;
        }
        System.out.println(ind + 1);
        System.out.println(max);
    }

}
