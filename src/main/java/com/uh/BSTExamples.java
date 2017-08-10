package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BSTExamples {

     //mode
    static BSTNode prev = null;
    static int max = Integer.MIN_VALUE;
    private static int count = 1;
    
    public static void main(String[] args) {

        BSTNode root = new BSTNode(50);
        root.left = new BSTNode(30);
        root.right = new BSTNode(70);
        root.left.left = new BSTNode(20);
        root.left.right = new BSTNode(40);
        root.right.left = new BSTNode(60);
        root.right.right = new BSTNode(80);

        Sum sum = new Sum();
         addGreaterValuesToNode(root, sum);
        inorder(root);
        System.out.println();
        sum.sum = 0;
        addLesserValuesToNode(root, sum);
        inorder(root);
        System.out.println();
        BSTNode pre = new BSTNode(0);
        BSTNode suc = new BSTNode(0);
        inorderPredAndSuc(root, 450, pre, suc);
        System.out.println(pre.data + "--" + suc.data);

        sum.sum = 0;
        KthLargest(root, 3, sum);

        sum.sum = 0;
        KthSmallest(root, 3, sum);

        System.out.println(nodesInRange(root, 340, 1100));

        sum.sum = 0;
        System.out.println(subtreesInRange(root, 20, 55, sum));

        sum.sum = 0;
        sum.count = 0;
        sumKSmallelemets(root, 3, sum);
        
        sum.sum = 0;
        sum.count = 0;
        sumKLargeelemets(root, 3, sum);
        
        inorder(root);
        System.out.println();
        swapAlternateLevel(root);
        inorder(root);
        System.out.println();
        BSTNode root1 = new BSTNode(50);
        root1.left = new BSTNode(30);
        root1.right = new BSTNode(70);
        root1.left.left = new BSTNode(20);
        root1.left.left.left = new BSTNode(20);
        root1.left.right = new BSTNode(40);
        root1.right.left = new BSTNode(60);
        root1.right.right = new BSTNode(80);
        root1.right.right.left = new BSTNode(80);
        BSTNode sucs = inorderSuccesor(root1, root1.right.left);
        System.out.println(sucs.data);
        
        List<Integer> modes = new ArrayList<>();
        mode(root1, modes);
        System.out.println(Arrays.deepToString(modes.toArray()));

    }

    //inorder
    private static void mode(BSTNode node, List<Integer> modes) {

        if(node == null)
            return;
        
        mode(node.left, modes);
        
        if(prev != null){
            if(node.data == prev.data)
                count++;
            else
                count = 1;
        }
        
        if(count > max){
            max = count;
            modes.clear();
            modes.add(node.data);
        } else if(count == max){
            modes.add(node.data);
        }
        prev = node;
        mode(node.right, modes);
        
    }

    private static BSTNode inorderSuccesor(BSTNode root, BSTNode n) {

        if(n.right != null){
            BSTNode r = n.right;
            while(r != null){
                r = r.left;
            }
            return r;
        }
        BSTNode suc = null;
        
        while(root != null){
            if(root.data > n.data){
                suc = root;
                root = root.left;
            } else if(root.data < n.data){
                root = root.right;
            } else
                break;
        }
        
        return suc;
    }

    private static void swapAlternateLevel(BSTNode root) {

        if(root == null) return;
        swapAltLevelUtil(root.left,root.right,0);
    }

    private static void swapAltLevelUtil(BSTNode left, BSTNode right, int l) {

        if(left == null  || right == null)
            return;
        
        if(l%2 !=0){
            int tmp = left.data;
            left.data = right.data;
            right.data = tmp;
        }
        
        swapAltLevelUtil(left.left,right.right,l+1);
        swapAltLevelUtil(left.right,right.left,l+1);
        
        
    }

    private static void sumKLargeelemets(BSTNode root, int k, Sum sum) {

        if(root == null || sum.count>k) return;
        
        sumKLargeelemets(root.right, k, sum);
        sum.sum += root.data;
        sum.count++;
        if(sum.count == k){
            System.out.println(sum.sum);
            return;
        }
        sumKLargeelemets(root.left, k, sum);
        
    }

    private static void sumKSmallelemets(BSTNode root, int k, Sum sum) {

        if (root == null || sum.count > k)
            return;

        sumKSmallelemets(root.left, k, sum);

        sum.sum += root.data;
        sum.count = sum.count + 1;

        if (sum.count == k) {
            System.out.println(sum.sum);
            return;
        }
        sumKSmallelemets(root.right, k, sum);

    }

    private static int subtreesInRange(BSTNode root, int low, int high, Sum sum) {
        if (root == null)
            return 0;

        int l = root.left != null ? subtreesInRange(root.left, low, high, sum) : 0;
        int r = root.right != null ? subtreesInRange(root.right, low, high, sum) : 0;

        if (l != 0 && r != 0 && root.data <= high && root.data >= low) {
            sum.sum = sum.sum + 1;
            return sum.sum;
        }

        return 0;
    }

    private static int nodesInRange(BSTNode root, int low, int high) {

        if (root == null)
            return 0;

        if (root.data >= low && root.data <= high) {
            return 1 + nodesInRange(root.left, low, high) + nodesInRange(root.right, low, high);
        }

        else if (root.data < low)
            return nodesInRange(root.right, low, high);
        else
            return nodesInRange(root.left, low, high);
    }

    private static void KthSmallest(BSTNode root, int k, Sum sum) {
        if (root == null || sum.sum > k)
            return;
        KthSmallest(root.left, k, sum);
        sum.sum = sum.sum + 1;
        if (k == sum.sum) {
            System.out.println(root.data);
            return;
        }
        KthSmallest(root.right, k, sum);

    }

    private static void KthLargest(BSTNode root, int k, Sum sum) {
        // reverse inorder

        if (root == null || sum.sum > k)
            return;

        KthLargest(root.right, k, sum);
        sum.sum = sum.sum + 1;
        ;
        if (k == sum.sum) {
            System.out.println(root.data);
            return;
        }
        KthLargest(root.left, k, sum);
    }

    private static void inorderPredAndSuc(BSTNode root, int key, BSTNode pre, BSTNode suc) {

        if (root == null)
            return;

        if (root.data == key) {

            if (root.left != null) {
                BSTNode cur = root.left;
                while (cur.right != null) {
                    cur = cur.right;
                }
                pre.data = cur.data;
            }

            if (root.right != null) {
                BSTNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                suc.data = cur.data;
            }
            return;
        }

        if (root.data > key) {
            suc.data = root.data;
            inorderPredAndSuc(root.left, key, pre, suc);
        } else {
            pre.data = root.data;
            inorderPredAndSuc(root.right, key, pre, suc);
        }
    }

    private static void inorder(BSTNode root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    private static void addGreaterValuesToNode(BSTNode root, Sum sum) {
        // reverse in order
        if (root == null)
            return;
        addGreaterValuesToNode(root.right, sum);
        sum.sum = sum.sum + root.data;
        root.data = sum.sum;
        addGreaterValuesToNode(root.left, sum);
    }

    private static void addLesserValuesToNode(BSTNode root, Sum sum) {
        if (root == null)
            return;
        addLesserValuesToNode(root.left, sum);
        sum.sum = sum.sum + root.data;
        root.data = sum.sum;
        addLesserValuesToNode(root.right, sum);
    }

}

class Sum {
    int sum;
    int count;
}
