package com.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TopKFrequentWords {

    public static void main(String[] args) {

        String[] words = { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" };
        int k = 4;

        List<String> top = topKFrequent(words, k);
        System.out.println(top);
    }

    private static List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> map = new TreeMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() == a.getValue()
                ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
        pq.addAll(map.entrySet());

        List<String> ret = new ArrayList<>();
        while (k-- > 0) {
            ret.add(pq.poll().getKey());
        }

        return ret;
    }

}
