package com.google;

import java.util.HashSet;
import java.util.Set;

public class NextClosestTime {

     int diff = Integer.MAX_VALUE;
     String res = "";
     int h;
     int m;
    
    public static void main(String[] args) {

        String time = "19:34";

        //O(24 * 60) searches
        String close = nextClosest(time);
        System.out.println(close);

        String close2 = nextClosest("23:59");
        System.out.println(close2);
        
        NextClosestTime n = new NextClosestTime();
        
        //dfs O(24) times
        System.out.println(n.nextClosest2("23:59"));
    }

    private String nextClosest2(String time) {

        int[] digit = new int[4];
        int tot = 0;
        String[] val = time.split(":");
        int hour = Integer.parseInt(val[0]);
        int minu = Integer.parseInt(val[1]);
        digit[tot++] = hour / 10;
        digit[tot++] = hour % 10;
        digit[tot++] = minu / 10;
        digit[tot++] = minu % 10;

        h = hour;
        m = minu;
        
        dfs(digit, 0, new int[4]);
        
        return res;
        
        
    }

    private void dfs(int[] digit, int i, int[] ret) {

        if(i == 4){
            int hr = 10 * ret[0] + ret[1];
            int min = 10 * ret[2] + ret[3];
            
            int d = diff(hr, min);
            if(d < diff){
                diff = d;
                res = valid(hr) + ":" + valid(min);
            }
        } else {
            for (int j = 0; j < 4; j++) {
                
                ret[i] = digit[j];
                if(i == 1){
                    int hr = 10 * ret[0] + ret[1];
                    if(hr >=0 && hr <= 23)
                        dfs(digit, i+1, ret);
                } else if(i == 3){
                    int min = 10 * ret[2] + ret[3];
                    if(min >= 0 && min <= 59)
                        dfs(digit, i+1, ret);
                } else {
                    dfs(digit, i+1, ret);
                }
            }
        }
    }

    private int diff(int hr, int min) {
        int c2o = 60 * 60 - h * 60 - m;
        int n2o = 60 * 60 - hr * 60 - min;
        return n2o < c2o ? c2o - n2o : c2o - n2o + 3600;
    }

    private static String nextClosest(String time) {

        String[] split = time.split(":");
        Set<Integer> set = new HashSet<>();

        int hour = add(set, split[0]);
        int min = add(set, split[1]);

        int[] times = new int[] { hour, min };
        next(times);

        while (!contains(times[0], times[1], set)) {
            next(times);
        }

        return valid(times[0]) + ":" + valid(times[1]);
    }

    private static String valid(int i) {

        if (i >= 0 && i <= 9)
            return "0" + i;
        return i + "";
    }

    private static boolean contains(int i, int j, Set<Integer> set) {
        return set.contains(i % 10) && set.contains(i / 10) && set.contains(j % 10) && set.contains(j / 10);
    }

    private static void next(int[] times) {

        int hour = times[0];
        int min = times[1];

        min++;
        if (min == 60) {
            hour++;
            min = 0;
            if (hour == 24)
                hour = 0;
        }

        times[0] = hour;
        times[1] = min;
    }

    private static int add(Set<Integer> set, String s) {
        int time = Integer.parseInt(s);
        set.add(time % 10);
        set.add(time / 10);
        return time;
    }

}
