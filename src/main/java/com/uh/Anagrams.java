package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Anagrams {

    public static void main(String[] args) {

        String wordArr[] = { "cat", "dog", "tac", "god", "act", "mad", "odg", "dam", "aaa", "bbb", "AAA", "BbB" };
        System.out.println("**********HashMap Method***********");
        usingHashMap(wordArr);
        System.out.println("**********Alternate Method***********");

        AWord[] dup = new AWord[wordArr.length];
        
        for (int i = 0; i < dup.length; i++) {
            char[] charArray = wordArr[i].toLowerCase().toCharArray();
            Arrays.sort(charArray);
            dup[i] = new AWord(i, new String(charArray));
        }
        
        Arrays.sort(dup);
        
        for (int i = 0; i < dup.length; i++) {
            System.out.print(wordArr[dup[i].i] + " ");
        }

    }

    private static void usingHashMap(String[] wordArr) {
        Map<Integer, List<String>> map = new HashMap<>();

        for (int i = 0; i < wordArr.length; i++) {
            int m = hash(wordArr[i]);
            List<String> list = map.get(m);
            if (list == null)
                list = new ArrayList<>();
            list.add(wordArr[i]);
            map.put(m, list);
        }

        Set<Integer> keySet = map.keySet();
        for (Integer s : keySet) {
            System.out.println(Arrays.deepToString(map.get(s).toArray()));
        }
        
    }

    private static int hash(String s) {
        s = s.toLowerCase();
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        return new String(ch).hashCode();
    }

}

class AWord implements Comparable<AWord> {
    int i;
    String s;

    public AWord(int i, String s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public int compareTo(AWord o) {
        return this.s.compareTo(o.s);
    }

}

class WQ implements Comparator<WQ>{

    @Override
    public int compare(WQ o1, WQ o2) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
