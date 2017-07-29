package com.uh;

public class BNinaryTreeChildSums {

    static BNode root;

    public static void main(String[] args) {

        root = new BNode(10);
        root.left = new BNode(8);
        root.right = new BNode(2);
        root.right.left = new BNode(2);
        root.right.right = new BNode(0);
        root.left.left = new BNode(3);
        root.left.right = new BNode(5);

        System.out.println(childSum(root));

        root = new BNode(26);
        root.left = new BNode(10);
        root.right = new BNode(3);
        root.right.right = new BNode(3);
        root.right.left = new BNode(0);
        root.left.left = new BNode(4);
        root.left.right = new BNode(6);

        System.out.println(childSumTree(root));

        int[] arr = new int[10];
        int sum = 40;
        Sum s = new Sum();
        rootToLeafPath(root, 0, arr, sum, s);
        System.out.println(s.sum);

        root = new BNode(50);
        root.left = new BNode(7);
        root.right = new BNode(2);
        root.right.right = new BNode(30);
        root.right.left = new BNode(1);
        root.left.left = new BNode(3);
        root.left.right = new BNode(5);

        // postorder
        convertToSumTree(root);
        inorder(root);
    }

    private static void inorder(BNode node) {

        if (node == null)
            return;

        inorder(node.left);

        System.out.print(node.data + " ");

        inorder(node.right);
    }

    private static void convertToSumTree(BNode node) {

        int ld = 0;
        int rd = 0;
        int diff = 0;
        if (node == null || (node.left == null && node.right == null))
            return;
        else {
            // postorder
            convertToSumTree(node.left);
            convertToSumTree(node.right);

            if (node.left != null)
                ld = node.left.data;

            if (node.right != null)
                rd = node.right.data;

            diff = ld + rd - node.data;

            if (diff > 0)
                node.data = node.data + diff;

            if (diff < 0)
                increment(node, -diff);

        }

    }

    private static void increment(BNode node, int diff) {

        if (node.left != null) {
            node.left.data += diff;
            increment(node.left, diff);
        } else if (node.right != null) {
            node.right.data += diff;
            increment(node.right, diff);
        }
    }

    private static void rootToLeafPath(BNode node, int i, int[] arr, int sum, Sum s) {

        if (node == null)
            return;

        arr[i] = node.data;
        i++;

        int cur_sum = 0;
        if (node.left == null && node.right == null) {
            for (int j = 0; j < i; j++) {
                System.out.print(arr[j] + " ");
                cur_sum += arr[j];
            }
            System.out.println();

            if (cur_sum > s.sum) {
                s.sum = cur_sum;
            }
        }

        rootToLeafPath(node.left, i, arr, sum, s);
        rootToLeafPath(node.right, i, arr, sum, s);
    }

    private static boolean childSumTree(BNode node) {

        int ld = 0, rd = 0;
        if (node == null || (node.left == null && node.right == null))
            return true;

        ld = sum(node.left);
        rd = sum(node.right);

        if (node.data == ld + rd && childSumTree(node.left) && childSumTree(node.right))
            return true;

        return false;
    }

    private static int sum(BNode node) {
        if (node == null)
            return 0;
        return node.data + sum(node.left) + sum(node.right);
    }

    private static boolean childSum(BNode node) {

        int ld = 0, rd = 0;
        if (node == null || (node.left == null && node.right == null))
            return true;

        if (node.left != null)
            ld = node.left.data;

        if (node.right != null)
            rd = node.right.data;

        if ((node.data == ld + rd) && childSum(node.left) && childSum(node.right))
            return true;

        return false;
    }

}
