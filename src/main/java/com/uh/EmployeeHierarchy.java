package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EmployeeHierarchy {

    static Map<String, Integer> output = new HashMap<String, Integer>();

    public static void main(String[] args) {

        Map<String, String> input = new HashMap<String, String>();
        input.put("A", "C");
        input.put("B", "C");
        input.put("C", "F");
        input.put("D", "E");
        input.put("E", "F");
        input.put("F", "F");

        Map<String, List<String>> mgrMap = new HashMap<String, List<String>>();

        for (Entry<String, String> entry : input.entrySet()) {
            if (entry.getKey() != entry.getValue()) {
                List<String> list = mgrMap.get(entry.getValue());
                if (list == null)
                    list = new ArrayList<>();
                list.add(entry.getKey());
                mgrMap.put(entry.getValue(), list);
            }
        }
        
        System.out.println(mgrMap);

        for (String entry : input.keySet()) {
            populateCount(entry, mgrMap);
        }

        System.out.println(output);

    }

    private static int populateCount(String key, Map<String, List<String>> mgrMap) {

        int count = 0;
        if (!mgrMap.containsKey(key)) {
            output.put(key, 0);
            return 0;
        } else if (output.containsKey(key)) {
            count = output.get(key);
        } else {
            List<String> list = mgrMap.get(key);
            count = list.size();
            for (String emp : list) {
                count += populateCount(emp, mgrMap);
            }
            output.put(key, count);
        }
        return count;
    }

}
