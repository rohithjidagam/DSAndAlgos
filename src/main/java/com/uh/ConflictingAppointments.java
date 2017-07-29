package com.uh;

public class ConflictingAppointments {

    static ITree root;

    public static void main(String[] args) {

        INode[] interval = { new INode(1, 5), new INode(3, 7), new INode(2, 6), new INode(10, 15), new INode(5, 6),
                new INode(4, 100) };
        root = null;
        int n = interval.length;
        printConflicts(interval, n);
    }

    private static void printConflicts(INode[] interval, int n) {

        insert(interval[0], root);

    }

    private static ITree insert(INode iNode, ITree node) {
        if (node == null) {
            node = new ITree(iNode);
            return node;
        }

        int l = root.i.low;

        if (iNode.low < l) {
            root.left = insert(root.left.i, node);
        } else {
            root.right = insert(root.right.i, node);
        }
        
        if(root.max < node.max){
            
        }

        return root;

    }

}

class ITree {

    INode i;
    int max;
    ITree left, right;

    public ITree(INode i) {
        this.i = i;
        this.max = i.high;
        left = right = null;
    }

}

class INode {
    int low, high;

    INode(int l, int h) {
        this.low = l;
        this.high = h;
    }
}
