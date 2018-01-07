package com.google;

public class MaxProductLengths {

	public static void main(String[] args) {

		String[] strs = { "a", "bc", "cd", "efgh" };
		int max_len = product(strs);
		System.out.println(max_len);
	}

	private static int product(String[] strs) {

		Integer[] checker = new Integer[strs.length];
		for (int i = 0; i < checker.length; i++) {
			int num = 0;
			for (int j = 0; j < strs[i].length(); j++) {
				num |= (1 << strs[i].charAt(j) - 'a');
			}
			checker[i] = num;
		}
		
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < strs.length; i++) {
			for (int j = i + 1; j < strs.length; j++) {
				if ((checker[i] & checker[j]) == 0) //checking if the two strings have common character
					max = Math.max(max, strs[i].length() * strs[j].length());
			}
		}
		return max;

	}

}
