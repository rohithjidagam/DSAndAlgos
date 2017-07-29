package com.uh;

import java.util.HashSet;

public class LongestSubstring {

    // Without repeating characters
    public static void main(String[] args) {

        String str = "aababcac";
        int n = str.length();

       // int l = longSubLength(str, n);
       // System.out.println(l);

        int l2 = longSubStringHashSet(str);
        System.out.println(l2);
    }

    private static int longSubStringHashSet(String s) {
        if (s == null || s.length() == 0)
            return 0;

        HashSet<Character> set = new HashSet<Character>();

        int max = 0;

        int i = 0;
        int start = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
            } else {
                max = Math.max(max, set.size());
                while (start < i && s.charAt(start) != c) {
                    set.remove(s.charAt(start));
                    start++;
                }
                start++;
            }

            i++;
        }
        
        max = Math.max(max, set.size());

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
