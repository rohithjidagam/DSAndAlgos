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
        System.out.println();
        System.out.println("**********Anagram Search Method***********");
        String txt = "BACDGABCDA";
        String pat = "ABCD";

        searchAllAnagramsOfPatInText(txt.toCharArray(), pat.toCharArray());
        
        System.out.println("**********Pattern Searching (Robin-Karp)***********");
        

    }

    private static void searchAllAnagramsOfPatInText(char[] txt, char[] pat) {

        int m = pat.length;
        int n = txt.length;

        int[] countP = new int[256];
        int[] countT = new int[256];

        for (int i = 0; i < m; i++) {
            countP[pat[i]]++;
            countT[txt[i]]++;
        }

        for (int i = m; i < n; i++) {

            if (compare(countP, countT)) {
                System.out.println("Found at: " + (i - m));
            }

            countT[txt[i]]++;
            countT[txt[i - m]]--;
        }

        if (compare(countP, countT)) {
            System.out.println("Found at: " + (n - m));
        }

    }

    private static boolean compare(int[] countP, int[] countT) {

        for (int i = 0; i < countP.length; i++) {
            if (countP[i] != countT[i])
                return false;
        }

        return true;
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

class WQ implements Comparator<WQ> {

    @Override
    public int compare(WQ o1, WQ o2) {
        // TODO Auto-generated method stub
        return 0;
    }

}
