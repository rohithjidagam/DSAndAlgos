package com.uh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringsRecursion {

    public static void main(String[] args) {

        String str = "abc";
        breakStringsWithBrackets(str, 0, "");

        minBracketSwaps("[]][][[]][][");

        countWords("One Two\tThree \n\t\t\t\t\nFour\nFive");

        recurAddto9("4189");

        printAllStringsBySpaces("ABCD");

        removeMultipleSpaces("  Welcome   to ,  the   java class   .  ");

        // printAllSentences();

        // generateBinary();

        phoneNumberMapping();
    }

    private static void phoneNumberMapping() {

        Map<Character, String> hm = new HashMap<Character, String>();

        hm.put('2', "ABC");
        hm.put('3', "DEF");
        hm.put('4', "GHI");
        hm.put('5', "JKL");
        hm.put('6', "MNO");
        hm.put('7', "PQRS");
        hm.put('8', "TUV");
        hm.put('9', "WXYZ");
        hm.put('1', "1");
        hm.put('0', "0");

        StringBuilder str = new StringBuilder();
        String phNo = "012";
        printStrings(phNo, 0, hm, str);
    }

    private static void printStrings(String phNo, int i, Map<Character, String> hm, StringBuilder str) {

        if (i == phNo.length()) {
            System.out.println(str.toString());
            return;
        }

        String s = hm.get(phNo.charAt(i));
        for (int j = 0; j < s.length(); j++) {
            str.append(s.charAt(j));
            printStrings(phNo, i+1, hm, str);
            str.deleteCharAt(str.length()-1);
        }

    }

    private static void generateBinary() {

        String s = "1??0?101";

        generate(s, 0);
    }

    private static void generate(String s, int i) {

        char[] cs = s.toCharArray();

        if (i == cs.length) {
            System.out.println(cs);
            return;
        }

        if (cs[i] == '?') {

            cs[i] = '0';
            generate(new String(cs), i + 1);

            cs[i] = '1';
            generate(new String(cs), i + 1);
        } else {
            generate(new String(cs), i + 1);
        }
    }

    private static void printAllSentences() {

        String arr[][] = { { "you", "we" }, { "have", "are" }, { "sleep", "eat", "drink" } };

        String res = "";

        for (int i = 0; i < arr[0].length; i++) {
            if (arr[0][i] != "") {
                recurPrint(arr, 0, i, res);
            }
        }
    }

    private static void recurPrint(String[][] arr, int i, int j, String result) {

        result += arr[i][j];
        result += " ";

        if (i == arr.length - 1) {
            System.out.println(result.trim());
            return;
        }

        for (int k = 0; k < arr[i + 1].length; k++) {
            if (arr[i + 1][k] != "")
                recurPrint(arr, i + 1, k, result);
        }

    }

    private static void removeMultipleSpaces(String s) {

        s = s.trim();
        String res = "";
        int spaces = 0;
        int i;
        for (i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != ' ') {
                res += s.charAt(i);
            } else {
                if (spaces > 1 && s.charAt(i + 1) != ' ') {
                    res += " ";
                    spaces = 0;
                }
                spaces++;
            }
        }

        res += s.charAt(i);

        System.out.println("[" + res + "]");
    }

    private static void printAllStringsBySpaces(String s) {

        int n = s.length();
        char[] buf = new char[2 * n];

        buf[0] = s.charAt(0);
        printSpaces(s, buf, 1, 1, n);

    }

    private static void printSpaces(String s, char[] buf, int i, int j, int n) {

        if (i == n) {
            System.out.println(buf);
            return;
        }

        buf[j] = s.charAt(i);
        printSpaces(s, buf, i + 1, j + 1, n);

        buf[j] = ' ';
        buf[j + 1] = s.charAt(i);

        printSpaces(s, buf, i + 1, j + 2, n);
    }

    private static void recurAddto9(String s) {

        int[] d = new int[9];
        d[0] = 1;

        int res = 0;
        int zeros = 0;
        int mod_sum = 0;
        for (int i = 0; i < s.length(); i++) {

            int c = s.charAt(i) - '0';
            if (c == 0) {
                zeros++;
            } else {
                zeros = 0;
            }

            mod_sum += c;
            mod_sum %= 9;
            res += d[mod_sum];
            d[mod_sum]++;
            res -= zeros;

        }

        System.out.println(res);
    }

    private static void countWords(String s) {

        // 2 states
        // 1 - word
        // 0 - character
        int state = 0;
        int wc = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || s.charAt(i) == '\t' || s.charAt(i) == '\n') {
                state = 0;
            } else if (state == 0) {
                state = 1;
                wc++;
            }
        }
        System.out.println(wc);

    }

    private static void minBracketSwaps(String s) {

        int n = s.length();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '[')
                list.add(i);
        }

        int count = 0;
        int p = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {

            if (s.charAt(i) == '[') {
                count++;
                p++;
            } else {
                count--;
            }

            if (count < 0) {

                sum += list.get(p) - i;
                s = swap(s, i, list.get(p));
                p++;

                count = 1;
            }
        }
        System.out.println(s);
        System.out.println(sum);

    }

    private static String swap(String s, int i, Integer j) {
        char[] charArray = s.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return new String(charArray);

    }

    private static void breakStringsWithBrackets(String str, int i, String res) {

        if (i == str.length()) {
            System.out.println(res);
        }

        for (int j = i; j < str.length(); j++) {
            String sub = str.substring(i, j + 1);
            breakStringsWithBrackets(str, j + 1, res + "(" + sub + ")");
        }
    }

}
