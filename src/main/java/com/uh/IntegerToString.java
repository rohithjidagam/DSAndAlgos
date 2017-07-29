package com.uh;

import java.util.HashMap;
import java.util.Map;

public class IntegerToString {

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        buildHashMap(map);

        int num = 25456;
        String n = num + "";
        int len = n.length();

        System.out.println(recut(map, n, len));

    }

    private static String recut(Map<Integer, String> map, String n, int len) {
        int num = Integer.parseInt(n);
        if (len < 2 || (len < 3 && num < 20)) {
            return map.get(num);
        } else if (len < 3) {
            String f = map.get(Integer.parseInt(n.charAt(0) + "0"));
            String s = map.get(n.charAt(1));
            return f + s;
        } else if (len < 4) {
            return map.get(Integer.parseInt(n.charAt(0) + "0")) + "hunderd"
                    + recut(map, n.substring(1), n.substring(1).length());

        } else if (len < 5) {
            return map.get(Integer.parseInt(n.charAt(0) + "")) + "thousand"
                    + recut(map, n.substring(1), n.substring(1).length());
        } else if (len < 6) {
            return map.get(Integer.parseInt(n.charAt(0) + "0")) + "thousand"
                    + recut(map, n.substring(1), n.substring(1).length());
        }
        return "";
    }

    private static void buildHashMap(Map<Integer, String> map) {

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
        map.put(13, "thirteen");
        map.put(14, "fourteen");
        map.put(15, "fifteen");
        map.put(16, "sixteen");
        map.put(17, "seventeen");
        map.put(18, "eighteen");
        map.put(19, "nineteen");
        map.put(20, "twenty");
        map.put(30, "thirty");
        map.put(40, "forty");
        map.put(50, "fifty");
        map.put(60, "sixty");
        map.put(70, "seventy");
        map.put(80, "eighty");
        map.put(90, "ninety");

    }

}
