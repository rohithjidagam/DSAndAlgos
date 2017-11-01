package com.uh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAndSearchWord {

    Map<Integer, List<String>> map = new HashMap<>();

    static TTNode root = null;

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

        System.out.println("********");
        // Trie Method
        root = new TTNode();
        add("bad");
        add("mad");
        add("dad");
        System.out.println(find("b.."));
        System.out.println(find("b.d"));
        System.out.println(find(".a."));
        System.out.println(find("..d"));
        System.out.println(find("cad"));
        System.out.println(find("..."));

    }

    private static boolean find(String s) {

        return find(s.toCharArray(), 0, root);
    }

    private static boolean find(char[] ch, int i, TTNode cur) {

        if (i == ch.length)
            return cur.isLeaf;

        if (ch[i] == '.') {
            for (int j = 0; j < 26; j++) {
                if (cur.child[j] != null) {
                    return find(ch, i + 1, cur.child[j]);
                }
            }
        } else {
            if (cur.child[ch[i] - 'a'] == null)
                return false;
            else
                return find(ch, i + 1, cur.child[ch[i] - 'a']);
        }
        return false;
    }

    private static void add(String s) {

        TTNode cur = root;
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            if (cur.child[ch] == null)
                cur.child[ch] = new TTNode();
            cur = cur.child[ch];
        }
        cur.isLeaf = true;
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

class TTNode {
    TTNode[] child;
    boolean isLeaf;
    int N = 26;

    public TTNode() {
        isLeaf = false;
        child = new TTNode[N];
        for (int i = 0; i < child.length; i++) {
            child[i] = null;
        }
    }
}
