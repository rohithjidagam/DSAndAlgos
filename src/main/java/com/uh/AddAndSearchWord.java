package com.uh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAndSearchWord {

    Map<Integer, List<String>> map = new HashMap<>();

    public static void main(String[] args) {

        AddAndSearchWord se = new AddAndSearchWord();
        se.addWord("bad");
        se.addWord("mad");
        se.addWord("dad");
        System.out.println(se.search("b.."));
        System.out.println(se.search("b.d"));
        System.out.println(se.search(".a."));
        System.out.println(se.search("..d"));
        System.out.println(se.search("cad"));
        System.out.println(se.search("..."));
        
    }

    public void addWord(String word) {
        int index = word.length();
        if (!map.containsKey(index)) {
            List<String> list = new ArrayList<>();
            list.add(word);
            map.put(index, list);
        } else {
            map.get(index).add(word);
        }
    }

    public boolean search(String word) {
        int index = word.length();
        if (!map.containsKey(index)) {
            return false;
        }

        List<String> list = map.get(index);
        for (String s : list) {
            if (isSame(s, word)) { // when word has '.'
                return true;
            }
        }
        return false;
    }

    public boolean isSame(String s, String word) { // word has '.'
        for (int i = 0; i < s.length(); i++) {
            if (word.charAt(i) != '.' && s.charAt(i) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
