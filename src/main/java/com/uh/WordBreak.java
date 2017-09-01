package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordBreak {

    static final String[] dict = { "mobile", "samsung", "sam", "sung", "man", "mango", "and", "go" };

    public static void main(String[] args) {

        String str = "samsungandmango";

        boolean con = wordBreak(str);
        System.out.println(con);

        boolean con2 = wordBreakDP(str);
        System.out.println(con2);

        print(str, str.length(), "");

        List<String> list = printHashMap(str, new HashMap<String, List<String>>());
        System.out.println(Arrays.deepToString(list.toArray()));

    }

    private static List<String> printHashMap(String s, HashMap<String, List<String>> map) {
        if (map.get(s) != null)
            return map.get(s);

        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        for (int i = 0; i < dict.length; i++) {
            if (s.startsWith(dict[i])) {
                List<String> subs = printHashMap(s.substring(dict[i].length()), map);
                for (String sub : subs) {
                    res.add(dict[i] + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }

        map.put(s, res);
        System.out.println(map);
        return res;

    }

    private static void print(String str, int n, String res) {

        for (int i = 1; i <= n; i++) {

            String pre = str.substring(0, i);

            if (contains(pre)) {
                if (i == n) {
                    res += pre;
                    System.out.println(res);
                    return;
                }
                print(str.substring(i, n), n - i, res + pre + " ");
            }

        }

    }

    private static boolean wordBreakDP(String str) {

        int n = str.length();

        if (n == 0)
            return true;
        boolean[] dp = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!dp[i] && contains(str.substring(0, i))) {
                dp[i] = true;
            }
            if (dp[i]) {
                if (i == n)
                    return true;
                for (int j = i + 1; j <= n; j++) {
                    if (!dp[j] && contains(str.substring(i, j))) {
                        dp[j] = true;
                    }
                    if (dp[j] && j == n)
                        return true;
                }

            }

        }

        return false;
    }

    private static boolean wordBreak(String str) {

        int size = str.length();

        if (size == 0)
            return true;

        for (int i = 1; i <= size; i++) {
            if (contains(str.substring(0, i)) && wordBreak(str.substring(i, size)))
                return true;

        }
        return false;
    }

    private static boolean contains(String w) {
        for (int i = 0; i < dict.length; i++) {
            if (dict[i].equals(w))
                return true;
        }
        return false;
    }

}
