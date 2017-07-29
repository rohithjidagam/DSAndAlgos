package com.uh;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ShortestChain {

    public static void main(String[] args) {

        List<String> dict = new ArrayList<>();
        dict.add("POON");
        dict.add("LOON");
        dict.add("ROON");
        dict.add("PLEE");
        dict.add("SAME");
        dict.add("POIE");
        dict.add("PLEA");
        dict.add("PLIE");
        dict.add("POIN");
        dict.add("POAN");
        dict.add("PBAN");
        dict.add("PEEE");

        String start = "TOON";
        String end = "PLEA";

        Queue<Word> q = new LinkedList<>();

        q.add(new Word(1, start));
        System.out.println(start);
        while (!q.isEmpty()) {

            Word st = q.poll();
            Word e = null;
            Iterator<String> it = dict.iterator();
            while (it.hasNext()) {
                String w = it.next();
                if (isAdjacent(st.word, w)) {
                    e = new Word(st.dist + 1, w);
                    q.add(e);
                    System.out.println(w);
                    it.remove();
                    if (w == end) {
                        System.out.println(e.dist);
                        break;
                    }
                }
            }

        }
        
    }

    private static boolean isAdjacent(String start, String w) {
        
        int count = 0;

        if(start.length()!=w.length()) return false;
        
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != w.charAt(i))
                count++;
        }
        return count == 1 ? true : false;
    }

}

class Word {
    String word;
    int dist;

    Word(int l, String s) {
        word = s;
        dist = l;
    }
}
