package com.uh;

public class ConnectNodes {

    public static void main(String[] args) {

        BNode root;
        root = new BNode(1);
        root.left = new BNode(2);
        root.right = new BNode(3);
        root.left.left = new BNode(4);
        root.left.left.left = new BNode(6);
        root.left.right = new BNode(9);
        root.right.right = new BNode(5);
        root.right.right.right = new BNode(7);
        
        //using constant space
        connect(root);
        inoder(root);
    }
    
    private static void connect(BNode root) {

        BNode temp = new BNode(0);
        while(root != null){
            BNode cur = temp;

            while(root!=null){
                if(root.left != null){
                    cur.next =  root.left;
                    cur = cur.next;
                }
                
                if(root.right != null){
                    cur.next =  root.right;
                    cur = cur.next;
                }
                root = root.next;
                
            }
            root = temp.next;
            temp.next = null;
        }
    }

    private static void inoder(BNode node) {

        if (node == null)
            return;

        inoder(node.left);

        System.out.println(node + "->" + node.next);
        inoder(node.right);

    }

}
