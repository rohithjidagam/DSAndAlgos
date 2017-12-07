package com.google;

import java.util.HashMap;
import java.util.Map;

public class TaskScheduler {

    public static void main(String[] args) {

        TaskScheduler t = new TaskScheduler();
        System.out.println(t.getTotalWaitTime("ABCD", 3));
        System.out.println(t.getTotalWaitTime("ABAD", 3));
        System.out.println(t.getTotalWaitTime("AAAA", 3));
        System.out.println(t.getTotalWaitTime("ABCACBDA", 4));
    }

    public int getTotalWaitTime(String s, int k) {
        if (s.length() == 0) {
            throw new IllegalArgumentException("No tasks declared");
        }
        Map<Character, Integer> runningTasks = new HashMap<>();
        int currentTime = 0;
        for (char t : s.toCharArray()) {
            if (runningTasks.containsKey(t)) {
                while ((runningTasks.get(t) - currentTime) >= 0) {
                    currentTime++;
                }
            }
            runningTasks.put(t, currentTime + k);
            currentTime++;
        }
        return currentTime;
    }

}
