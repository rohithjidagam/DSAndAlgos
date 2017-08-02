package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentSkipListSet;

public class Sample2 {

    public static void main(String[] args) {

        crazy_sum(20);

        int[] nums = { 2, 3, 5, 2 };

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] * i;
        }
        System.out.println(sum);

        int count = 0;
        for (int i = 1; i < 37; i++) {
            if (isSquare(i))
                count++;
        }

        System.out.println(count);

        /*
         * int n1 = 6; int n2 = 12;
         * 
         * for (int i = n1; i <= n2; i++) {
         * 
         * if(isPrime(i)) System.out.println(i);
         * 
         * 
         * }
         * 
         * strings();
         * 
         * int[] arr = { -1, 1 }; int sum = adjacentElementsProduct(arr);
         * System.out.println(sum);
         * 
         * ConcurrentSkipListSet<String> list = new
         * ConcurrentSkipListSet<String>(); list.add("1"); list.add("2");
         * list.add("3"); list.add("4");
         * 
         * Iterator<String> iterator = list.iterator(); while
         * (iterator.hasNext()) { String next = iterator.next(); if
         * (next.equals("5")) list.remove(next); }
         * 
         * System.out.println(list.size());
         * 
         * String s1 = "thought"; String s2 = "sloughts"; ascii_delete(s1, s2);
         * 
         * Stack<Character> st = new Stack(); String s = "((())";
         * 
         * for (int i = 0; i < s.length(); i++) { if (s.charAt(i) == '(')
         * st.push(s.charAt(i)); else st.pop(); }
         * 
         * System.out.println(st.size());
         */
    }

    static boolean isSquare(int n) {
        int i = 1;
        int sum = 0;
        while (i <= n) {
            sum += i;
            if (sum == n)
                return true;
            i += 2;
        }
        return false;
    }

    private static void crazy_sum(int n) {

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 3; i < n; i++) {
            if ((i % 3 == 0 || i % 5 == 0) && i % 15 != 0) {
                list.add(i);
            }
        }
        int[] res = new int[list.size()];
        int j = 0;
        for (Integer val : list) {
            res[j++] = val;
        }
        Arrays.sort(res);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static void ascii_delete(String s1, String s2) {

        Map<Character, Integer> map = new HashMap<>();
        chechString(s1, map);
        // chechString(s2, map);

        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            Integer in = map.get(ch);
            if (in == null) {
                if (map2.get(ch) == null)
                    map2.put(ch, 1);
                else if (map2.get(ch) != null)
                    map2.put(ch, map2.get(ch) + 1);
            }
        }

        System.out.println(map2);

        Set<Character> keySet = map2.keySet();
        int count = 0;
        for (Character c : keySet) {
            count += c;
        }

        System.out.println(count);

    }

    private static void chechString(String s, Map<Character, Integer> map) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer in = map.get(ch);
            if (in == null)
                map.put(ch, 1);
            else
                map.put(ch, in + 1);
        }
    }

    static int adjacentElementsProduct(int[] inputArray) {

        int max = Integer.MIN_VALUE;

        for (int i = 0; i <= inputArray.length - 2; i++) {

            int prod = inputArray[i] * inputArray[i + 1];

            max = Math.max(prod, max);

        }

        return max;

    }

    private static void strings() {
        String s1 = "13a21b2c1321d";

        String s2 = "123";

        String st = "";

        boolean[] b = new boolean[256];
        for (int i = 0; i < s2.length(); i++) {
            b[s2.charAt(i)] = true;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (!b[s1.charAt(i)])
                st += s1.charAt(i);
        }

        System.out.println(st);
    }

}
