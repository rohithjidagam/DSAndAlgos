package com.uh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
/*
public class FindApplicationErrors {

    class Application implements Comparator<Application> {

        int errorCount;
        String applicationName;

        // getters and setters

        @Override
        public int compare(Application a1, Application a2) {
            return a1.errorCount > a2.errorCount ? 1 : -1;
        }
    }

    Map<String, Integer> map = new HashMap<>();
    Queue<Application> queue = new PriorityQueue<>();

    public Set<String> getTop5ApplicationsWithMostErrors(String s) {
        for (Entry<String, Integer> e : map.entrySet()) {
            Application a = new Application();
            a.setApplicaitonName(e.getKey());
            a.setCount(e.getValuue());
            queue.add(a);
        }
        int i = 0;
        Set<String> returnSet = new HashSet<>();
        while (i < 5) {
            Application a = queue.peek();
            returnSet.add(a.getApplicationName());
            i++;
        }
        return returnSet;
    }

    public void readLogFile(File file) {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = "";
        while ((s = br.readLine()) != null) {
            if (isError(s)) {
                String applicationName = s.split("|")[0];
                if (map.containsKey(applicationName)) {
                    int count = map.get(applicationName);
                    map.put(applicationName, ++count);
                } else {
                    map.put(applicationName, 1);
                }
            }
        }
    }

    public void parseDirectory(File dir) {
        for (File f : dir.getFiles()) {
            if (f.isDirectory()) {
                parseDirectory(f);
            } else {
                readLogFile(f);
            }
        }
    }

    public static void main(String[] args) {
        FindApplicationErrors a = new FindApplicationErrors();
        // Top level directory
        a.parseDirectory(topLevelDirectory);

    }
} */
