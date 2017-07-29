package com.uh;

public class ShortestUniquePrefixTrie {

    public static void main(String[] args) {

        String arr[] = { "zebra", "dog", "duck", "dove" };

        findPrefixes(arr, arr.length);

        String arr2[] = { "geeksforgeeks", "geeks", "geek", "geezer" };

        longestCommonPrefix(arr2, arr2.length);

    }

    private static void longestCommonPrefix(String[] arr2, int length) {

        TrieNode root = TrieNode.getTrieNode();
        root.freq = 0;

        for (int i = 0; i < length; i++) {
            insert(root, arr2[i]);
        }

        walkTrie(root);
    }

    private static void walkTrie(TrieNode root) {

        TrieNode cur = root;
        String s = "";
        Sum sum = new Sum();
        sum.count = 0;
        while (cur != null && cur.child != null && count(cur.child, sum) == 1) {
            cur = root.child[sum.count];
            s += (char) (sum.count + 'a');
        }

        System.out.println(s);
    }

    private static int count(TrieNode[] child, Sum sum) {

        int count = 0;
        for (int i = 0; i < child.length; i++) {
            if (child[i] != null) {
                count++;
                sum.count = i;
            }
        }
        return count;
    }

    private static void findPrefixes(String[] arr, int length) {

        TrieNode root = TrieNode.getTrieNode();
        root.freq = 0;

        for (int i = 0; i < length; i++) {
            insert(root, arr[i]);
        }

        char[] prefix = new char[26];

        findPrefixUtil(root, prefix, 0);

    }

    private static void findPrefixUtil(TrieNode root, char[] prefix, int index) {

        if (root == null)
            return;

        if (root.freq == 1) {
            prefix[index] = ' ';
            System.out.println(new String(prefix));
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.child[i] != null) {
                prefix[index] = (char) (i + 'a');
                findPrefixUtil(root.child[i], prefix, index + 1);

            }
        }
    }

    private static void insert(TrieNode root, String s) {

        TrieNode cur = root;
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';

            if (cur.child[ch] == null) {
                cur.child[ch] = TrieNode.getTrieNode();
            } else {
                cur.child[ch].freq++;
            }

            cur = cur.child[ch];

        }
    }

}

class TrieNode {
    int freq;
    TrieNode[] child;

    public static TrieNode getTrieNode() {

        TrieNode node = new TrieNode();
        node.freq = 1;
        node.child = new TrieNode[26];

        for (int i = 0; i < 26; i++) {
            node.child[i] = null;
        }

        return node;

    }
}
