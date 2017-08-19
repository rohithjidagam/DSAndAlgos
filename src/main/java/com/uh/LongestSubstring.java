package com.uh;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    // Without repeating characters
    public static void main(String[] args) {

        String str = "aababcac";
        int n = str.length();

        int l = longSubLength(str, n);
        System.out.println(l);

        int l2 = longSubStringHashMap(str);
        System.out.println(l2);

        String text = "ADOBECODEBANC";
        String pat = "ABC";

        String res = minWindowSubString(text, pat);
        System.out.println(res);
    }

    private static String minWindowSubString(String s, String p) {

        int m = s.length();
        int n = p.length();

        if (n > m)
            return "";

        int[] text = new int[256];
        int[] pat = new int[256];

        for (int i = 0; i < n; i++) {
            pat[p.charAt(i)]++;
        }

        int count = 0;
        int start = 0;
        int st_ind = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {

            char ch = s.charAt(i);

            text[ch]++;

            if (pat[ch] != 0 && text[ch] <= pat[ch])
                count++;

            if (count == n) {
                while (pat[s.charAt(start)] == 0 || text[s.charAt(start)] > pat[s.charAt(start)]) {
                    if (text[s.charAt(start)] > pat[s.charAt(start)])
                        text[s.charAt(start)]--;
                    start++;
                }

                int len = i - start + 1;
                if (len < min) {
                    min = len;
                    st_ind = start;
                }

            }
        }

        if (st_ind == -1)
            return "";

        return s.substring(st_ind, st_ind + min);
    }

    private static int longSubStringHashMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (!map.containsKey(ch)) {
                map.put(ch, i);
                len++;
            } else {
                max = Math.max(max, len);
                len = Math.min(len + 1, i - map.get(ch));
                map.put(ch, i);
            }
        }

        return max;
    }

    private static int longSubLength(String str, int n) {

        int[] visited = new int[256];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }

        int cur_len = 0;
        int max_len = 0;
        int prev_index = 0;

        visited[str.charAt(0)] = 0;

        for (int i = 1; i < n; i++) {

            prev_index = visited[str.charAt(i)];

            if (prev_index == -1 || i - cur_len > prev_index)
                cur_len++;
            else {
                if (cur_len > max_len)
                    max_len = cur_len;

                cur_len = i - prev_index;

            }
            visited[str.charAt(i)] = i;
        }

        if (cur_len > max_len)
            max_len = cur_len;

        return max_len;
    }

}
