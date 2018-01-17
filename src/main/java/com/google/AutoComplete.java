package com.google;

import java.util.ArrayList;
import java.util.List;

public class AutoComplete {

    TrieNode root;

    public AutoComplete() {
        root = new TrieNode();
    }

    public static void main(String[] args) {

        AutoComplete ac = new AutoComplete();
        String[] words = { "c", "ca", "car", "cat", "cart", "caty", "cattle", "cap", "a", "an", "ant", "ab", "abc" };
        for (int i=0;i<words.length;i++)
            ac.insert(words[i], i+1);
        
        String prefix = "ca";
        
        TrieNode node = ac.searchHelper(prefix);
        if(node == null){
            System.out.println("No prefix found.");
            return;
        }
        
        List<TrieNode> list = new ArrayList<>();
        ac.parseTrie(node, list);
        System.out.println(list);
        
    }

    private void parseTrie(TrieNode node, List<TrieNode> list) {
        
        if(node.s != null)
            list.add(node);

        TrieNode cur = node;
        
        for (int i = 0; i < 26; i++) {
            if(cur.child[i] != null){
                parseTrie(cur.child[i], list);
            }
        }
    }

    private TrieNode searchHelper(String prefix) {

        TrieNode cur = root;
        char[] ch = prefix.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            int c = ch[i] - 'a';
            if(cur.child[c] == null)
                return null;
            cur = cur.child[c];
        }
        return cur;
    }

    private void insert(String s, int f) {

        TrieNode cur = root;
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            int c = ch[i] - 'a';
            if (cur.child[c] == null)
                cur.child[c] = new TrieNode();
            cur = cur.child[c];
        }
        cur.s = s;
        cur.freq = f;
    }

}

class TrieNode {
    TrieNode[] child;
    String s;
    int freq;
    private final int N = 26;

    public TrieNode() {
        child = new TrieNode[N];
        for (int i = 0; i < N; i++)
            child[i] = null;
        s = null;
        freq = 0;
    }
    
    @Override
    public String toString() {
        return s + "--" + freq;
    }
}
