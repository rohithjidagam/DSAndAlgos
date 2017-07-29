package com.uh;

import java.util.HashMap;

public class BinarySearchTreeSum {

    public static void main(String[] args) {

        BSTNode root = new BSTNode(1);
        root.left = new BSTNode(2);
        root.right = new BSTNode(3);
        root.left.left = new BSTNode(4);
        root.left.right = new BSTNode(5);
        root.right.left = new BSTNode(6);
        root.right.right = new BSTNode(7);

        //inorder
        verticalSum(root);

        //preorder
        topView(root);
        
        //preorder
        leftView(root);
        
        //reverse preorder
        rightView(root);
        
        //reverse preorder
        bottomView(root);

    }

    private static void bottomView(BSTNode root) {

        HashMap<Integer, Integer> map = new HashMap<>();

        bottomViewUtil(root, map, 0);

        System.out.println(map);
    }

    private static void bottomViewUtil(BSTNode root, HashMap<Integer, Integer> map, int i) {

        if(root == null)
            return;
        
        map.put(i, root.data);
        bottomViewUtil(root.right, map, i+1);
        bottomViewUtil(root.left, map, i-1);
    }

    private static void rightView(BSTNode root) {

        Sum sum = new Sum();
        sum.sum = 0;
        rightViewUtil(root,1,sum);
    }

    private static void rightViewUtil(BSTNode root, int level, Sum sum) {

        if(root == null) return;
        
        
        if(sum.sum < level){
            System.out.println(root.data);
            sum.sum = level;
        }
        
        leftViewUtil(root.right, level+1, sum);
        leftViewUtil(root.left, level+1, sum);
    }

    private static void leftView(BSTNode root) {

        Sum sum = new Sum();
        sum.sum = 0;
        leftViewUtil(root,1,sum);
    }

    private static void leftViewUtil(BSTNode root, int level, Sum sum) {

        if(root == null) return;
        
        if(sum.sum < level){
            System.out.println(root.data);
            sum.sum = level;
        }
        
        leftViewUtil(root.left, level+1, sum);
        leftViewUtil(root.right, level+1, sum);
        
        
        
    }

    private static void topView(BSTNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();

        topViewUtil(root, map, 0);

        System.out.println(map);

    }

    private static void topViewUtil(BSTNode root, HashMap<Integer, Integer> map, int hd) {

        if(root == null) return;
        
        if(map.get(hd) == null)
        map.put(hd, root.data);
        
        topViewUtil(root.left, map, hd-1);
        
        topViewUtil(root.right, map, hd+1);
        
    }

    private static void verticalSum(BSTNode root) {

        HashMap<Integer, Integer> map = new HashMap<>();

        verticalSumUtil(root, map, 0);

        System.out.println(map);
    }

    private static void verticalSumUtil(BSTNode root, HashMap<Integer, Integer> map, int hd) {

        if (root == null)
            return;

        verticalSumUtil(root.left, map, hd - 1);

     /*   Integer integer = map.get(hd);
        if (integer == null) {
            map.put(hd, root.data);
        } else {
            map.put(hd, integer + root.data);
        } */
        
        map.put(hd, map.getOrDefault(map.get(hd), 0) + root.data);
        verticalSumUtil(root.right, map, hd + 1);

    }

}
