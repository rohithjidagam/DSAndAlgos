package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    BNode root;

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        tree.root = new BNode(20);
        tree.root.left = new BNode(8);
        tree.root.right = new BNode(22);
        tree.root.right.left = new BNode(24);
        tree.root.right.right = new BNode(27);
        tree.root.left.left = new BNode(4);
        tree.root.left.right = new BNode(12);
        tree.root.left.right.left = new BNode(10);
        tree.root.left.right.left.left = new BNode(9);
        tree.root.left.right.right = new BNode(14);
        BNode target = tree.root.left.right;
        tree.printkdistanceNode(tree.root, target, 2);

        tree.printkdistanceNodeFromLeaf(tree.root, 2);

        tree.printSingles(tree.root);

        tree.treeSum(tree.root);

        tree.boundarytravesal(tree.root);
        tree.connectNodes(tree.root);
        tree.inoder(tree.root);

        int min = tree.minDepth(tree.root);
        System.out.println(min);

        int max = tree.maxDepth(tree.root);
        System.out.println(max);

    }

    private int maxDepth(BNode node) {

        if (node == null)
            return 0;

        int ld = maxDepth(node.left);
        int rd = maxDepth(node.right);

        return ld >= rd ? ld + 1 : rd + 1;
    }

    private int minDepth(BNode node) {

        if (node == null)
            return 0;

        return Math.min(minDepth(node.left), minDepth(node.right)) + 1;
    }

    private void boundarytravesal(BNode node) {

        if (node == null)
            return;

        System.out.print(node.data + "-");
        printLeftBoundary(node.left);

        printLeaves(node.left);
        printLeaves(node.right);

        printRightBoundary(node.right);
    }

    private void printLeaves(BNode node) {

        if (node == null)
            return;

        printLeaves(node.left);

        if (node.left == null && node.right == null) {
            System.out.print(node.data + "-");
        }
        printLeaves(node.right);
    }

    private void printRightBoundary(BNode node) {

        if (node == null)
            return;

        if (node.right != null) {
            printRightBoundary(node.right);
            System.out.print(node.data + "-");
        } else if (node.left != null) {
            printRightBoundary(node.left);
            System.out.print(node.data + "-");
        }
    }

    private void printLeftBoundary(BNode node) {

        if (node == null)
            return;

        if (node.left != null) {
            System.out.print(node.data + "-");
            printLeftBoundary(node.left);
        } else if (node.right != null) {
            System.out.print(node.data + "-");
            printLeftBoundary(node.right);
        }

    }

    private void inoder(BNode node) {

        if (node == null)
            return;

        inoder(node.left);

        System.out.println(node + "->" + node.next);
        inoder(node.right);

    }

    private void connectNodes(BNode root) {

        Queue<BNode> q = new LinkedList<>();
        root.level = 0;
        q.add(root);

        while (!q.isEmpty()) {

            BNode node = q.poll();
            int level = node.level;
            if (q.peek() != null && level == q.peek().level) {
                node.next = q.peek();
            } else {
                node.next = null;
            }

            if (node.left != null) {
                node.left.level = level + 1;
                q.add(node.left);
            }

            if (node.right != null) {
                node.right.level = level + 1;
                q.add(node.right);
            }

        }
    }

    private void treeSum(BNode root2) {
        System.out.println(treeSumUtil(root2, 0));
    }

    private int treeSumUtil(BNode node, int val) {
        if (node == null)
            return 0;

        val = val * 10 + node.data;
        if (node.left == null && node.right == null) {
            return val;
        }

        return treeSumUtil(node.left, val) + treeSumUtil(node.right, val);
    }

    private void printSingles(BNode node) {

        if (node == null)
            return;

        if (node.left != null && node.right != null) {
            printSingles(node.left);
            printSingles(node.right);
        } else if (node.left != null) {
            System.out.println(node.left.data);
            printSingles(node.left);
        } else if (node.right != null) {
            System.out.println(node.right.data);
            printSingles(node.right);
        }

    }

    private void printkdistanceNodeFromLeaf(BNode node, int k) {

        int[] path = new int[1000];
        boolean[] visited = new boolean[1000];
        printkdistanceNodeFromLeafUtil(node, path, visited, 0, k);

    }

    private void printkdistanceNodeFromLeafUtil(BNode node, int[] path, boolean[] visited, int pathIndex, int k) {

        if (node == null)
            return;

        path[pathIndex] = node.data;
        visited[pathIndex] = false;
        pathIndex++;

        if (node.left == null && node.right == null && pathIndex - k - 1 >= 0 && !visited[pathIndex - k - 1]) {
            System.out.println(path[pathIndex - k - 1]);
            visited[pathIndex - k - 1] = true;
            return;
        }
        printkdistanceNodeFromLeafUtil(node.left, path, visited, pathIndex, k);
        printkdistanceNodeFromLeafUtil(node.right, path, visited, pathIndex, k);

    }

    private int printkdistanceNode(BNode node, BNode target, int k) {

        if (node == null)
            return -1;

        if (node.data == target.data) {
            printKdistDown(node, k);
            return 0;
        }

        int dl = printkdistanceNode(node.left, target, k);

        if (dl != -1) {

            if (1 + dl == k) {
                System.out.println(node.data);
            } else {
                printKdistDown(node.right, k - dl - 2);
            }
            return 1 + dl;
        }

        int dr = printkdistanceNode(node.right, target, k);
        if (dr != -1) {
            if (1 + dr == k) {
                System.out.println(node.data);
            } else {
                printKdistDown(node.left, k - dr - 2);
            }
            return 1 + dr;
        }
        return -1;

    }

    private void printKdistDown(BNode node, int k) {

        if (node == null || k < 0)
            return;

        if (k == 0) {
            System.out.println(node.data);
            return;
        }
        printKdistDown(node.left, k - 1);
        printKdistDown(node.right, k - 1);
    }

}

class BNode {
    int data;
    BNode left;
    BNode right;
    BNode next;
    int level;

    public BNode(int d) {
        data = d;
        left = right = next = null;
        level = 0;
    }

    @Override
    public String toString() {
        return data + "";
    }
}
