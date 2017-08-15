package com.uh;

import java.util.Stack;

public class DecodeString {
    static String decodeString(String s) {
        if (s == null)
            throw new IllegalArgumentException(s);
        if (s.length() == 0)
            return s;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) && i + 2 < s.length() && s.charAt(i + 1) == '[') {
                int times = Integer.parseInt("" + s.charAt(i));
                i += 2;
                String ss = s.substring(i);
                String decoded = decodeString(ss);
                while (times-- > 0)
                    sb.append(decoded);
                i += decoded.length();
                continue;
            }
            if (s.charAt(i) == ']') {
                return sb.toString();
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        // 3[a2[bd]g4[ef]h]
        String str = "10[a]";
        System.out.println(decodeString(str));

        String res = decodeStringUsingStacks(str);
        System.out.println(res);
    }

    private static String decodeStringUsingStacks(String s) {

        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}