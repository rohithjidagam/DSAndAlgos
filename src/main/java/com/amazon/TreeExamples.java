package com.amazon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreeExamples {

    public static void main(String[] args) {

        BNode root = new BNode(1);
        root.left = new BNode(3);
        root.left.left = new BNode(5);
        root.left.left.left = new BNode(6);
        root.right = new BNode(2);
        root.right.right = new BNode(9);
        root.right.right.right = new BNode(7);

        int width = treewidth(root);
        System.out.println(width);
        
        Map<Integer, Integer> map = new HashMap<>();
        int sum = equalPartition(root, map);
        System.out.println(sum);
        if(sum == 0)
            System.out.println(map.get(sum) > 1);
        else{
            System.out.println(sum%2 == 0 && map.containsKey(sum/2));
        }
    }

    private static int equalPartition(BNode root, Map<Integer, Integer> map) {

        if(root == null)
            return 0;
        int sum = root.data + equalPartition(root.left, map) + equalPartition(root.right, map);
        map.put(sum, map.getOrDefault(sum, 0)+1);
        return sum;
    }

    private static int treewidth(BNode root) {

        if (root == null)
            return 0;

        Queue<BNode> q = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        q.add(root);
        index.add(0);

        int max = 1;
        while (!q.isEmpty()) {
            int n = q.size();
            int left = 0;
            int right = 0;
            for (int i = 0; i < n; i++) {
                BNode node = q.poll();
                int ind = index.poll();
                if (i == 0)
                    left = ind;
                if (i == n - 1)
                    right = ind;

                if (node.left != null) {
                    q.add(node.left);
                    index.add(2 * ind);
                }

                if (node.right != null) {
                    q.add(node.right);
                    index.add(2 * ind + 1);
                }

            }

            max = Math.max(max, right - left + 1);

        }
        return max;
    }

}

class BNode {
    int data;
    BNode left, right;

    public BNode(int d) {
        data = d;
        left = right = null;
    }
}
