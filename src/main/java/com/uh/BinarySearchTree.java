package com.uh;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class BinarySearchTree {

    static BSTNode first, prev, mid, last;
    static BSTNode head;
    static BSTNode pre = null;

    public static void main(String[] args) {

        BSTNode root = new BSTNode(4);
        root.left = new BSTNode(2);
        root.right = new BSTNode(5);
        root.left.left = new BSTNode(1);
        root.left.right = new BSTNode(3);

        isBST(root);

        int[] arr = { 4, 2, 5, 1, 3 };
        printSorted(arr, 0, arr.length - 1);
        System.out.println();
        inOderSuccessor(root, new BSTNode(4));

        printKeys(root, 2, 10);

        correctSwapNodes();

        System.out.println();

        // inorder
        bstToDLL(root);

        while (head.right != null) {
            System.out.print(head.data + " ");
            head = head.right;
        }
        System.out.println();
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.left;
        }

        System.out.println();
        Integer[] preOrder = { 10, 5, 1, 7, 40, 30, 20, 50 };
        preOrderToInorder(preOrder);
    }

    private static void preOrderToInorder(Integer[] preOrder) {
        Stack<Integer> stack = new Stack<>();
        stack.push(preOrder[0]);

        for (int i = 1; i < preOrder.length; i++) {

            while (!stack.isEmpty() && stack.peek() < preOrder[i]) {
                System.out.print(stack.pop() + " ");
            }
            stack.push(preOrder[i]);

        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

    }

    private static void bstToDLL(BSTNode root) {

        if (root == null)
            return;

        bstToDLL(root.left);

        if (pre == null) {
            head = root;
        } else {
            root.left = pre;
            pre.right = root;
        }
        pre = root;
        bstToDLL(root.right);

    }

    private static void correctSwapNodes() {
        BSTNode root = new BSTNode(6);
        root.left = new BSTNode(10); // swapped
        root.right = new BSTNode(2); // swapped
        root.left.left = new BSTNode(1);
        root.left.right = new BSTNode(3);
        root.right.left = new BSTNode(7);
        root.right.right = new BSTNode(12);
        inoder(root);
        System.out.println();
        correct(root);

    }

    private static void correct(BSTNode root) {

        correctUtil(root, first, prev, mid, last);

        if (first != null && last != null) {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        } else if (first != null && mid != null) {
            int temp = first.data;
            first.data = mid.data;
            mid.data = temp;
        }

        inoder(root);

    }

    private static void inoder(BSTNode node) {

        if (node == null)
            return;

        inoder(node.left);

        System.out.print(node.data + " ");
        inoder(node.right);

    }

    private static void correctUtil(BSTNode root, BSTNode first, BSTNode prev, BSTNode mid, BSTNode last) {
        if (root != null) {
            correctUtil(root.left, first, prev, mid, last);
            if (prev != null && root.data < prev.data) {
                if (first == null) {
                    first = prev;
                    mid = root;
                } else {
                    last = root;
                }
            }
            prev = root;
            correctUtil(root.right, first, prev, mid, last);
        }
    }

    private static void sortedLinkedListToBST() {

        LinkedList<Integer> list = new LinkedList<>();
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        sortedLLtoBSTUtil(list.size());

    }

    private static void sortedLLtoBSTUtil(int size) {

    }

    private static void printKeys(BSTNode node, int k1, int k2) {

        if (node == null)
            return;

        if (node.data > k1) {
            printKeys(node.left, k1, k2);
        }

        if (node.data >= k1 && node.data <= k2) {
            System.out.println(node.data);
        }

        if (node.data < k2) {
            printKeys(node.right, k1, k2);
        }

    }

    private static void inOderSuccessor(BSTNode root, BSTNode node) {

        BSTNode suc = null;
        if (node.right != null) {
            BSTNode n = node.right;
            while (n.left != null) {
                n = n.left;
            }
            System.out.println(n.data);
        } else {
            while (root != null) {
                if (root.data < node.data) {
                    root = root.right;
                } else if (root.data > node.data) {
                    suc = root;
                    root = root.left;
                } else {
                    break;
                }
            }
            if (suc != null)
                System.out.println(suc.data);
        }

    }

    private static void printSorted(int[] arr, int s, int e) {

        if (s > e)
            return;
        printSorted(arr, 2 * s + 1, e);
        System.out.print(arr[s] + "-");
        printSorted(arr, 2 * s + 2, e);

    }

    private static void isBST(BSTNode root) {
        System.out.println(isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    private static boolean isBSTUtil(BSTNode node, int minValue, int maxValue) {

        if (node == null)
            return true;
        if (node.data < minValue || node.data > maxValue) {
            return false;
        }

        return isBSTUtil(node.left, minValue, node.data - 1) && isBSTUtil(node.right, node.data + 1, maxValue);
    }

}

class BSTNode {

    int data;
    BSTNode left;
    BSTNode right;

    public BSTNode(int d) {
        data = d;
        left = right = null;
    }

}