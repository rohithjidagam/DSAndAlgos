package com.uh;

public class StringExamples2 {

    public static void main(String[] args) {
        palindrome();

        boolean iso = isomorphic("aabcccadefgh", "xxyzzzxpqrst");
        System.out.println(iso);

        String res = multiply2Large();
        System.out.println(res);

        String add = add2Large();
        System.out.println(add);

        String diff = diff2Large();
        System.out.println(diff);

        reverseString("   a  b  c def ggg  ,, hi");
    }

    private static void reverseString(String s) {
        String res = "";
        s = s.trim();
        int i = 0;
        String[] split = s.split(" ");
        for (String st : split) {
            if (!st.isEmpty())
                res += reverse(st);
            if (!st.isEmpty() && i != split.length - 1)
                res += " ";

            i++;
        }

        System.out.println(reverse(res));

    }

    static String reverse(String s) {
        char[] ch = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            i++;
            j--;
        }

        return new String(ch);
    }

    private static String diff2Large() {

        String s1 = "11443333311111111100";
        String s2 = "1144422222221111";

        int n1 = s1.length();
        int n2 = s2.length();

        int[] res = new int[n1 + 1];
        int id = 0;
        int i = n1 - 1;
        int j = n2 - 1;
        int borrow = 0;

        while (i >= 0 || j >= 0) {

            int m1 = s1.charAt(i) - '0';

            int m2 = 0;
            if (j >= 0)
                m2 = s2.charAt(j) - '0';
            else
                m2 = 0;

            int diff = m1 - m2 - borrow;

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else
                borrow = 0;

            res[id++] = diff;

            i--;
            j--;
        }

        String s = "";
        int k = res.length - 1;
        while (k >= 0)
            s += res[k--];

        return s;
    }

    private static String add2Large() {

        String s1 = "3333311111111111";
        String s2 = "44422222221111";
        int n1 = s1.length();
        int n2 = s2.length();

        int[] res = new int[n1 + 1];
        int id1 = 0;
        int carry = 0;

        int i = n1 - 1;
        int j = n2 - 1;

        while (i >= 0 || j >= 0) {

            int m1 = s1.charAt(i) - '0';
            int m2 = 0;
            if (j >= 0)
                m2 = s2.charAt(j) - '0';
            else
                m2 = 0;

            int sum = m1 + m2 + carry;

            carry = sum / 10;
            res[id1++] = sum % 10;

            i--;
            j--;

        }

        if (carry > 0)
            res[id1] = carry;

        int k = res.length - 1;
        while (k >= 0 && res[k] == 0)
            k--;

        if (k == -1)
            return "0";

        String s = "";
        while (k >= 0) {
            s += res[k--];
        }
        return s;
    }

    private static String multiply2Large() {

        String s1 = "1235421415454545454545454544";
        String s2 = "1714546546546545454544548544544545";

        int n1 = s1.length();
        int n2 = s2.length();

        if (n1 == 0 || n2 == 0)
            return "0";

        int[] res = new int[n1 + n2];

        int id1 = 0;
        int id2 = 0;

        for (int i = n1 - 1; i >= 0; i--) {

            int carry = 0;
            int m1 = s1.charAt(i) - '0';
            id2 = 0;

            for (int j = n2 - 1; j >= 0; j--) {

                int m2 = s2.charAt(j) - '0';

                int prod = m1 * m2 + res[id1 + id2] + carry;

                carry = prod / 10;
                res[id1 + id2] = prod % 10;
                id2++;

            }

            if (carry > 0)
                res[id1 + id2] = carry;

            id1++;

        }

        int i = res.length - 1;
        while (i >= 0 && res[i] == 0)
            i--;

        if (i == -1)
            return "0";

        String s = "";
        while (i >= 0) {
            s += res[i--];
        }
        return s;
    }

    private static boolean isomorphic(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        if (m != n)
            return false;

        boolean[] vis = new boolean[26];
        int[] map = new int[26];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }

        for (int i = 0; i < m; i++) {

            if (map[s1.charAt(i) - 'a'] == -1) {

                if (vis[s2.charAt(i) - 'a'])
                    return false;

                vis[s2.charAt(i) - 'a'] = true;
                map[s1.charAt(i) - 'a'] = s2.charAt(i) - 'a';
            } else if (map[s1.charAt(i) - 'a'] != s2.charAt(i) - 'a') {
                return false;
            }
        }

        return true;

    }

    private static void palindrome() {
        Integer x = 15;
        Integer output = 0;
        String s = Integer.toBinaryString(x);
        System.out.println(s);
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i++) == s.charAt(j--));
        if (i >= j) {
            output = 1;
            System.out.println(output);
        } else {
            System.out.println(output);
        }
    }

}
