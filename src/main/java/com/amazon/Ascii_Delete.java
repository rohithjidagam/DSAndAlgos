package com.amazon;

import java.util.HashMap;
import java.util.Map;

public class Ascii_Delete {

    public static void main(String[] args) {

        ascii_delete("thought", "sloughs");
        ascii_delete("cat", "bat");
        ascii_delete("cat", "cattle");

    }

    private static void ascii_delete(String str1, String str2) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int d = 0;

        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
            } else {
                d += ch;
            }
        }

        for (Character c : map.keySet()) {
            if (map.get(c) > 0) {
                d += c * map.get(c);
            }
        }

        System.out.println(d);
    }

}
