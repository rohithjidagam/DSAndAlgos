package com.uh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class ReArrangeString {

    public static void main(String[] args) {

        rearrangeCharacters();

        String res = rearrangeCharactersWithKdistance();
        System.out.println(res);

        String res2 = rearrangeCharactersWithAtleastKdistance();
        System.out.println(res2);

        int count = taskScheduler();
        System.out.println(count);
    }

    private static int taskScheduler() {
        char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B' };
        int n = 2;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
        }

        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());
        
        pq.addAll(map.entrySet());
        
        int count = 0;
        while(!pq.isEmpty()){
            int k = n+1;
            List<Map.Entry> temp = new ArrayList<>();
            
            while(k > 0 && !pq.isEmpty()){
                
                Entry<Character, Integer> poll = pq.poll();
                poll.setValue(poll.getValue() - 1);
                temp.add(poll);
                k--;
                count++;
            }
            
            for (Entry<Character, Integer> entry : temp) {
                if(entry.getValue() > 0)
                    pq.add(entry);
            }
            
            if(pq.isEmpty())
                break;
            
            count +=k;
            
        }
        
        return count;
    }

    private static String rearrangeCharactersWithAtleastKdistance() {

        String s = "aabbcc";
        int k = 3;

        if (k == 0)
            return s;

        int n = s.length();
        if (n == 0)
            return "";

        Map<Character, Integer> freqMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        System.out.println(freqMap);

        Queue<PS> maxHeap = new PriorityQueue<PS>((x, y) -> y.f - x.f);
        Set<Character> keySet = freqMap.keySet();
        for (Character ch : keySet) {
            maxHeap.add(new PS(freqMap.get(ch), ch));
        }

        StringBuilder sb = new StringBuilder();

        Queue<PS> queue = new PriorityQueue<>(k);

        for (int i = 0; i < n; i++) {

            if (maxHeap.size() == 0) {
                return "";
            }
            PS poll = maxHeap.poll();
            sb.append(poll.s);
            int newVal = poll.f - 1;
            queue.add(new PS(newVal, poll.s));

            if (queue.size() == k) {
                PS p = queue.poll();
                if (p.f > 0) {
                    maxHeap.add(poll);
                }
            }
        }

        return sb.toString();
    }

    private static String rearrangeCharactersWithKdistance() {

        String s = "aabbcc";
        int k = 3;

        int n = s.length();
        if (n == 0)
            return "";

        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        Queue<PS> maxHeap = new PriorityQueue<PS>();
        Set<Character> keySet = freqMap.keySet();
        for (Character ch : keySet) {
            maxHeap.add(new PS(freqMap.get(ch), ch));
        }

        char[] res = new char[n];
        int st = 0;
        int cur = st;

        while (!maxHeap.isEmpty()) {
            PS poll = maxHeap.poll();
            int f = poll.f;
            char ch = poll.s;
            int l = 0;
            while (l < f) {
                res[st] = ch;
                st = st + k;
                if (st >= n) {
                    cur++;
                    st = cur;
                }
                l++;
            }
        }

        String out = "";
        for (int i = 0; i < res.length; i++)
            out += res[i];
        return out;
    }

    private static void rearrangeCharacters() {

        String str = "aaabbbbcc";

        PriorityQueue<PS> pq = new PriorityQueue<>();

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }

        for (Character c : map.keySet()) {
            pq.add(new PS(map.get(c), c));
        }

        PS prev = new PS(-1, '#');
        String res = "";
        while (!pq.isEmpty()) {
            PS cur = pq.poll();
            char c = cur.s;
            res += c;

            if (prev.f > 0)
                pq.add(prev);

            cur.f--;
            prev = cur;

        }

        System.out.println(res);

    }

}
