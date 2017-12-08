package com.microsoft;

public class BinarySeachTree {

	public static void main(String[] args) {

		int[] arr = { 3, 20, 5, 7, 10, 30, 35, 15, 17 };

		BNode root = null;
		for (int i = 0; i < arr.length; i++) {
			root = insert(root, arr[i]);
		}

		inorder(root);
	}

	private static void inorder(BNode root) {

		if (root == null)
			return;
		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);
	}

	private static BNode insert(BNode root, int i) {

		if (root == null) {
			root = new BNode(i);
			return root;
		}

		if (root.data < i) {
			if (root.right == null)
				root.right = new BNode(i);
			else
				insert(root.right, i);
		} else {
			if (root.left == null)
				root.left = new BNode(i);
			else
				insert(root.left, i);
		}

		return root;
	}

}

class BNode {
	int data;
	BNode left;
	BNode right;

	public BNode(int d) {
		data = d;
		left = right = null;
	}
}
