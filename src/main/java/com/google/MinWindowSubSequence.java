package com.google;

public class MinWindowSubSequence {

    public static void main(String[] args) {

        String S = "abcdebdde";
        String T = "bde";

        MinWindowSubSequence m = new MinWindowSubSequence();
        System.out.println(m.minWindow(S, T));
    }

    public String minWindow(String S, String T) {

        String res = "";
        int minLen = 100001;
        int m = S.length();
        int n = T.length();

        for (int i = 0; i <= m - n; i++) {

            if (S.charAt(i) != T.charAt(0))
                continue;

            int len = find(S.substring(i, Math.min(i + minLen, S.length())), T);
            if (len != -1 && len < minLen) {
                minLen = len;
                res = S.substring(i, i + len);
            }
        }

        return res;
    }

    private int find(String S, String T) {

        int i = 0;
        int j = 0;

        while (i < S.length() && j < T.length()) {
            if (S.charAt(i) == T.charAt(j)) {
                i++;
                j++;
                if (j == T.length())
                    return i;
            } else {
                i++;
            }
        }

        return -1;
    }

}
