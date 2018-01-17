package com.google;

import java.util.Arrays;

public class KEmptySlots {

	public static void main(String[] args) {

		int[] flowers = { 6, 5, 8, 9, 7, 1, 10, 2, 3, 4 };
		int k = 2;
		int n = flowers.length;
		Integer[] days = new Integer[n];

		for (int i = 0; i < n; i++) {
			days[flowers[i] - 1] = i + 1;
		}
		
		System.out.println(Arrays.deepToString(days));
		
		int left = 0;
		int right = k + 1;
		int res = Integer.MAX_VALUE;
		
		for(int i=1;right < n;i++){
			
			if(days[i] > days[left] && days[i] > days[right])
				continue;
			
			if(i == right){
				res = Math.min(res, Math.max(days[left], days[right]));
				System.out.println(days[left] + "--" + days[right] + "--" + res);
			}
			
			left = i;
			right = i + k + 1;
		}
		
		System.out.println(res);

	}

}
