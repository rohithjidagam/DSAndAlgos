package com.uh;

import java.util.Stack;

public class TreeBottom {

    public static void main(String[] args) {

        String s = "(2 (7 (2 () ()) (6 (5 () ()) (11 () ()))) (5 () (9 (4 () ()) ())))";
        TreeBottom tb = new TreeBottom();
        tb.treeBottom(s);
    }

    int[] treeBottom(String tree) {

        BinaryTree binaryTree = new BinaryTree();
        Stack<Character> stack = new Stack<>();
        int n = tree.length();
        char c;
        for (int i = 0; i < tree.length(); i++) {
            c = tree.charAt(i);
            if (c != ' ') {
                if (c == '(') {
                    stack.push(c);
                    if (i < n)
                        binaryTree.insertDataLeft(tree.charAt(i + 1));
                } else if (c == ')')
                    if (stack.peek() == '(')
                        stack.pop();
            }
        }

        return null;
    }

    class BinaryTree {
        private Node root = null;

        public Node getRoot() {
            return root;
        }

        void insertDataLeft(int data) {
            Node node = new Node(data, null, null);
            if (root == null) {
                root = node;
            } else {
                insertLeft(node, root);
            }
        }

        void insertDataRight(int data) {
            Node node = new Node(data, null, null);
            if (root == null) {
                root = node;
            } else {
                insertRight(node, root);
            }
        }

        private Node insertLeft(Node node, Node root1) {
            if (root1 == null) {
                root1 = new Node(((Node) node).getData(), null, null);
                if (this.root == null) {
                    this.root = root1;
                }
            } else {
                root1.setLeftChild(insertLeft(node, root1.getLeftChild()));
            }
            return root1;
        }

        private Node insertRight(Node node, Node root1) {
            if (root1 == null) {
                root1 = new Node(((Node) node).getData(), null, null);
                if (this.root == null) {
                    this.root = root1;
                }
            } else {
                root1.setRightChild(insertLeft(node, root1.getRightChild()));
            }
            return root1;
        }
    }

    class Node {
        int data;
        Node leftChild, rightChild;

        private Node(int data) {
            this.data = data;
            leftChild = rightChild = null;
        }

        private Node(int data, Node left, Node right) {
            this.data = data;
            this.leftChild = left;
            this.rightChild = right;
        }

        public int getData() {
            return data;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }
}