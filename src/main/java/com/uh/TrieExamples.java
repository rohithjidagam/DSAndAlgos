package com.uh;

public class TrieExamples {

    TrNode root = null;

    public static void main(String[] args) {

        String[] arr = { "the", "a", "there", "answer", "any", "by", "bye", "their" };
        TrieExamples trie = new TrieExamples();
        trie.root = new TrNode();
        for (int i = 0; i < arr.length; i++) {
            trie.insert(arr[i], trie.root);
        }

    }

    private void insert(String s, TrNode root) {
        
        

    }

}

class TrNode {
    boolean isLeaf;
    TrNode[] child;

    public TrNode() {
        child = new TrNode[26];
    }
}
