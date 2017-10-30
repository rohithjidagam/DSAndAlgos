package com.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class InsertDeleteRamdomWithdups {

    List<Integer> list = new ArrayList<>();
    Map<Integer, Set<Integer>> map = new HashMap<>();
    Random r = new Random();

    public static void main(String[] args) {

        InsertDeleteRamdomWithdups ins = new InsertDeleteRamdomWithdups();
        /*
         * System.out.println(ins.insert(1)); print(ins);
         * 
         * System.out.println(ins.insert(-1)); print(ins);
         * 
         * System.out.println(ins.insert(2)); print(ins);
         * 
         * System.out.println(ins.insert(1)); print(ins);
         * 
         * System.out.println(ins.insert(1)); print(ins);
         * 
         * System.out.println(ins.random()); System.out.println(ins.random());
         * 
         * System.out.println(ins.remove(1)); print(ins);
         */

        System.out.println(ins.insert(0));
        print(ins);

        System.out.println(ins.remove(0));
        print(ins);

        System.out.println(ins.insert(-1));
        print(ins);

        System.out.println(ins.remove(0));
        print(ins);

        System.out.println(ins.random());
    }

    private boolean remove(int val) {

        if (!map.containsKey(val))
            return false;

        Set<Integer> set = map.get(val);
        List<Integer> asList = new ArrayList(set);
        int randId = r.nextInt(asList.size());
        int size = asList.size();
        Collections.swap(asList, randId, size - 1);
        asList.remove(size - 1);
        set = new HashSet<>(asList);
        if (set.size() == 0) {
            map.remove(val);
        } else {
            map.put(val, set);
        }
        int n = list.size();
        Collections.swap(list, randId, n - 1);
        list.remove(n - 1);

        return true;
    }

    private int random() {
        return list.get(r.nextInt(list.size()));
    }

    private static void print(InsertDeleteRamdomWithdups ins) {
        System.out.println(ins.map);
        System.out.println(ins.list);
    }

    private boolean insert(int val) {

        Set<Integer> set;
        boolean flag = false;
        if (map.containsKey(val)) {
            set = map.get(val);
        } else {
            flag = true;
            set = new LinkedHashSet<>();
        }

        int n = list.size();
        set.add(n);
        map.put(val, set);
        list.add(val);

        return flag;
    }

}
