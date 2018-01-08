package com.uh;

import java.util.Stack;

public class SerializeNAryTree {

    static String ser = "";

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
        serialize(tree.root);
        System.out.println(ser);

        System.out.println();

        // BNode root = deserialize(ser);

        BNode root = constructTree(ser);
        inoder(root);

    }

    private static BNode constructTree(String s) {

        Stack<BNode> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            int j = i;
            char c = s.charAt(i);
            if (c == ']')
                stack.pop();
            else if ((c >= '0' && c <= '9') || c == '-') {
                while (i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')
                    i++;
                BNode node = new BNode(Integer.parseInt(s.substring(j, i + 1)));
                if (!stack.isEmpty()) {
                    BNode parent = stack.peek();
                    if (parent.left != null)
                        parent.right = node;
                    else
                        parent.left = node;
                }
                stack.push(node);
            }

        }
        return stack.isEmpty() ? null : stack.peek();
    }

    private static BNode deserialize(String s) {
        if (s == "")
            return null;
        Stack<BNode> stack = new Stack<>();
        int n = s.length();
        int i = 0;
        while (i < n && s.charAt(i) != '[' && s.charAt(i) != ']')
            i++;

        BNode root = new BNode(Integer.parseInt(s.substring(0, i)));
        stack.push(root);
        while (i < n) {
            if (s.charAt(i) == ']') {
                i++;
                stack.pop();
            } else {
                int j = i;
                while (i < n && s.charAt(i) != '[' && s.charAt(i) != ']')
                    i++;
                BNode p = new BNode(Integer.parseInt(s.substring(j, i - j)));
                if (stack.peek().left != null)
                    stack.peek().right = p;
                else
                    stack.peek().left = p;
                stack.push(p);
            }
        }
        return root;
    }

    private static void inoder(BNode node) {

        if (node == null)
            return;

        inoder(node.left);

        System.out.println(node + "->" + node.next);
        inoder(node.right);

    }

    private static void serialize(BNode root) {

        if (root == null)
            return;

        ser += root.data;
        if (root.left != null || root.right != null) {
            ser += "[";
            serialize(root.left);
            ser += "]";
        }

        if (root.right != null) {
            ser += "[";
            serialize(root.right);
            ser += "]";
        }

    }

}
