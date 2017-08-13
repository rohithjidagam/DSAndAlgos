package com.uh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class StringExamples {

    public static void main(String[] args) {

        StringExamples se = new StringExamples();

        toggle();

        groupOccurences();

        compareVersionNumbers();

        removeExtraSpaces();

        runLengthEncoding();

        System.out.println(se.lineEncoding("wwwwaaaabbbcccd"));

        evaluateString();

        // priority queue impl
        rearrangeCharacters();

        palindromeQueries();

        distinctStrings();

        wildCardMatching("ge?ks*", "geeksforgeeks");

       // boolean b = isPalindrome("A man, ...a plan, a canal: Panama");
       // System.out.println(b);

        validParanthesis("(())[{}][[]]{([])}(");
    }

    private static void validParanthesis(String s) {

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.keySet().contains(ch)) {
                stack.push(ch);
            } else if (map.values().contains(ch)) {
                if (!stack.isEmpty() && map.get(stack.peek()) == ch) {
                    stack.pop();
                } else {
                    System.out.println(false);
                    break;
                }

            }

        }
        
        System.out.println(stack.isEmpty());

    }

    private static void wildCardMatching(String s1, String s2) {
        System.out.println(wilcardMatch(s1, s2));
    }

    private static boolean wilcardMatch(String s1, String s2) {

        if (s1.length() == 0 && s2.length() == 0)
            return true;

        if (s1.length() > 1 && s1.charAt(0) == '*' && s2.length() == 0)
            return false;

        if (s1.length() > 1 && s1.charAt(0) == '?'
                || (s1.length() != 0 && s2.length() != 0 && s1.charAt(0) == s2.charAt(0)))
            return wilcardMatch(s1.substring(1), s2.substring(1));

        if (s1.length() != 0 && s1.charAt(0) == '*')
            return wilcardMatch(s1.substring(1), s2) || wilcardMatch(s1, s2.substring(1));

        return false;
    }

    String lineEncoding(String s) {

        int j = 1;
        String res = "";

        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i) == s.charAt(i - 1)) {
                j++;
            } else {

                res += s.charAt(i - 1);
                if (j > 1) {
                    res += j;
                }
                j = 1;
            }
            if (i == s.length() - 1) {
                res += s.charAt(i);
                res += j;
            }

        }

        return res;

    }

    private static void distinctStrings() {

        String input[] = { "abcd", "acbd", "adcb", "cdba", "bcda", "badc" };

        Set<String> set = new HashSet<>();

        for (int i = 0; i < input.length; i++) {
            String s = encode(input[i]);
            System.out.println(s);
            if (!set.contains(s))
                set.add(s);
        }

        System.out.println(set.size());
    }

    private static String encode(String s) {
        int[] even = new int[26];
        int[] odd = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((i & 1) != 0)
                odd[c - 'a']++;
            else
                even[c - 'a']++;
        }

        String encoding = "";
        for (int i = 0; i < s.length(); i++) {
            encoding += even[i];
            encoding += odd[i];
        }

        return encoding;

    }

    private static void palindromeQueries() {

        String str = "geeks";
        char[] ch = str.toCharArray();
        int Q = 2;
        int i1 = 3;
        int i2 = 0;
        char c1 = 'e';
        int j1 = 0;
        int j2 = 2;
        char c2 = 's';
        int n = str.length();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - i - 1))
                set.add(i);
        }

        for (int i = 0; i < Q - 1; i++) {

            ch[i1] = ch[i2] = c1;

            if (i1 > n / 2)
                i1 = n - 1 - i1;
            if (i2 > n / 2)
                i2 = n - 1 - i2;

            addRemoveUnequal(ch, i1, n, set);

        }
    }

    private static void addRemoveUnequal(char[] ch, int i1, int n, Set<Integer> set) {

        if (ch[i1] == ch[n - 1 - i1]) {

            int i = new String(ch).indexOf(i1);
            if (set.contains(i))
                set.remove(i);
        } else
            set.add(i1);

    }

    private static void rearrangeCharacters() {

        String str = "aaabbbbcc";

        PriorityQueue<PS> pq = new PriorityQueue<>();

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }

        for (Character c : map.keySet()) {
            pq.add(new PS(map.get(c), c));
        }

        PS prev = new PS(-1, '#');
        String res = "";
        while (!pq.isEmpty()) {
            PS cur = pq.poll();
            char c = cur.s;
            res += c;

            if (prev.f > 0)
                pq.add(prev);

            cur.f--;
            prev = cur;

        }

        System.out.println(res);

    }

    private static void evaluateString() {

        String str = "4-2+6*3";

        char[] charArray = str.toCharArray();

        if (charArray.length == 0 || isOp(charArray[0])) {
            System.out.println("Invalid");
        }

        int res = charArray[0] - '0';

        for (int i = 1; i < charArray.length; i = i + 2) {

            char ch = charArray[i];
            int n2 = charArray[i + 1] - '0';

            if (!isOp(ch)) {
                System.out.println("Invalid here");
                break;
            }

            if (ch == '+')
                res += n2;
            else if (ch == '-')
                res -= n2;
            else if (ch == '*')
                res *= n2;
            else if (ch == '/')
                res /= n2;
            else {
                System.out.println("Invalid");
                break;
            }

        }

        System.out.println(res);
    }

    private static boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static void runLengthEncoding() {

        String str = "wwwwaaaabbbcccd";
        String st2 = "";
        int i;
        for (i = 0; i < str.length(); i++) {

            st2 += str.charAt(i);

            int len = 1;
            while (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                i++;
                len++;
            }
            st2 += len;

        }

        System.out.println(st2);

        String st3 = "";
        for (int j = 0; j < st2.length(); j++) {

            char charAt = st2.charAt(j);
            if (charAt <= 'z' && charAt >= 'a') {
                st3 += charAt;
            } else {
                int n = charAt - '0';
                for (int k = 0; k < n; k++)
                    st3 += st2.charAt(j - 1);
            }

        }
        System.out.println(st3);
    }

    private static void removeExtraSpaces() {

        String str = "   Hello Geeks . Welcome ,  to   GeeksforGeeks   .    ";

        char[] charArray = str.toCharArray();
        int n = charArray.length;
        int i = 0;
        int j = -1;

        boolean space = false;

        while (++j < n && charArray[j] == ' ')
            ;

        while (j < n) {
            if (charArray[j] != ' ') {
                if ((charArray[j] == '.' || charArray[j] == ',' || charArray[j] == '?') && i - 1 >= 0
                        && charArray[i - 1] == ' ') {
                    charArray[i - 1] = charArray[j++];
                } else {
                    charArray[i++] = charArray[j++];
                }
                space = false;
            } else if (charArray[j++] == ' ') {
                if (!space) {
                    charArray[i++] = ' ';
                    space = true;
                }
            }
        }

        System.out.println(new String(charArray).substring(0, i));

    }

    private static void compareVersionNumbers() {

        String str1 = "12.0.3";
        String str2 = "12.1.7";

        int num1 = 0;
        int num2 = 0;
        int i, j;
        for (i = 0, j = 0; i < str1.length() || j < str2.length(); i++, j++) {

            while (i < str1.length() && str1.charAt(i) != '.') {
                num1 = num1 * 10 + str1.charAt(i) - '0';
                i++;
            }

            while (j < str2.length() && str2.charAt(j) != '.') {
                num2 = num2 * 10 + str2.charAt(j) - '0';
                j++;
            }

            if (num1 > num2)
                System.out.println(num2);
            else
                System.out.println(num1);

            num1 = 0;
            num2 = 0;
        }
    }

    private static void groupOccurences() {

        String str = "geeksforgeeks";

        char[] charArray = str.toCharArray();
        int[] count = new int[26];

        for (int i = 0; i < charArray.length; i++) {
            count[charArray[i] - 'a']++;
        }

        for (int i = 0; i < charArray.length; i++) {
            while (count[charArray[i] - 'a']-- > 0) {
                System.out.print(charArray[i]);
            }
            // count[charArray[i] - 'a'] = 0;
        }

    }

    private static void toggle() {

        String str = "GeKf@rGeek$";
        char[] charArray = str.toCharArray();

        for (int i = 0; i < str.length(); i++) {
            if (charArray[i] >= 'a' && charArray[i] <= 'z') {
                int ch = charArray[i] - 'a' + 'A';
                charArray[i] = (char) ch;
            } else if (charArray[i] >= 'A' && charArray[i] <= 'Z') {
                int ch = charArray[i] - 'A' + 'a';
                charArray[i] = (char) ch;
            }
        }

        System.out.println(charArray);
    }

    private static boolean isPalindrome(String s) {

        if (s == null)
            return false;

        if (s == "" || s.length() == 1)
            return true;

        int n = s.length();
        s = s.toLowerCase();
        int i = 0;
        int j = n - 1;

        while (i < j) {
            while (!Character.isAlphabetic(s.charAt(i)) && !Character.isDigit(s.charAt(i))) {
                i++;
            }

            while (!Character.isAlphabetic(s.charAt(j)) && !Character.isDigit(s.charAt(j))) {
                j--;
            }

            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

        }

        return true;

    }

}

class PS implements Comparable<PS> {
    int f;
    char s;

    public PS(int i, char st) {
        f = i;
        s = st;
    }

    @Override
    public int compareTo(PS o) {
        return this.f < o.f ? 1 : this.f > o.f ? -1 : 0;
    }
}
