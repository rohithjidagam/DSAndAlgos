package com.uh;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IsoscelesTriangle {

    public static void main(String[] args) {
        IsoscelesTriangle cf = new IsoscelesTriangle();
        // int[] inta = { 5, 3, 5, 10, 1, 10, 10 };
        int[] inta = { 5, 3, 2, 9, 5, 4, 9, 5, 5 };
        System.out.println(cf.isoscelesTriangles(inta));
    }

    int isoscelesTriangles(int[] a) {

        int c = 0;
        int count = 0;
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] < a[i + 1])
                c++;
            if (a[i] == a[i + 1])
                count++;
        }

        if (c == n - 1)
            return 0;
        if (count == n - 1)
            return (n * (n - 1) * (n - 2)) / 6;

        Isos[] res = new Isos[(n * (n - 1)) / 2];
        int k = 0;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i != j) {
                    if (a[i] == a[j] && i < j) {
                        res[k++] = new Isos(i, j, a[i] + a[j]);
                    }
                }
            }

        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < res.length; i++) {
            if (res[i] == null)
                break;
            if (map.get(res[i].sum) == null) {
                map.put(res[i].sum, 1);
            } else {
                map.put(res[i].sum, map.get(res[i].sum) + 1);
            }
        }

        Set<Integer> keySet = map.keySet();
        int max = Integer.MIN_VALUE;
        for (Integer integer : keySet) {
            if (map.get(integer) > max) {
                max = map.get(integer);
            }
        }

        int sum = 0;

        for (int i = 0; i < res.length; i++) {
            if (res[i] == null)
                break;
            Isos is = res[i];
            for (int j = 0; j < n; j++) {
                if (is.i != j && is.j != j && is.sum - a[j] != a[j]) {
                    if (is.sum > a[j]) {
                        sum++;
                    }
                }

            }

        }

        if (max > 3)
            max = max - 2;
        else
            max = max / 2;
        return sum + max;

    }

}

