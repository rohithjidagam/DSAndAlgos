package com.uh;

import java.util.HashMap;
import java.util.Map;

public class Frequencies {

    public static void main(String[] args) {

        int arr[] = { 1, 1, 1, 2, 3, 3, 5, 5, 8, 8, 8, 9, 9, 10 };

        Map<Integer, Integer> map = new HashMap<>();

        findFreq(arr, 0, arr.length - 1, map);
        
        System.out.println(map);
    }

    private static void findFreq(int[] a, int s, int e, Map<Integer, Integer> map) {

        if (a[s] == a[e]) {
            if(map.get(a[s]) !=null)
            map.put(a[s], map.get(a[s]) + e - s + 1);
            else
                map.put(a[s], e-s+1);
        } else {
            int mid = (s + e) / 2;
            findFreq(a, s, mid, map);
            findFreq(a, mid + 1, e, map);
        }

    }

}
