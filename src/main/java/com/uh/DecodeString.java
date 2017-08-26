package com.uh;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DecodeString {

    public static void main(String args[]) {
        // 3[a2[bd]g4[ef]h]
        String str = "2[a3[b1[c]]]";
        String res = decodeStringUsingStacks(str);
        System.out.println(res);

        Map<String, String> map = new HashMap<>();
        String enc = encode(res, map);
        System.out.println(enc);
    }

    private static String encode(String s, Map<String, String> map) {

        if (s.length() < 3)
            return s;
        if (map.get(s) != null)
            return map.get(s);
        String ret = s;

        for (int i = 1; i < s.length() / 2; i++) {
            String temp = s.substring(0, i);
            int count = 0;
            int offst = 0;

            while (offst + i <= s.length() && s.substring(offst, i).equals(temp)) {
                offst += i;
                count++;

                String code = count + "[" + encode(temp, map) + "]";
                String now = "";
                if (code.length() < i * count) {
                    now = code + encode(s.substring(offst, s.length() - offst), map);
                } else {
                    now = temp + encode(s.substring(i, s.length() - i), map);
                }
                if (now.length() < ret.length())
                    ret = now;
            }

        }

        map.put(s, ret);
        return ret;
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