package com.amazon;

public class TreeDist {

    public static void main(String[] args) {

        int[] arr = { 5, 6, 3, 1, 2, 4 };

        //BST
        int dist = bstDist(arr, 6, 2, 4);
        System.out.println(dist);
    }

    static int bstDist(int[] arr, int n, int node1, int node2) {

        TreeNode root = null;

        root = insert(root, arr[0]);
        for (int i = 1; i < arr.length; i++) {
            insert(root, arr[i]);
        }

        inoder(root);

        if (!isPresent(root, node1) || !isPresent(root, node2))
            return -1;

        int dist = distance(root, node1, node2);

        return dist;
    }

    static boolean isPresent(TreeNode root, int data) {

        if (root.data == data) {
            return true;
        }
        if (root.left != null && isPresent(root.left, data)) {
            return true;
        }

        if (root.right != null && isPresent(root.right, data)) {
            return true;
        }

        return false;
    }

    private static int distance(TreeNode root, int node1, int node2) {

        if (root == null)
            return 0;

        if (root.data > node1 && root.data > node2)
            return distance(root.left, node1, node2);

        if (root.data < node1 && root.data < node2)
            return distance(root.right, node1, node2);

        if (root.data >= node1 && root.data <= node2)
            return distanceFromRoot(root, node1) + distanceFromRoot(root, node2);

        return -1;

    }

    static int distanceFromRoot(TreeNode root, int data) {
        if (root.data == data)
            return 0;
        else if (root.data > data)
            return 1 + distanceFromRoot(root.left, data);
        return 1 + distanceFromRoot(root.right, data);
    }

    private static void inoder(TreeNode node) {

        if (node == null)
            return;

        inoder(node.left);

        System.out.println(node.data);
        inoder(node.right);

    }

    private static TreeNode insert(TreeNode root, int key) {

        if (root == null) {
            root = new TreeNode(key);
        } else if (root.data > key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        return root;
    }

}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int d) {
        data = d;
        left = right = null;
    }
}
