package com.google;

import java.util.ArrayList;
import java.util.List;

public class WordSquares {

    public static void main(String[] args) {

        String[] strs = { "ball", "area", "lead", "lady", "wall" };
        List<List<String>> res = wordSquares(strs);
        for (List<String> list : res) {
            System.out.println(list);
        }
        
        String[] strs2 = {"abat","baba","atan","atal"};
        List<List<String>> res2 = wordSquares(strs2);
        for (List<String> list : res2) {
            System.out.println(list);
        }
    }

    private static List<List<String>> wordSquares(String[] words) {

        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0)
            return res;

        TNode root = new TNode();

        for (String s : words) {
            insert(s, root);
        }

        int len = words[0].length();
        TNode[] nodes = new TNode[len];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = root;
        }

        dfs(0, 0, len, nodes, res);

        return res;
    }

    private static void dfs(int r, int c, int len, TNode[] rows, List<List<String>> res) {

        if (r == len && c == len) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                list.add(rows[i].word);
            }
            res.add(list);
            return;
        }

        if (c < len) {
            TNode prev_r = rows[r];
            TNode prev_c = rows[c];

            for (int i = 0; i < 26; i++) {
                if (rows[r].child[i] != null && rows[c].child[i] != null) {

                    rows[r] = rows[r].child[i];
                    if(r != c)
                        rows[c] = rows[c].child[i];
                    
                    dfs(r, c+1, len, rows, res);
                    
                    rows[r] = prev_r;
                    if(c != r)
                        rows[c] = prev_c;
                }
            }
        } else {
            dfs(r+1, r+1, len, rows, res);
        }
    }

    private static void insert(String s, TNode root) {

        TNode cur = root;
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {

            int c = ch[i] - 'a';
            if (cur.child[c] == null)
                cur.child[c] = new TNode();

            cur = cur.child[c];
        }

        cur.isLeaf = true;
        cur.word = s;

    }

}

class TNode {
    TNode[] child;
    boolean isLeaf;
    String word;
    int N = 26;

    public TNode() {
        isLeaf = false;
        child = new TNode[N];
        word = null;
        for (int i = 0; i < child.length; i++) {
            child[i] = null;
        }
    }
}
