package com.amazon;

import java.util.Map;
import java.util.TreeMap;

public class PalindromePartitionsDistinct {

	/*
	 * http://www.allenlipeng47.com/blog/index.php/2016/03/15/palindrome-pairs/
	 */
	public static void main(String[] args) {

		palindromeSubStrs("aabaa");
	}

	static void palindromeSubStrs(String s) {
		Map<String, Integer> m = new TreeMap<>();
		int n = s.length();

		int[][] dp = new int[2][n + 1];

		s = "*" + s + "$";

		for (int j = 0; j <= 1; j++) {
			int l = 0;
			dp[j][0] = 0;

			int i = 1;
			while (i <= n) {
				while (s.charAt(i - l - 1) == s.charAt(i + j + l))
					l++;

				dp[j][i] = l;
				int k = 1;
				while ((dp[j][i - k] != l - k) && (k < l)) {
					dp[j][i + k] = Math.min(dp[j][i - k], l - k);
					k++;
				}
				l = Math.max(l - k, 0);
				i += k;
			}
		}

		s = s.substring(1, s.length() - 1);

		m.put(s.substring(0, 1), 1);
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= 1; j++)
				for (int k = dp[j][i]; k > 0; k--)
					m.put(s.substring(i - k - 1, i - k - 1 + 2 * k + j), 1);
			m.put(s.substring(i, i + 1), 1);
		}

		System.out.println("Below are " + (m.size()) + " palindrome sub-strings");

		for (Map.Entry<String, Integer> ii : m.entrySet())
			System.out.println(ii.getKey());
	}
}
