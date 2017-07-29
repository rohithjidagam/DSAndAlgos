package com.uh;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PerfectBinarySearchTreeTraversals {

    public static void main(String[] args) {

        BSTNode root = new BSTNode(1);
        root.left = new BSTNode(2);
        root.right = new BSTNode(3);
        root.left.left = new BSTNode(4);
        root.left.right = new BSTNode(5);
        root.right.left = new BSTNode(6);
        root.right.right = new BSTNode(7);
        root.left.left.left = new BSTNode(8);
        root.left.left.right = new BSTNode(9);
        root.left.right.left = new BSTNode(10);
        root.left.right.right = new BSTNode(11);
        root.right.left.left = new BSTNode(12);
        root.right.left.right = new BSTNode(13);
        root.right.right.left = new BSTNode(14);
        root.right.right.right = new BSTNode(15);

        levelOrder(root);

        reverseLevelOrder(root);

        alternateTopToBottom(root);
        
        alternateBottomToTop(root);
        
        printNodesInEachLevel(root);
    }

    private static void printNodesInEachLevel(BSTNode root) {

        if(root == null) return;
        
        Queue<BSTNode> q = new LinkedList<>();
        q.add(root);
        
        int i = 1;
        while(!q.isEmpty()){
            
            int size = q.size();
            System.out.println("Level " + i++ +" has " + size + " nodes");
            
            while(size-- > 0){
                
                BSTNode node = q.poll();
                System.out.print(node.data + " ");
                
                if(node.left!=null){
                    q.add(node.left);
                }
                
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            
            System.out.println();
            
        }
    }

    private static void alternateBottomToTop(BSTNode root) {

        if(root == null) return;
        
        Stack<BSTNode> s = new Stack<BSTNode>();
        s.push(root);
        
        if(root.left == null) return;
        
        s.push(root.right);
        s.push(root.left);
        
        Queue<BSTNode> q = new LinkedList<BSTNode>();
        
        q.add(root.left);
        q.add(root.right);
        
        while(!q.isEmpty()){
            
            BSTNode first = q.poll();
            BSTNode second = q.poll();
            
            if(second.left!=null){
                
                //LRRL
                s.push(second.left);
                s.push(first.right);
                s.push(second.right);
                s.push(first.left);
                
                //Order change Note RLLR
                q.add(first.right);
                q.add(second.left);
                q.add(first.left);
                q.add(second.right);
            }
        }
        
        while(!s.isEmpty()){
            System.out.print(s.pop().data + " ");
        }
        System.out.println();
    }

    private static void alternateTopToBottom(BSTNode root) {

        if (root == null)
            return;

        System.out.print(root.data + " ");

        if (root.left != null)
            System.out.print(root.left.data + " " + root.right.data + " ");

        if (root.left == null)
            return;

        Queue<BSTNode> q = new LinkedList<>();

        q.add(root.left);
        q.add(root.right);

        while (!q.isEmpty()) {

            BSTNode first = q.poll();
            BSTNode second = q.poll();

            if (first.left != null) {
                System.out.print(first.left.data + " " + second.right.data + " ");
                System.out.print(first.right.data + " " + second.left.data + " ");
                
                //same order as printing LRRL
                q.add(first.left);
                q.add(second.right);
                q.add(first.right);
                q.add(second.left);
            }
        }
        System.out.println();
    }

    private static void reverseLevelOrder(BSTNode root) {

        if (root == null)
            return;

        Queue<BSTNode> q = new LinkedList<>();
        Stack<Integer> s = new Stack<>();

        q.add(root);
        while (!q.isEmpty()) {

            BSTNode node = q.poll();
            s.push(node.data);

            // Right -> Left is pushed to queue
            if (node.right != null) {
                q.add(node.right);
            }
            if (node.left != null) {
                q.add(node.left);
            }
        }

        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
        System.out.println();
    }

    private static void levelOrder(BSTNode root) {

        if (root == null)
            return;

        Queue<BSTNode> q = new LinkedList<BSTNode>();
        q.add(root);

        while (!q.isEmpty()) {

            BSTNode node = q.poll();
            System.out.print(node.data + " ");

            // Left -> Right is pushed to queue
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }

        }
        System.out.println();
    }

}
