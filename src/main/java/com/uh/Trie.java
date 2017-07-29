package com.uh;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Trie {

    TNode root = null;

    public static void main(String[] args) {

        String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their", "toy", "theri", "thermometer" };

        Trie trie = new Trie();
        trie.insert(keys);

        trie.print(trie.root);

        trie.search("the");

        trie.phone("ther");

        trie.prefixMatch("thermon");

        trie.printAllWordsDict();

    }

    private void printAllWordsDict() {

        String[] dict = { "go", "bat", "me", "eat", "goal", "boy", "run" };
        root = new TNode();
        for (int i = 0; i < dict.length; i++) {
            insert(dict[i]);
        }

        char arr[] = { 'e', 'o', 'b', 'a', 'm', 'g', 'l' };

        boolean[] hash = new boolean[26];

        for (int i = 0; i < arr.length; i++) {
            hash[arr[i] - 'a'] = true;
        }

        TNode pchild = root;

        String s = "";
        for (int i = 0; i < hash.length; i++) {
            char c = (char) (i + 'a');
            if (hash[i] && pchild.child.get(c) != null) {
                s += c;
                searchWord(pchild.child.get(c), hash, s);
                s = "";
            }

        }

    }

    private void searchWord(TNode tNode, boolean[] hash, String s) {

        if (tNode.isLeaf)
            System.out.println(s);

        for (int i = 0; i < hash.length; i++) {

            char c = (char) (i + 'a');
            if (hash[i] && tNode.child.get(c) != null) {
                s += c;
                searchWord(tNode.child.get(c), hash, s);
            }

        }
    }

    private void prefixMatch(String s) {

        if (root != null) {
            String result = "";
            TNode cur = root;
            int prevMatch = 0;
            for (int i = 0; i < s.length(); i++) {

                char ch = s.charAt(i);
                TNode tNode = cur.child.get(ch);

                if (tNode != null) {
                    result += ch;
                    cur = tNode;

                    if (cur.isLeaf) {
                        prevMatch = i + 1;
                    }
                } else
                    break;

            }

            if (cur.isLeaf) {
                System.out.println(result.substring(0, prevMatch));
            } else {
                System.out.println(result);
            }
        }
    }

    private void phone(String s) {

        TNode prev = root;

        String prefix = "";
        for (int i = 0; i < s.length(); i++) {

            prefix += s.charAt(i);

            TNode node = prev.child.get(s.charAt(i));

            if (node == null) {
                System.out.println("\nNo Results Found for \"" + prefix + "\"");
                i++;
                break;
            }

            System.out.println("\nSuggestions based on \"" + prefix + "\" are");
            displayContactsUtil(node, prefix);

            // Change prevNode for next prefix
            prev = node;
        }

    }

    private void displayContactsUtil(TNode node, String prefix) {

        if (node.isLeaf)
            System.out.println(prefix);

        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 'a');
            TNode next = node.child.get(ch);
            if (next != null) {
                displayContactsUtil(next, prefix + ch);
            }
        }
    }

    private void search(String s) {

        if (root != null) {
            TNode cur = root;
            for (int i = 0; i < s.length(); i++) {
                System.out.println(s.charAt(i));
                TNode tNode = cur.child.get(s.charAt(i));
                if (tNode == null && i == s.length() - 1) {
                    System.out.println("Not Found");
                    break;
                }
                cur = tNode;

                if (cur != null && cur.isLeaf && i == s.length() - 1) {
                    System.out.println("Found");
                    break;
                }
            }
        }

    }

    private void print(TNode root) {

        if (root != null && root.child != null) {
            HashMap<Character, TNode> child = root.child;
            Set<Entry<Character, TNode>> entrySet = child.entrySet();
            for (Entry<Character, TNode> entry : entrySet) {
                if (entry.getValue() != null) {
                    System.out.print(entry.getKey() + " ");
                    print(entry.getValue());
                }
            }
            System.out.println();
        }
    }

    private void insert(String[] keys) {
        root = new TNode();
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i]);
        }
    }

    private void insert(String s) {

        TNode cur = root;

        for (int i = 0; i < s.length(); i++) {

            TNode next = cur.child.get(s.charAt(i));

            if (next == null) {
                next = new TNode();
                cur.child.put(s.charAt(i), next);
            }
            cur = next;

            if (i == s.length() - 1) {
                cur.isLeaf = true;
            }
        }

    }

}

class TNode {
    HashMap<Character, TNode> child;
    boolean isLeaf;

    public TNode() {
        child = new HashMap<>();
        isLeaf = false;
        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 'a');
            child.put(ch, null);
        }
    }
}
