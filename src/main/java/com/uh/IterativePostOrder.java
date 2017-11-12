package com.uh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class IterativePostOrder {

	static Node root;
	ArrayList<Integer> list = new ArrayList<Integer>();
	static boolean mat[][];

	public static void main(String[] args) {

		IterativePostOrder tree = new IterativePostOrder();

		// Let us create trees shown in above diagram
		tree.root = new Node(5);
		tree.root.left = new Node(1);
		tree.root.right = new Node(2);
		tree.root.left.left = new Node(0);
		tree.root.left.right = new Node(4);
		tree.root.right.left = new Node(3);
		tree.root.right.right = new Node(6);

		// ArrayList<Integer> mylist = tree.postOrderIterative(tree.root);

		// System.out.println("Post order traversal of binary tree is :");
		// System.out.println(mylist);

		// ancestorMatrix(root);

		rootToLeaf(root);

	}

	private static void rootToLeaf(Node root) {

		if (root == null)
			return;

		Stack<Node> stack = new Stack<>();

		HashMap<Node, Node> map = new HashMap<>();

		stack.push(root);
		map.put(root, null);

		while (!stack.isEmpty()) {

			Node n = stack.pop();

			if (n.right == null && n.left == null) {
				print(n, map);
			}

			if (n.right != null) {
				stack.push(n.right);
				map.put(n.right, n);
			}
			if (n.left != null) {
				stack.push(n.left);
				map.put(n.left, n);
			}
		}
	}

	private static void print(Node n, HashMap<Node, Node> map) {

		Stack<Node> st = new Stack<>();

		while (n != null) {
			st.push(n);
			n = map.get(n);
		}

		while (!st.isEmpty()) {
			System.out.print(st.pop().data + "->");
		}

		System.out.println();
	}

	private static void ancestorMatrix(Node root2) {

		List<Integer> list = new ArrayList<>();
		mat = new boolean[10][10];
		int n = ancestorMatrixUtil(root2, list);

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				if (mat[i][j])
					System.out.print(1 + " ");
				else
					System.out.print(0 + " ");
			}
			System.out.println();
		}

	}

	private static int ancestorMatrixUtil(Node root2, List<Integer> list2) {

		if (root2 == null)
			return 0;

		int data = root2.data;

		for (int i = 0; i < list2.size(); i++) {
			mat[list2.get(i)][data] = true;
		}

		list2.add(data);

		int l = ancestorMatrixUtil(root2.left, list2);
		int r = ancestorMatrixUtil(root2.right, list2);

		list2.remove(list2.size() - 1);

		return l + r + 1;
	}

	private ArrayList<Integer> postOrderIterative(Node node) {

		if (node == null) {
			return list;
		}

		Stack<Node> stack = new Stack<Node>();

		Node root = node;
		while (root != null) {
			if (root.right != null) {
				stack.push(root.right);
				stack.push(root);
				root = root.left;
			}

			if (root == null) {

				Node tmp = stack.pop();
				if (tmp.right == null) {
					System.out.println(tmp.data);
				}
				if (tmp.right != null && tmp.right.data == stack.peek().data) {

				}
				root = null;
			}
		}
		return null;
	}

}

class Node {
	int data;
	Node left, right;

	Node(int item) {
		data = item;
		left = right;
	}
}
