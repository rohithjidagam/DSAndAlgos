package com.uh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LunchOptions {

    static class Pair {
        public Pair(String key, String value) {
            this.pairKey = key;
            this.pairValue = value;
            // TODO Auto-generated constructor stub
        }

        String pairKey;
        String pairValue;

        public String getPairKey() {
            return pairKey;
        }

        public String getPairValue() {
            return pairValue;
        }
    }

    public static void main(String[] args) {
        List<Pair> p1 = new ArrayList<Pair>();
        p1.add(new Pair("Pizza", "Italian"));
        p1.add(new Pair("Curry", "Indian"));
        p1.add(new Pair("Masala", "Indian"));
        List<Pair> p2 = new ArrayList<Pair>();
        p2.add(new Pair("Jose", "Italian"));
        p2.add(new Pair("John", "Indian"));
        p2.add(new Pair("Sarah", "Thai"));
        p2.add(new Pair("Mary", "*"));

        print(f(p1, p2));
    }

    static void print(List<Pair> list) {
        for (Pair p : list) {
            System.out.println(p.getPairKey() + "," + p.getPairValue());
        }
    }

    static List<Pair> f(List<Pair> lunch, List<Pair> emps) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> lunchOps = new HashSet<>();
        for (Pair p : lunch) {
            lunchOps.add(p.getPairKey());
            map.put(p.getPairValue(), new ArrayList<String>());
        }

        for (Pair p : lunch) {
            map.get(p.getPairValue()).add(p.getPairKey());
        }

        List<Pair> output = new ArrayList<Pair>();
        for (Pair p : emps) {
            if (p.getPairValue().equals("*")) {
                for (String op : lunchOps) {
                    output.add(new Pair(p.getPairKey(), op));
                }
            } else {
                List<String> ops = map.get(p.getPairValue());
                if (ops != null) {
                    for (String s : ops) {
                        output.add(new Pair(p.getPairKey(), s));
                    }
                }
            }
        }
        return output;

    }

}
