package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeBinaryTree {

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
        
        String ser = serialize(tree.root);
        System.out.println(ser);
        
        BNode node = deserialize(ser);
        
        inorder(node);
        System.out.println();
    }

    private static void inorder(BNode node) {

        if(node == null) return;
        
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    private static BNode deserialize(String s) {
        
        if(s == null || s.length() == 0)
            return null;
        
        String[] arr = s.split(",");
        BNode root = new BNode(Integer.parseInt(arr[0]));
        Queue<BNode> q = new LinkedList<>();
        q.add(root);
        
        int i = 1;
        while(!q.isEmpty()){
            
            BNode node = q.poll();
            
            if(node == null)
                continue;
            
            if(!"#".equals(arr[i])){
                node.left = new BNode(Integer.parseInt(arr[i]));
                q.add(node.left);
            } else {
                node.left = null;
                q.add(node.left);
            }
            i++;
            
            if(!"#".equals(arr[i])){
                node.right = new BNode(Integer.parseInt(arr[i]));
                q.add(node.right);
            } else {
                node.right = null;
                q.add(node.right);
            }
            i++;
            
        }
        

        return root;
    }

    private static String serialize(BNode root) {

        Queue<BNode> q = new LinkedList<>();
        q.add(root);
        StringBuilder s = new StringBuilder();
        while(!q.isEmpty()){
            
            BNode poll = q.poll();
            if(poll != null){
                s.append(poll.data).append(",");
                q.add(poll.left);
                q.add(poll.right);
            }else{
                s.append("#,");
            }
        }
        
        s.deleteCharAt(s.length()-1);
        return s.toString();
        
    }

}
