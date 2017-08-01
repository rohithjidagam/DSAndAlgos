package com.uh;

public class ShortestUniquePrefixTrie {

    public static void main(String[] args) {

        String arr[] = { "zebra", "dogword", "ducket", "dovelet" };

        findPrefixes(arr, arr.length);

        String arr2[] = { "geekeegsforgeeks", "geekeegs", "geekeeg", "geekeegzer" };

        longestCommonPrefix(arr2, arr2.length);

    }

    private static void longestCommonPrefix(String[] arr2, int length) {

        TrieNode root = TrieNode.getTrieNode();
        root.freq = 0;

        for (int i = 0; i < length; i++) {
            insert(root, arr2[i]);
        }

        walkTrie(root);
        
        //print(root);
    }

    private static void print(TrieNode root) {

        if(root == null)
            return;
        
       TrieNode[] child = root.child;
       for (int i=0;i<child.length;i++) {
           if(child[i]!=null){
               System.out.println(child[i].freq + " --" + (char) (i + 'a'));
               print(child[i]);
           }
        
    }
    }

    private static void walkTrie(TrieNode root) {

        TrieNode cur = root;
        String s = "";
        Sum sum = new Sum();
        sum.count = 0;
        while (cur != null && cur.child != null && count(cur.child, sum) == 1) {
            cur = cur.child[sum.count];
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
            
            if(i == s.length() - 1)
                cur.isLeaf = true;

            cur = cur.child[ch];

        }
    }

}

class TrieNode {
    int freq;
    TrieNode[] child;
    boolean isLeaf;

    public static TrieNode getTrieNode() {

        TrieNode node = new TrieNode();
        node.freq = 1;
        node.child = new TrieNode[26];
        node.isLeaf = false;

        for (int i = 0; i < 26; i++) {
            node.child[i] = null;
        }

        return node;

    }
}
