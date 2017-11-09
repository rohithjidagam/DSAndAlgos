package com.amazon;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public static void main(String[] args) {

        int n = 5;
        List<List<Integer>> list = pascal(n);
        System.out.println(list);

        
        List<Integer> list2 = nthPascal(n);
        System.out.println(list2);

    }

    private static List<Integer> nthPascal(int n) {

        List<Integer> list = new ArrayList<>();
        if (n < 0)
            return list;

        for (int i = 0; i < n + 1; i++) {
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j + 1) + list.get(j));
            }
        }

        return list;
    }

    private static List<List<Integer>> pascal(int n) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();

        if (n <= 0)
            return list;
        List<Integer> row = null;
        for (int i = 0; i < n; i++) {
            row = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i)
                    row.add(1);
                else
                    row.add(list.get(i - 1).get(j) + list.get(i - 1).get(j - 1));
            }
            list.add(row);
        }
        return list;
    }

}
