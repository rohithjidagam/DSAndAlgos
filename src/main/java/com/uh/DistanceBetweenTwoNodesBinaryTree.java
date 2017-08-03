package com.uh;

public class DistanceBetweenTwoNodesBinaryTree {

    static boolean left = false;
    static BNode targetNode = null;
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        tree.root = new BNode(1);
        tree.root.left = new BNode(2);
        tree.root.right = new BNode(3);
        tree.root.right.left = new BNode(6);
        tree.root.right.right = new BNode(7);
        tree.root.left.left = new BNode(4);
        tree.root.left.right = new BNode(5);
        tree.root.right.left.right = new BNode(8);
        tree.root.right.left.right.right = new BNode(9);
        tree.root.right.left.right.left = new BNode(10);

        distanceBetweeN2Nodes(tree);
        System.out.println();
        System.out.println("*******************");
        
        kNodesFromTarget(tree);
        System.out.println();

    }

    private static void kNodesFromTarget(BinaryTree tree) {

        int k = 3;
        int target = 3;
        
        int d = distance(tree.root, target);
        System.out.println(d);
        System.out.println(left);

        if(k-d > 0 && !left){
            printKNodesRoot(tree.root.left, k-d-1);
            printKNodesRoot(targetNode, k);
        }
        
        if(k-d > 0 && left){
            printKNodesRoot(tree.root.right, k-d-1);
            printKNodesRoot(targetNode, k);
        }
        
        if(k-d < 0 && !left){
            printKNodesRoot(tree.root.right, -(k-d)-1);
        }
        
        if(k-d < 0 && left){
            printKNodesRoot(tree.root.left, -(k-d)-1);
        }
    }

    private static void printKNodesRoot(BNode root, int k) {
        
        if(root == null)
            return;
        
        if(k == 0){
            System.out.print(root.data + " ");
            return;
        }
        
        printKNodesRoot(root.left, k-1);
        printKNodesRoot(root.right, k-1);
            
        
    }

    private static int distance(BNode root, int target) {

        if(root == null)
            return -1;
        
        if(root.data == target){
            targetNode = root;
            return 0;
        }
        
        int ld = distance(root.left, target);
        int rd = distance(root.right, target);
        
        if(ld == -1 && rd == -1)
            return -1;
        
        left = ld > rd ? true : false;
        
        return Math.max(ld, rd) + 1;
        
    }

    private static void distanceBetweeN2Nodes(BinaryTree tree) {
        int n1 = 5;
        int n2 = 8;

        // dist(n1,n2) = dist(root,n1) + dist(root,n2) - 2* dist(root,lca)

        BNode lca = lca(tree.root, n1, n2);

        int d1 = dist(tree.root, n1);
        int d2 = dist(tree.root, n2);
        int lca_dist = dist(tree.root, lca.data);

        System.out.println(d1 + "-" + d2 + "-" + lca_dist);

        System.out.println("Distance:" + (d1 + d2 - 2 * lca_dist));
        
        printPath(tree.root,n1);
        System.out.println();
        printPath(tree.root,n2);
    }

    private static boolean printPath(BNode root, int n1) {

        if(root == null)
            return false;
        
        if(root.data == n1 || printPath(root.left, n1) || printPath(root.right, n1)){
            System.out.print(root.data + "-");
            return true;
        }
        return false;
    }

    private static int dist(BNode root, int n1) {

        if (root == null)
            return 0;

        int x = 0;
        if ((root.data == n1) || (x = dist(root.left, n1)) > 0 || (x = dist(root.right, n1)) > 0) {
            return x + 1;
        }
        return 0;
    }

    private static BNode lca(BNode root, int n1, int n2) {

        if (root == null)
            return null;

        if (root.data == n1 || root.data == n2)
            return root;

        BNode left_lca = lca(root.left, n1, n2);
        BNode right_lca = lca(root.right, n1, n2);

        if (left_lca != null && right_lca != null)
            return root;

        return left_lca != null ? left_lca : right_lca;

    }

}
